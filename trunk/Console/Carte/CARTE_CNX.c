//------------------------------------------------------------------------

// Carte ARM

//------------------------------------------------------------------------

// Biblioth�ques standards
#include <stdio.h>
//#include <time.h>
#include <errno.h>
#include <string.h>	// (pour strerror)

// Biblioth�ques de gestion de socket
#include <sys/socket.h>
//#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>


#include <netinet/tcp.h>
//#include <fcntl.h>	// Controle d'option

// D�finition des messages d'�change
#include "CARTE_CNX.h"

//#define DEBUG_CNX

static int tailleBufferEnvoi;				// Taille du buffer d'envoi en octets
static int tailleBufferRecoit;				// Taille du buffer de reception en octets

#define max(_a,_b) (((_a)>(_b))?(_a):(_b))
#define min(_a,_b) (((_a)<(_b))?(_a):(_b))


static void erreur(char* msg) {
	printf("erreur : %s\n(%s)\n",msg,strerror(errno));
}

// Serveur
// Accepte une seule connexion (permet uniquement le monothread)
// rend 0 si pas d'erreur
int CNX_serveur(unsigned short port,int (*cb)(unsigned char msg,SOCKET sock)) {
	SOCKET socketService;
	SOCKET socketEcoute;
	SOCKADDR_IN addrServeur,addrClient;
	int optim;				// Optimisation du buffer d'envoi
	int lng;
	unsigned char message;

	// Cr�ation de la socket d'�coute de nouvelle connexion
	socketEcoute = socket(AF_INET,SOCK_STREAM,IPPROTO_TCP);
	if(socketEcoute==-1) {
		erreur("Cr�ation de la socket d'�coute impossible"); 
		return 1;
	}

	// Accepte plusieurs connexions de m�me adresse
	// (utilis� car la couche r�seau semble mettre du temps � fermer la connexion
	// -> empeche de relancer le serveur juste apr�s l'avoir ferm�)
	optim = 1;	// TRUE
	if(setsockopt(socketEcoute,SOL_SOCKET,SO_REUSEADDR,(char*)&optim,sizeof(int))) {
		erreur("Info de socket impossible"); 
		return 1;
	}

	// Attachement de la socket au port d'�coute
	addrServeur.sin_family = AF_INET;					// Espace de nommage : internet
	addrServeur.sin_addr.s_addr = htonl(INADDR_ANY);	// Addresse IP
	addrServeur.sin_port = htons(port);					// Num�ro de port du service
	if(bind(socketEcoute,(SOCKADDR*)&addrServeur,sizeof(SOCKADDR_IN))) {
		erreur("Relation entre socket et addresse impossible");
		return 1;
	}

	// Attente de connexion
#ifdef DEBUG_CNX
	printf("CNX_serveur : attend\n");
#endif
	if(listen(socketEcoute,1)) {
		erreur("Probl�me d'�coute socket");
		return 1;
	}

	// Accepte la demande de connexion
#ifdef DEBUG_CNX
	printf("CNX_serveur : connection\n");
#endif
	lng = sizeof(addrClient);
	socketService = accept(socketEcoute,(SOCKADDR*)&addrClient,&lng);

	// Ferme la socket d'�coute
	if(close(socketEcoute)) {
		erreur("Fermeture de la socket d'�coute");
		return 1;
	}

	// Red�fini le buffer d'envoi
//	tailleBufferEnvoi = 0x50000;
//	if(setsockopt(socketService,SOL_SOCKET,SO_SNDBUF,(char*)&tailleBufferEnvoi,lng))
//		erreur("Info de socket impossible");

	// D�sactive l'optimisation d'envoi
	optim = 1;
	if(setsockopt(socketService,IPPROTO_TCP,TCP_NODELAY,(char*)&optim,sizeof(int)))
		erreur("Info de socket impossible");

	// M�morise la taille du buffer d'envoi
	lng = sizeof(int);
	if(getsockopt(socketService,SOL_SOCKET,SO_SNDBUF,(char*)&tailleBufferEnvoi,&lng))
		erreur("Info de socket impossible");
#ifdef DEBUG_CNX
	printf("Buffer d'envoi : %i octets\n",tailleBufferEnvoi);
#endif

	// M�morise la taille du buffer de reception
	lng = sizeof(int);
	if(getsockopt(socketService,SOL_SOCKET,SO_RCVBUF,(char*)&tailleBufferRecoit,&lng))
		erreur("Info de socket impossible");
#ifdef DEBUG_CNX
	printf("Buffer de reception : %i octets\n",tailleBufferRecoit);
#endif

	// App�le la callback d'initialisation
	if(cb(0,socketService)) {
		// Demande de fermeture
		if(close(socketService))
			erreur("Fermeture de la socket de service");

		printf("Connexion ferm�e\n");

		return 0;
	}

	// Initialise le descripteur de sockets en attente de lecture
	// Boucle principale
	while(1) {
		// Lit le message
		if(CNX_recoit_char(socketService,&message))
			message = 0xFF;

			// App�le la callback
		if(cb(message,socketService)) {
			// Demande de fermeture
			if(close(socketService))
				erreur("Fermeture de la socket de service");

			printf("Connexion ferm�e\n");

			return 0;
		}
	}
}

/*
// Change les options de connexion
int LNX_options() {
	int b,s;

	if(getsockopt(socketService,IPPROTO_TCP,TCP_NODELAY,(char*)&b,&s))
		erreur("Info de socket impossible");
	printf("b=%i  s=%i\n",b,s);

	b=1,s=sizeof(int);
	if(setsockopt(socketService,IPPROTO_TCP,TCP_NODELAY,(char*)&b,s))
		erreur("Info de socket impossible");

	if(getsockopt(socketService,IPPROTO_TCP,TCP_NODELAY,(char*)&b,&s))
		erreur("Info de socket impossible");
	printf("b=%i  s=%i\n",b,s);

	return 0;
}*/

// Re�oit des donn�es du client
// Rend 0 si pas d'erreur
int CNX_recoit(SOCKET sock,void* ptr,int nb) {
	int lng=0,res;

#ifdef DEBUG_CNX
	printf("CNX_recoit : %i octets\n",nb);
#endif

	// Recoit tous les octets attendus
	while(lng<nb) {
		res = recv(sock,((char*)ptr)+lng,nb-lng,0);
		if(res==0 || res==-1) {
			erreur("Reception socket");
			return 1;
		}
		lng += res;
	}

	return 0;
}


// Envoi des donn�es au client
// Rend 0 si pas d'erreur
int CNX_envoi(SOCKET sock,void* ptr,int nb) {
	// Fractionne les donn�es en fonction de la taille max du buffer d'envoi
	int lng=0;

#ifdef DEBUG_CNX
	printf("CNX_envoi : %i octets\n",nb);
#endif

	while(lng<nb) {
		int snd = min(tailleBufferEnvoi,nb-lng);

		if(send(sock,((char*)ptr)+lng,snd,0)!=snd) {
			erreur("Envoi socket");
			return 1;
		}
		lng += snd;
	}

	return 0;
}
/*int CNX_envoi(SOCKET sock,void* ptr,int nb) {
	if(send(sock,ptr,nb,0)!=nb) {
		erreur("Envoi socket");
		return 1;
	}

	return 0;
}*/


// Envoi un octet au client
// Rend 0 si pas d'erreur
int CNX_envoi_char(SOCKET sock,char val) {
	return CNX_envoi(sock,&val,1);
}


// Re�oit un octet du client
// Rend 0 si pas d'erreur
int CNX_recoit_char(SOCKET sock,char* val) {
	return CNX_recoit(sock,val,1);
}


// Re�oit un entier du client
// Rend 0 si pas d'erreur
int CNX_recoit_int(SOCKET sock,int* val) {

	if(CNX_recoit(sock,val,4))
		return 1;

	*val = ntohl(*val);

	return 0;
}


// Envoi un entier au client
// Rend 0 si pas d'erreur
int CNX_envoi_int(SOCKET sock,int val) {
	int i = htonl(val);

	return CNX_envoi(sock,&i,4);
}


// Etablit une connexion avec un serveur
// rend NULL si erreur
SOCKET CNX_connecte(char* ip,unsigned short port) {
	SOCKADDR_IN serveur;
	int lng;
	int optim;
	int i;
	SOCKET sock;

	// Cr�ation de socket
	sock = socket(AF_INET,SOCK_STREAM,IPPROTO_TCP);
	if(sock==-1) {
		erreur("Cr�ation socket impossible");
		return SOCKET_ERROR;
	}

	// Connexion au serveur
	serveur.sin_family = AF_INET;					// Espace de nommage : internet
	serveur.sin_addr.s_addr = inet_addr(ip);
//	if(!inet_aton(ip,&serveur.sin_addr.s_addr)) {	// Addresse IP
//		erreur("Addresse incorrecte");
//		return SOCKET_ERROR;
//	}
	serveur.sin_port = htons(port);					// Num�ro de port du service

	i = 0;
	while(1) {
		if(connect(sock,(SOCKADDR*)&serveur,sizeof(SOCKADDR_IN))==0)
			break;
		else if(i<10)
			usleep(500000);
		else {
			erreur("Connexion serveur impossible");
			return SOCKET_ERROR;
		}
		i++;
	}

	// Red�fini le buffer d'envoi
//	tailleBufferEnvoi = 0x50000;
//	if(setsockopt(sock,SOL_SOCKET,SO_SNDBUF,(char*)&tailleBufferEnvoi,sizeof(int))) {
//		erreur("Info de socket impossible");
//		return SOCKET_ERROR;
//	}

	// Red�fini le buffer de reception
//	if(setsockopt(sock,SOL_SOCKET,SO_RCVBUF,(char*)&bufferMax,lngBufferMax)) {
//		erreur("Changement taille buffer socket impossible");
//		return SOCKET_ERROR;
//	}

	// D�sactive l'optimisation d'envoi
	optim = 1;
	if(setsockopt(sock,IPPROTO_TCP,TCP_NODELAY,(char*)&optim,sizeof(int)))
		erreur("Info de socket impossible");

	// M�morise la taille du buffer d'envoi
	lng = sizeof(int);
	if(getsockopt(sock,SOL_SOCKET,SO_SNDBUF,(char*)&tailleBufferEnvoi,&lng))
		erreur("Info de socket impossible");
	printf("Buffer d'envoi : %i octets\n",tailleBufferEnvoi);

	// M�morise la taille du buffer de reception
	lng = sizeof(int);
	if(getsockopt(sock,SOL_SOCKET,SO_RCVBUF,(char*)&tailleBufferRecoit,&lng))
		erreur("Info de socket impossible");
	printf("Buffer de reception : %i octets\n",tailleBufferRecoit);


	return sock;
}


// Ferme la connexion avec un serveur
// Rend 0 si pas d'erreur, SOCKET_ERROR sinon
int CNX_deconnecte(SOCKET sock) {

	// Ferme la socket
	return close(sock);
}

