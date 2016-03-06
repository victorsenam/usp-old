#ifndef AUX_H
#define AUX_H

#include "headers.cpp"

// Funções Auxiliares para imprimir matrizes e vetores
void printmat (int n, double A[][nmax], double (*toprint)(int, int, double A[][nmax]));
void printvect (int n, double b[], double (*toprint)(int, double b[]));
void testfail (int flag, int type);

#include "aux.cpp"

#endif
