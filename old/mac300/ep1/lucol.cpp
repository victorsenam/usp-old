#ifndef LUCOL_CPP
#define LUCOL_CPP

#include "lucol.h"

int lucol (int n, double A[][nmax], int p[]) {
    clock_t beg = clock();
    for (int i = 0; i < n; i++)
        p[i] = i;

    for (int k = 0; k < n; k++) {
        int maxi = k;
        for (int i = k+1; i < n; i++) {
            if (std::abs(A[i][k]) > std::abs(A[maxi][k]))
                maxi = i;
        }

        p[k] = maxi;
        for (int j = 0; j < n; j++)
            std::swap(A[k][j], A[maxi][j]);

        if (A[k][k] == 0)
            return -1;

        for (int i = k+1; i < n; i++) {
            A[i][k] /= A[k][k];
            for (int j = k+1; j < n; j++)
                A[i][j] -= A[i][k]*A[k][j];
        }
    }
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

int sscol (int n, double A[][nmax], int p[], double b[]) {
    clock_t beg = clock();
    // b = Pb       
    for (int i = 0; i < n; i++)
        std::swap(b[i], b[p[i]]);

    // Ly = Pb
    for (int i = 0; i < n; i++)
        for (int j = 0; j < i; j++)
            b[i] -= b[j]*A[i][j];

    // Ux = y
    for (int i = n-1; i >= 0; i--) {
        for (int j = i+1; j < n; j++)
            b[i] -= b[j]*A[i][j];

        if (A[i][i] == 0)
            return -1;

        b[i] /= A[i][i];
    }
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

#endif
