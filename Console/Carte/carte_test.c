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
	char data[64];

	switch (msg)
	{
		char mess;
		int i, frame;
		
		
		case 0 : // lors de la connexion initiale
			
			printf("connexion msg = 0\n");
			break;	// fin de l'initialisation

		case 'd':	// demande de login
		//	CNX_recoit (sock, data, 11);
		//	if (strcmp (data,"admin,admin")==0)	// =0 si les 2 chaines sont egales
		//	{	
				// test du login
				login_ok = 1;
			//	CNX_envoi(sock, "ok", 2);
				// recoit d'abord la taille des donnees a recevoir
				// recoit les donnees de la taille precedemment envoyee
				// mise à jour des fichiers quand ttes les donnees annoncees sont arrivees
				printf ("dans 'd'\n");
				f_test=fopen("ordres.txt", "w");	// ouverture du fichier en ecriture
				CNX_recoit_int (sock, &taille);
				frame = 64; 	//taille d'une frame socket en reception
				printf ("taille = %d frame %d\n", taille, frame);
				
				// decouper la taille en plusieurs frame
				if (taille >= frame)
				{
					i=taille;
					printf ("taille = %d i mod frame = %d\n", taille, i%frame);
					do
				//	for (i=taille; i<=(i%frame); i= i-frame)
					{
						printf ("dans for i = %d\n", i);
						CNX_recoit (sock, data, frame);
						printf ("%s\n", data);
						fwrite (data,1, frame, f_test);
						CNX_envoi (sock, "ok\n", 3);
						i=i-frame;
					}while (i > (i%frame));
					
					if (i!=0)
					{
						printf ("dans if i = %d\n", i);
						CNX_recoit (sock, data, i);
						printf ("%s\n", data);
						fwrite (data,1, i, f_test);
						CNX_envoi (sock, "ok\n", 3);
					}
				}
				else
				{
					CNX_recoit (sock, data, taille);
					printf ("%s\n", data);
					fprintf (f_test, "%s\n", data);
					CNX_envoi (sock, "ok\n", 3);
				}
				fclose (f_test);

				printf ("apres envoi string\n");
			//	fclose (f_test);
		//	}
		/*	else	//probleme de login
			{
				CNX_envoi (sock, "wrong", 5);
			}*/
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
		printf ("init carte - attente connexion\n");

		if (CNX_serveur(2000,cb))					//port 2000 et callback
		{
			printf ("erreur serveur\n");
			return 1;
		}
		if (login_ok == 1)	// si on eu une connexion, on fork et on lance le programme parseur
		{
			login_ok = 0;
			// on lance parsage fichier ici comme ca la deconnexion a eu lieu et le vfork ne peut avoir 
			//lieu dans la callback
			// il faut lire la premiere ligne du fichier pour synchroniser le temps du PC et le temps de la carte
		/*	f_test=fopen("ordre.txt", "r");
			if (f_test == NULL)
			{
				printf ("fichier: erreur ouverture!!");
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
				wait (NULL);	// le pere attend le fils*/
				
			if (!vfork())	//on est dans le fils	on lance le programme de parse et on envoi sur la laison serie les donnees
			{
				execlp ("./parser", "./parser", NULL);
				_exit (3);
			}
			wait (NULL);	// le pere attend le fils
			//}
		}	
		printf ("connexion carte terminee\n");
	}
	return 0;
}


