//************************************************************************************************************************
//
//	bibliotheque pour la camera cougar
//
//************************************************************************************************************************
// PROTOCOLE: 
// acquisition en sequence:
//	- connexion de toutes les cameras
//	- reglage
//	- acquisition (si esclave commencer par les faire acquerir avant les maitres)
//	- transfert des images dans un buffer de la camera correspondante
//	- deconnexion de toutes les cameras
// si l'acquisition se fait en continue:
//	- connexion de toutes les cameras
//	- reglage
//	- acquisition avec le parametre 0 pour le nb d'images (si esclave commencer par les faire acquerir avant les maitres)
//	- transfert en continue
//	- demande d'arret du continu et arret du transfert
//	- appel de la fonction nb_im_restantes (int id) pour recupere le nb d'image qu'il reste a transferer
//	- transfere de ces images
//	- deconnexion
//************************************************************************************************************************


#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

// Taille du message envoy�
#define TAILLE 10

//taille de la longueur de chaine
#define CHN			80
// Taille de la chaine de caractere
#define LNG			1024

#define PORT 2000

typedef int bool;

typedef struct{
	SOCKET sock;
}CARTE;	//structure 


//sturcture des messages d'erreur
typedef enum{
	PAS_ERREUR,
	CONNEXION_IMPOSSIBLE,
	CONNEXION_EXISTANTE,
	DECONNEXION_IMPOSSIBLE,
	DECONNECTE,
	CAPTEUR_UTILISE,
	VALEUR_INCORRECTE, 
	ERREUR_PRISE,
	FIN_PROG,
}ERREUR;

char message [LNG];

int i_d =0;


int connexion(char* ip);

// envoi la taille des donnees puis les donnees a la carte
int donnees(void * data, int taille);

// fonction qui deconnecte la carte
int deconnexion();
