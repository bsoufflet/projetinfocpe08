#include <fcntl.h>
#include <termios.h>
#include <stdio.h>
#include <stdlib.h>


int connexion (int* fd);
int parametrage (int fd, speed_t vitesse);
int deconnexion(int fd);