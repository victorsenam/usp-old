#ifndef CHOLROW_H
#define CHOLROW_H

#include "headers.h"

int cholrow (int n, double A[][nmax]);
int forwrow (int n, double A[][nmax], double b[]);
int backrow (int n, double A[][nmax], double b[], int trans);

#include "cholrow.cpp"

#endif
