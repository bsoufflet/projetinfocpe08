
#makefile pour le projet SID RS232

INCLUDEDIR_DISTR_ = /home/marina/marina/ETI3/projet/dev/uClinux/uClinux-pragm-20070119
UCLIBC_DIR = $(INCLUDEDIR_DISTR_)/uClibc
INCLUDEDIR_LINUX_ = $(INCLUDEDIR_DISTR_)/linux-2.4/include
INCLUDEDIR_UCLIBC = $(UCLIBC_DIR)/include
RUNTIME = $(UCLIBC_DIR)/lib/crt0.o $(UCLIBC_DIR)/lib/crti.o $(UCLIBC_DIR)/lib/crtn.o

CC = arm-elf-gcc

CFLAGS = -g -Dlinux -D__linux__ -Dunix -D__uClinux__ -DEMBED -I$(INCLUDEDIR_UCLIBC) -I$(INCLUDEDIR_DISTR_) -fno-builtin -nostartfiles -I$(INCLUDEDIR_LINUX_) -I.

LDFLAGS = -L$(UCLIBC_DIR)/. -L$(UCLIBC_DIR)/lib

SRCt_WR = carte_test.c
OBJt_WR = carte_test.o
SRC_WR = CARTE_CNX.c
OBJ_WR = CARTE_CNX.o

all :
	$(CC) $(CFLAGS) -c -o $(OBJt_WR) $(SRCt_WR) 	
	$(CC) $(CFLAGS) -c -o $(OBJ_WR) $(SRC_WR)
	$(CC) $(CFLAGS) -Wl,-elf2flt $(RUNTIME) $(LDFLAGS) -o test $(OBJt_WR) $(OBJ_WR) -lc


clean :
	rm -f *.o *.gdb test
