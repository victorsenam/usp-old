#ifndef CHOLCOL_H
#define CHOLCOL_H

#include "headers.h"

int cholcol (int n, double A[][nmax]);
int forwcol (int n, double A[][nmax], double b[]);
int backcol (int n, double A[][nmax], double b[], int trans);

#include "cholcol.cpp"

#endif
