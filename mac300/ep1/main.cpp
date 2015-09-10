#include <cstdio>

const int nmax = 700;

int chocol (int n, double A[][nmax]);
int forwcol (int n, double A[][nmax], double b[]);
int backcol (int n, double A[][nmax], double b[], int trans);

int cholrow (int n, double A[][nmax]);
int forwrow (int n, double A[][nmax], double b[]);
int backrow (int n, double A[][nmax], double b[], int trans);

int lucol (int n, double A[][nmax], int p[]);
int sscol (int n, double A[][nmax], int p[], double b[]);

int lurow (int n, double A[][nmax], int p[]);
int ssrow (int n, double A[][nmax], int p[], double b[]);

int main () {
    int n;
    double A[nmax][nmax];
    double b[nmax];

    scanf("%d", &n);
    for 
}
