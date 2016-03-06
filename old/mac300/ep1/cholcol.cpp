#ifndef CHOLCOL_CPP
#define CHOLCOL_CPP

#include "cholcol.h"

int cholcol (int n, double A[][nmax]) {
    clock_t beg = clock();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < j; k++)
                A[i][j] -= A[i][k]*A[j][k];

            A[i][j] /= A[j][j];

            A[i][i] -= A[i][j]*A[i][j];
        }
        
        if (A[i][i] <= 0.0)
            return -1;
        A[i][i] = sqrt(A[i][i]);

    }

    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

int forwcol (int n, double A[][nmax], double b[]) {
    clock_t beg = clock();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++)
            b[i] -= A[i][j]*b[j];
        
        if (A[i][i] == 0)
            return -1;

        b[i] /= A[i][i];
    }
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

int backcol (int n, double A[][nmax], double b[], int trans) {
    clock_t beg = clock();
    for (int i = n-1; i >= 0; i--) {
		if (!trans) {
			for (int j = i+1; j < n; j++)
				b[i] -= A[i][j]*b[j];
		}

        if (A[i][i] == 0)
            return -1;

        b[i] /= A[i][i];

		if (trans) {
			for (int j = i-1; j >= 0; j--)
				b[j] -= A[i][j]*b[i];
		}
    }
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

#endif
