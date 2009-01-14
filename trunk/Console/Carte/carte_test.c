//***********************************************************************************
//			Programme CARTE
//***********************************************************************************
// Programme qui recoit une connexion avec le serveur par socket et qui lance
// la sauvegarde dans des fichiers de ce qu'il a recu.
// Il lance aussi le programme parseur pour que le fichier soit lu et que les
// données soient envoyées sur la liaison série.
//***********************************************************************************

#include <stdio.h>
#include "CARTE_CNX.h"
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>


// Type bool�en
typedef int bool;
#define TRUE (1==1)
#define FALSE (1==0)

int login_ok = 1;	// login de base faux
int i;	// indice pour les boucles for
FILE *f_test;
pid_t fils_pid;

//********************************************************************************************
// fonction de callback qui recoit et envoi les sockets au PC
int cb (unsigned char msg,SOCKET sock)			//fonction de callback
{

	char acc;
	int taille, frame;
	char *data;

	frame = 128; //taille d'une frame socket en reception

	switch (msg)
	{
		char mess;
		int i, frame;
		
		
		case 0 : // lors de la connexion initiale
			
			printf("connexion msg = 0\n");
			break;	// fin de l'initialisation

		case 'l':	// demande de login
			CNX_recoit (sock, data, 11);
			if (strcmp (data,"admin,admin")==0)	// =0 sir les 2 chianes soont egales
			{	
				// test du login
				login_ok = 1;
				CNX_envoi(sock, "ok", 2);
				// recoit d'abord la taille des donnees a recevoir
				// recoit les donnees de la taille precedemment envoyee
				// mise à jour des fichiers quand ttes les donnees annoncees sont arrivees
				printf ("dans 'l'\n");
				f_test=fopen("ordre.txt", "w");	// ouverture du fichier en ecriture
				CNX_recoit_int (sock, &taille);
				printf ("taille = %d\n", taille);
				
				// decouper la taille en plusieurs frame
				for (i=taille; i==i%frame; i= i-frame)
				{
					CNX_recoit (sock, data, frame);
					printf ("%s\n", data);
					fprintf (f_test, "/s\n", data);
				}
				if (i!=0)
				{
					CNX_recoit (sock, data, i);
					printf ("%s\n", data);
					fprintf (f_test, "/s\n", data);
				}
				fclose (f_test);

				printf ("apres envoi string\n");
				fclose (f_test);
			}
			else	//probleme de login
			{
				CNX_envoi (sock, "wrong", 5);
			}
			break;

		case 255 :		// message de fin de connexion
			printf ("message deconnexion %i\n", msg);
			return 1;
			break;	

		default :
			printf ("message inconnue %i\n", msg);
			break;
	}
	return 0;

}

//************************************************************************************************
// programme principal qui lance la callback
int main()
{

	char time[12];	// string pour le temps que l'on recoit dans le fichir pour la synchro
	while (1)
	{
		printf ("init carte\n");

		if (CNX_serveur(2000,cb))					//port 2000 et callback
		{
			printf ("erreur serveur\n");
			return 1;
		}
		if (login_ok == 1)	// si on eu un bon login, on fork et on lance le programme parseur
		{
			login_ok = 0;
			// on lance parsage fichier ici comme ca la deconnexion a eu lieu et le vfork ne peut avoir 
			//lieu dans la callback
			// il faut lire la premiere ligne du fichier pour synchroniser le temps du PC et le temps de la carte
			f_test=fopen("ordre.txt", "r");
			if (f_test == NULL)
			{
				printf ("fichier introuvable ou erreur ouverture!!");
			}
			else
			{
				fscanf(f_test, "%s\n", &time);
				printf("time lu: %s\n", time);
				fclose(f_test);
				
				
				if (!vfork())	// dans le fils
				{
					printf ("commande date\n");
					execlp ("date", "date", time, NULL); //on remet le timestamp du PC dans la carte
					_exit (3);
				}
				wait (NULL);	// le pere attend le fils
				
				if (!vfork())	//on est dans le fils
				{
					execlp ("./parser", "./parser", NULL);
					_exit (3);
				}
				wait (NULL);	// le pere attend le fils
			}
		}	
			// sinon on rebouble sur une attente de connexion
		
		printf ("terminate carte\n");
	}
	return 0;
}

