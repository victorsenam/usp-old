#include <cstdio>

const int nmax = 700;

void printmat (int n, double A[][nmax], bool (*shouldprint)(int, int));

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

void printmat (int n, double A[][nmax], bool (*shouldprint)(int, int));

int main () {
    int n;
    int x, y;
    double A[nmax][nmax];
    double b[nmax];

    scanf("%d", &n);
    for (int i = 0; i < n*n; i++)
        scanf("%d %d %lf", &x, &y, &A[x-1][y-1]);
    for (int i = 0; i < n; i++)
        scanf("%lf", b+i);

    printmat (n, A, ([](int i, int j) -> bool { return i < j; }));
}
