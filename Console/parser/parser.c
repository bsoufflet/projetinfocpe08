#include "parser.h"

void int2hex(char* resultat,int valeur)
{
	printf ("int2hex valeur recue %d\n", valeur);
	sprintf (resultat, "%x", valeur);
}


int main()
{
	int fd;	// pour la liaison serie
	time_t t;
	FILE * file;
	char buffer[20],temps[20],adresse_house[20],adresse_device[20],ordre[20];

	char envoi_serie1[3], envoi_serie2[3], recoit_serie;	// ce que l'on envoi sur la laison série
	char tmp;
	int TMP[7];
	int i;
	char checksum;
	TMP[3]=4;	//header envoi adresses
	TMP[4]=0;	// acquittement bon checksum
	TMP[5]=6;	// header pour la fonction
	memcpy (&envoi_serie1[2], &TMP[4], 1);
	printf ("dans parser\n");
	
	if ((fd=open("/dev/ttyS1",O_RDWR)) <0)	// ouverture de la liason serie sur le port COM 2!!!! (COM1 = console)
	{
		perror ("open");
		printf("Error opening RS232\n");
		return (-1);
	}

	// action parser du fichier
	if((file = fopen("ordres.txt", "r")) != NULL) //on ouvre le fichier et verifie si on y arrive
	{
		while(!feof(file))
		{
			
			/* lecture ligne par ligne et recuperation des informations */
		//	printf("lecture d'une ligne dans le fichier \n");
			fgets(buffer, 20, file);
			printf("contenu du buffer : %s\n", buffer); // affichage du contenu du buffer
				
			strncpy(temps, buffer, 10);
			printf("temps : %s\n", temps);
			
			strncpy(adresse_house, buffer+11, 1);
			printf("adresse house : %s\n", adresse_house);
			for (i=0;i<16; i++)
			{
			//	printf ("i=%d, ad house=%c, prot house=%c\n", i,adresse_house[0],proto[i].HouseCode[0]); 
				if (adresse_house[0] == proto[i].HouseCode[0]) // on cherhce la correspondance entre le fichier et le tableau
				{
					TMP[0]=proto[i].valeur;
					printf ("correspondance trouvee %d\n", TMP[0]);
				}
			}
			
			strncpy(adresse_device, buffer+12, 2);
			printf("adresse device : %s\n", adresse_device);
			for (i=0;i<16; i++)
			{
				//printf ("i=%d, ad device0=%c, prot device=%c, ad device1=%c, prot device=%c\n", i,adresse_device[0],proto[i].DeviceCode[0],adresse_device[1],proto[i].DeviceCode[1]); 
				if ((adresse_device[0]==proto[i].DeviceCode[0]) && (adresse_device[1]==proto[i].DeviceCode[1])) //on cherhce la correspondance entre le fichier et le tableau
				{
					TMP[1]=proto[i].valeur;
					printf ("correspondance trouvee %d\n", TMP[1]);
				}
			}
			
			strncpy(ordre, buffer+15, 1);
			printf("ordre : %s\n", ordre);
			
			TMP[2] = 16*TMP[0]+TMP[1];
			printf ("tmp[2]: %d\n", TMP[2]);
			memcpy(&envoi_serie1[0], &TMP[3], 1);
			memcpy(&envoi_serie1[1], &TMP[2], 1);
			printf ("envoye sur serie: %x %x\n", envoi_serie1[0], envoi_serie1[1]);

			checksum = ((envoi_serie1[0]+envoi_serie1[1])& 0xff);
			// a chaque ligne lu, envoi sur la liaison série
			do
			{
				printf ("dans do\n");
				write (fd, envoi_serie1, 2);	//envoi de l'entete adresse
			//	write (fd, &envoi_serie[1], 1);	//envoi de HouseCode et DeviceCode
				printf ("apres write\n");
				read (fd, &recoit_serie, 1);	// lecture liaison serie pour la checksum
				printf ("adresse recoit_serie = %x checksum = %x\n", recoit_serie, checksum);
			
			}while(recoit_serie != checksum);	// tant que la checksum est incorrect on renvoi l'adresse et le header
			write (fd, &envoi_serie1[2], 1);	// on envoi OxOO au peripherique
			read (fd, &recoit_serie, 1);	// on lit 0x55
			printf ("recoit_serie = %x \n", recoit_serie);
			memcpy (&envoi_serie2[0], &TMP[5], 1);

			if (ordre[0] == '1')	// lampe on
				TMP[7] = 16*TMP[0]+2;
			if (ordre[0] == '0')	// lampe off
				TMP[7] = 16*TMP[0]+3;

			memcpy(&envoi_serie2[1], &TMP[7], 1);
			checksum = ((envoi_serie2[0]+envoi_serie2[1])& 0xff);
			do
			{
				write (fd, envoi_serie2, 2);	//envoi du header pour la fonction
			//	write (fd, &envoi_serie[1], 1);	//envoi de la fonction
				read (fd, &recoit_serie, 1);	// lecture liaison serie
				printf ("fonction recoit_serie = %x checksum = %x\n", recoit_serie, checksum);
			
			}while(recoit_serie != checksum);
			write (fd, &envoi_serie1[2], 1);	// envoi du 0x00
			read (fd, &recoit_serie, 1);
			
		}
		
		fclose(file);
	}
	_exit(3);
	//return 0;
}

/*
int valeur = 6328900;
char Resultat[4];
memcpy(Resultat, &valeur, 4);

itoa (TMP[2],envoi_serie[1], 16);
*/
