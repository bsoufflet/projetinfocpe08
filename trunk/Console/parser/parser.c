#include "parser.h"
#include "x10.h"


int main()
{
	int fd;	// pour la liaison serie
	time_t t;
	FILE * file;
	char buffer[20],temps[20],adresse_house[20],adresse_device[20],ordre[20];
	int retour;
	unsigned char adresse;
	int TMP[2];
	int i;

	printf ("dans parser toto\n");
	
// ouverture de la liason serie sur le port COM 2 de la carte!!!! (COM1 = console)
	
	retour = connexion (&fd);	// connexion de la liaison serie
	if (retour != 0)
	{
		printf ("erreur ouverture port serie\n");
		_exit (3);
	}
	printf ("fd = %d\n", fd);
	retour = parametrage (fd, B4800);	// parametrage de la vitesse du port serie 4800 baud pour cette com (d'autre parametres sont en dur dans la fonction)
	if (retour != 0)
	{
		printf ("erreur parametrage port serie\n");
		deconnexion (fd);	// fermeture du port serie
		_exit (3);
	}
	printf ("port serie correctement ouvert et parametre\n");
	// fin reglage liaison serie
	
	// reglage de l'heure de l'interface CM11 pour eviter le message d'erreur A5 en boucle qui empeche la com
	retour = set_clock (fd);
	if (retour != 0)
	{
		printf ("set_clock: erreur set_clock\n");
		deconnexion (fd);	// fermeture du port serie
		_exit (3);
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
			TMP[1] = 16*TMP[0]+TMP[1];
			memcpy(&adresse, &TMP[1], 1);
			
			retour = adresse_transmit (fd, adresse);
			if (retour != 0)
			{
				printf ("erreur transmit adresse\n");
				fclose(file);
				deconnexion (fd);	// fermeture du port serie
				_exit (3);
			}
			if (ordre[0] == '0')	//lampe off
			{
				retour = prise_off (fd, TMP[0]);
				if (retour != 0)
				{
					printf ("erreur prise off\n");
					fclose(file);
					deconnexion (fd);	// fermeture du port serie
					_exit (3);
				}
			}
			else if (ordre[0] == '1')
			{
				retour = prise_on (fd, TMP[0]);
				if (retour != 0)
				{
					printf ("erreur prise on\n");
					fclose(file);
					deconnexion (fd);	// fermeture du port serie
					_exit (3);
				}
			}
		}

		fclose(file);
	}
	deconnexion (fd);
	_exit(2);
	//return 0;
}