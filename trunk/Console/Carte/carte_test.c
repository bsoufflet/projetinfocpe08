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


// Type bool�en
typedef int bool;
#define TRUE (1==1)
#define FALSE (1==0)

int i;	// indice pour les boucles for

//********************************************************************************************
// fonction de callback qui recoit et envoi les sockets au PC
int cb (unsigned char msg,SOCKET sock)			//fonction de callback
{

	char acc;
	int taille;
	FILE *data;
	char *a_mettre_ds_fichier;

	switch (msg)
	{
		char mess;
		case 0 : // lors de la connexion initiale
			
			if(CNX_envoi_char(sock,'1'))	//accuse
				return 1;
			printf("connexion msg = 0\n");
		
			break;	// fin de l'initialisation

		case 'd' :	// recoit la demande d'envoi des donnees
			// recoit d'abord la taille des donnees a recevoir
			// recoit les donnees de la taille precedemment envoyee
			// mise à jour des fichiers quand ttes les donnees annoncees sont arrivees
			printf ("dans 'd'\n");
			CNX_recoit_int (sock, &taille);
			CNX_envoi_char (sock, "o");
			printf ("taille = %d\n", taille);
			CNX_recoit (sock, data, taille);
			printf ("%s\n", data);
			break;

		case 255 :		// message de fin de connexion
			return 1;	// fin de la connexion
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
		printf ("terminate carte\n");
	}
	return 0;
}


