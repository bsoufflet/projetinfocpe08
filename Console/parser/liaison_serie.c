#include "liaison_serie.h"


int connexion (int* fd)
{
	if ((*fd=open("/dev/ttyS1",O_RDWR | O_NONBLOCK)) <0)	
	{
		perror ("open");
		printf("Error opening RS232\n");
		return 1;;
	}
	return 0;
}

//******************************************************************
void setspeed (struct termios * config, speed_t vitesse)
{
   cfsetispeed (config, vitesse);
   cfsetospeed (config, vitesse);
}

//*****************************************************************
int parametrage (int fd, speed_t vitesse)
{
	struct termios my_termios;
  	struct termios new_termios;
	printf ("dans parametrage fd = %d\n", fd);

	if (tcgetattr (fd, &my_termios) != 0) { // lecture de la configuration du port et sauvegarde de celui ci dans la structure configuration
		perror ("tcgetattr");
		return 1;;
	}
		
	my_termios.c_cflag   &= ~ CLOCAL;
	tcsetattr (fd, TCSANOW, &my_termios);
	
	tcgetattr (fd, &my_termios);
	memcpy (&new_termios, &my_termios, sizeof (struct termios));
	cfmakeraw (&my_termios);
	setspeed (&my_termios, vitesse);   	// vitesse 4800 bits/s
//	my_termios.c_cflag &= ~ PARENB;		//parite
	my_termios.c_cflag &= ~ CSIZE;
	my_termios.c_cflag |= CS8; 		// taille des donne?s 8 bits
	
	if (tcsetattr (fd, TCSANOW, &my_termios) < 0) // application des nouveaux parametres sur notre port serie
	{ 
		perror ("tcsetattr");
		return 1;
	}
	printf ("Port serie parametre \n");
	return 0;
}

//*****************************************************************
int deconnexion(int fd)
{
	close (fd);
}