//----------------------------------------------------------
//
//   Programme PC pour communication socket avec la carte
//
//----------------------------------------------------------

// protocole: 
// - connexion

#include "PC_CNX.h"

#include "test.h"

CARTE carte;


//*********************************************************************************************
// Creation de la socket et connexion aux cam�ras
// TRUE = maitre
int connexion(char* ip) 
{
	char acc;
	int i;

	printf ("avant connecte\n");
	carte.sock = CNX_connecte(ip,PORT);
	if (carte.sock == SOCKET_ERROR)	// erreur dans la connexion
	{
		printf ("connexion impossible!!\n");
		return CONNEXION_IMPOSSIBLE;
	}
	CNX_recoit_char(carte.sock,&acc);

	return PAS_ERREUR;
}

//*********************************************************************************************
// envoi la taille des donnees puis les donnees a la carte
int donnees(void* data, int taille)
{
	char acc;
	CNX_envoi_char(carte.sock,'d');	// dit a la carte que l'on va envoyer les donnees pour qu'elle se place dans la
					// bonne partie du code de la callback
//	taille = strlen (data);

	printf ("taille = %d\n", taille);
	CNX_envoi_int(carte.sock, taille);
	CNX_recoit_char (carte.sock, &acc);
	printf ("accuse = %c\n", acc);
	CNX_envoi (carte.sock,data,taille);

	return PAS_ERREUR;
}


//*********************************************************************************************
// fonction qui deconnecte la carte
int deconnexion() 
{
	CNX_deconnecte(carte.sock);	
	return PAS_ERREUR;
}

//*********************************************************************************************
// test de l'appli cougar
int main (int argc, char**argv) 
{
	int taille;
/*	FILE *f_test;
	f_test=fopen("test.txt", "w");
	fprintf (f_test, "coucou 01234");
	fseek (f_test, 0, SEEK_END);
	taille = ftell (f_test);
	fclose (f_test);*/
	// connexion a la carte
	taille = strlen ("1234ABC:PPPOIU");
	printf ("connexion %d\n",connexion ("192.168.0.30"));
	
	

	donnees ("1234ABC:PPPOIU", taille);

	deconnexion();
	
	return 0;
}
