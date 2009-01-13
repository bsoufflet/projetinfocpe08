#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <fcntl.h>

int main()
{
	int fd;
	printf ("dans parser\n");
	
	if ((fd=open("/dev/ttyS1",O_RDWR)) <0)	// ouverture de la liason serie sur le port COM 2!!!! (COM1 = console)
	{
		perror ("open");
		printf("Error opening RS232\n");
		return (-1);
	}

	// action parser du fichier
	
	
	// a chaque ligne lu, envoi sur la liaison série
	write (fd, "toto papa hihi", sizeof("toto papa hihi"));
	_exit(3);
}
