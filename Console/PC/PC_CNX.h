
// Biblioth�ques de gestion de socket
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <pthread.h>

#include <netinet/tcp.h>

// Red�finition des types utilis�s par les sockets
typedef int SOCKET; 
typedef struct sockaddr_in SOCKADDR_IN;
typedef struct sockaddr SOCKADDR; 
#define SOCKET_ERROR (-1)

// Serveur
// Accepte une seule connexion (permet uniquement le monothread)
// rend 0 si pas d'erreur
int CNX_serveur(unsigned short port,int (*cb)(unsigned char msg,SOCKET sock));

// Re�oit des donn�es du client
// Rend 0 si pas d'erreur
int CNX_recoit(SOCKET sock,void* ptr,int nb);

// Envoi des donn�es au client
// Rend 0 si pas d'erreur
int CNX_envoi(SOCKET sock,void* ptr,int nb);

// Envoi un octet au client
// Rend 0 si pas d'erreur
int CNX_envoi_char(SOCKET sock,char val);

// Re�oit un octet du client
// Rend 0 si pas d'erreur
int CNX_recoit_char(SOCKET sock,char* val);

// Re�oit un entier du client
// Rend 0 si pas d'erreur
int CNX_recoit_int(SOCKET sock,int* val);

// Envoi un entier au client
// Rend 0 si pas d'erreur
int CNX_envoi_int(SOCKET sock,int val);

// Etablit une connexion avec un serveur
// rend NULL si erreur
SOCKET CNX_connecte(char* ip,unsigned short port);

// Ferme la connexion avec un serveur
// Rend 0 si pas d'erreur, SOCKET_ERROR sinon
int CNX_deconnecte(SOCKET sock);

