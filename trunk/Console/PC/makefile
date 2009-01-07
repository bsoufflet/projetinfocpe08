#makefile

all: test.o PC_CNX.o
	gcc PC_CNX.o test.o -o test_PC

test.o: test.c
	gcc -c test.c

PC_CNX.o: PC_CNX.c
	gcc -c PC_CNX.c

clean:
	rm -f *.o *~