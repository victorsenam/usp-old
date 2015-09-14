#ifndef LUROW_CPP
#define LUROW_CPP

#include "lurow.h"

int lurow (int n, double A[][nmax], int p[]) {
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

        for (int i = k+1; i < n; i++)
            A[i][k] /= A[k][k];

        for (int j = k+1; j < n; j++) {
            for (int i = k+1; i < n; i++)
                A[i][j] -= A[i][k]*A[k][j];
        }
    }
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

int ssrow (int n, double A[][nmax], int p[], double b[]) {
    clock_t beg = clock();
    // b = Pb
    for (int i = 0; i < n; i++)
        std::swap(b[i], b[p[i]]);

    // Ly = Pb
    for (int j = 0; j < n; j++)
        for (int i = j+1; i < n; i++)
            b[i] -= b[j]*A[i][j];

    // Ux = y
    for (int j = n-1; j >= 0; j--) {
        if (A[j][j] == 0)
            return -1;

        b[j] /= A[j][j];

        for (int i = j-1; i >= 0; i--)
            b[i] -= b[j]*A[i][j];
    }
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

#endif
