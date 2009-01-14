#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <string.h>
#include <fcntl.h>


typedef struct
{
	char HouseCode[1];
	char DeviceCode[2];
	int equivalence[4];
	int valeur;
}PROTOCOLE;

PROTOCOLE proto[16] = {{"A","01",{0,1,1,0},6},{"B","02",{1,1,1,0},14},{"C","03",{0,0,1,0},2},{"D","04",{1,0,1,0},10},{"E","05",{0,0,0,1},1},{"F","06",{1,0,0,1},9},{"G","07",{0,1,0,1},5},{"H","08",{1,1,0,1},13},{"I","09",{0,1,1,1},7},{"J","10",{1,1,1,1},15},{"K","11",{0,0,1,1},3},{"L","12",{1,0,1,1},11},{"M","13",{0,0,0,0},0},{"N","14",{1,0,0,0},8},{"O","15",{0,1,0,0},4},{"P","16",{1,1,0,0},12}};

void int2hex (char* resultat,int valeur);
