#ifndef CHOLCOL_CPP
#define CHOLCOL_CPP

#include "cholcol.h"

int cholcol (int n, double A[][nmax]) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < j; k++)
                A[i][j] -= A[i][k]*A[j][k];
            A[i][j] /= A[j][j];

            A[i][i] -= A[i][j];
        }
        
        if (A[i][i] <= 0.0)
            return -1;
        A[i][i] = sqrt(A[i][i]);

    }
    return 0;
}

int forwcol (int n, double A[][nmax], double b[]) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++)
            b[i] -= A[i][j]*b[j];
        
        if (A[i][i] == 0)
            return -1;

        b[i] /= A[i][i];
    }
	printf("fim forwcol\n");
    return 0;
}

int backcol (int n, double A[][nmax], double b[], int trans) {
    for (int i = n-1; i >= 0; i--) {
        for (int j = i+1; j < n; j++)
            b[i] -= trans?A[j][i]:A[i][j];

        if (A[i][i] == 0) {
			printf("%d %d é %lf\n", i, i, A[i][i]);
            return -1;
		}

        b[i] /= A[i][i];
    }
	printf("fim backcol\n");
    return 0;
}

#endif
