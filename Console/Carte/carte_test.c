//***********************************************************************************
//			Programme CARTE
//***********************************************************************************
// Programme qui recoit une connexion avec le serveur par socket et qui lance
// la sauvegarde dans des fichiers de ce qu'il a recu.
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

int login_ok = 0;
int i;	// indice pour les boucles for

//********************************************************************************************
// fonction de callback qui recoit et envoi les sockets au PC
int cb (unsigned char msg,SOCKET sock)			//fonction de callback
{

	char acc;
	int taille, frame;
	char *data;
	pid_t fils_pid;

	frame = 128; //taille d'une frame socket en reception

	switch (msg)
	{
		char mess;
		int i, frame;
		FILE *f_test;
		
		case 0 : // lors de la connexion initiale
			
			//if(CNX_envoi_char(sock,'1'))	//accuse
			//	return 1;
			printf("connexion msg = 0\n");
			
		
			break;	// fin de l'initialisation

		case 'l':
			CNX_recoit(sock, data, 11);
			if (data[0]=='a' && data[1]=='d' && data[2]=='m' && data[3]=='i' && data[4]=='n' && data[5]==',' && data[6]=='a' && data[7]=='d' && data[8]=='m' && data[9]=='i' && data[10]=='n')
			{	
				// test du login
				login_ok = 1;
				CNX_envoi(sock, "ok", 2);
				// recoit d'abord la taille des donnees a recevoir
				// recoit les donnees de la taille precedemment envoyee
				// mise à jour des fichiers quand ttes les donnees annoncees sont arrivees
				printf ("dans 'l'\n");
				f_test=fopen("ordre.txt", "w");
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
				fclose (f_test);*/

				CNX_recoit (sock, data, taille);
				fprintf (f_test,"%s", data);
				printf ("%s\n", data);
			//	CNX_envoi (sock, "coucou paul 1234\n", strlen("coucou paul 1234\n"));
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

	while (1)
	{
		printf ("init carte\n");

		if (CNX_serveur(2000,cb))					//port 2000 et callback
		{
			printf ("erreur serveur\n");
			return 1;
		}
		if (login_ok == 1)
		{
			login_ok = 0;
			// on lance parsage fichier ici comme ca la deconnexion a eu lieu
			
			if (!vfork())	//on est dans le fils
			{
				execlp ("./parser", "./parser", NULL);
				_exit (3);
			}
			wait (NULL);	// le pere attend le fils
		}
		
		printf ("terminate carte\n");
	}
	return 0;
}


