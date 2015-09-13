#ifndef LUCOL_CPP
#define LUCOL_CPP

#include "lucol.h"

int lucol (int n, double A[][nmax], int p[]) {
    for (int i = 0; i < n; i++)
        p[i] = i;

    for (int k = 0; k < n; k++) {
        int maxi = k;
        for (int i = k+1; i < n; i++) {
            if (std::abs(A[p[i]][k]) > std::abs(A[p[maxi]][k]))
                maxi = i;
        }

        std::swap(p[k], p[maxi]);
        if (A[p[k]][k] == 0)
            return -1;

        for (int i = k+1; i < n; i++) {
            A[p[i]][k] = (A[p[i]][k]/A[p[k]][k]);
            for (int j = k+1; j < n; j++)
                A[p[i]][j] -= A[p[i]][k]*A[p[k]][j];
        }
    }
    return 0;
}

int sscol (int n, double A[][nmax], int p[], double b[]) {
    // b = Pb       
    for (int i = 0; i < n; i++) {
        int nx = i;
        double att = b[i];
        while (p[nx] < n) {
            p[nx] += n;
            nx = p[nx] - n;   
            std::swap(att, b[nx]);
        }
    }
    
    for (int i = 0; i < n; i++)
        p[i] -= n;


    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++)
            b[i] -= b[j]*A[p[i]][j];

        if (A[p[i]][i] == 0)
            return -1;

        b[i] /= A[p[i]][i];
    }

    for (int i = n-1; i >= 0; i--) {
        for (int j = i+1; j < n; j++)
            b[i] -= b[j]*A[p[i]][j];

        if (A[p[i]][i] == 0)
            return -1;

        b[i] /= A[p[i]][i];
    }
    return 0;
}

#endif
