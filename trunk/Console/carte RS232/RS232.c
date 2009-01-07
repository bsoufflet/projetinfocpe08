#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char**argv) 
{
	unsigned char data [10];
	int fd,ret, i, j, n;
	char RS232_device[255] = "/dev/ttyS1";

	printf("coucou\n");
	
	if ((fd=open("/dev/ttyS1",O_RDWR)) <0)
	{
		perror ("open");
		printf("Error opening RS232\n");
		return (-1);
	}
	
//	printf ("\n---Test d'ecriture RS232---\n\n" );
//	sscanf (argv [2 ], "%s", &n);
//	ret = ioctl(fd, RS232_SLAVE, n);
	
//	if (ret<0) 
//	{
//		perror ("RS232_SLAVE ioctl cmd");
//		close (fd);
//		return (-1);
//	}


	write (fd, "toto", 4);

	
/*	for (i=0, j=0; i<sizeof (tab) ,i++) 
	{
		data[0] = 0x02;
		data[1] = tab[i];
		// printf("Mise a ON des segments (0MCP23076:1xqx ,/ Aeg= 0x202x /
		// Data: 0x%02x) \n", n, data [0] , dat� [7] ) ;
		write (fd, &data[0], 2);
		if (i==9) 
		{ .
			i=0;
			j++;
			data[0] = 0x03;
			data[l] = tab[j];
			write (fd, &data[0], 2);
		}
		if ((i == 6) && (j == 4) ) break;
		usleep (50000) , .
	}*/
	close (fd);
	return 0;
}