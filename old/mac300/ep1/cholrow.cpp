#ifndef CHOLROW_CPP
#define CHOLROW_CPP

#include "cholrow.h"

int cholrow (int n, double A[][nmax]) {
    clock_t beg = clock();
	for (int j = 0; j < n; j++) {
		if (A[j][j] <= 0)
			return -1;

		A[j][j] = sqrt(A[j][j]);
        

        for (int i = j+1; i < n; i++)
            A[i][j] /= A[j][j];

        for (int k = j+1; k < n; k++)
            for (int i = k; i < n; i++)
                A[i][k] -= A[i][j]*A[k][j];
	}
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

int forwrow (int n, double A[][nmax], double b[]) {
    clock_t beg = clock();
    for (int j = 0; j < n; j++) {
        if (A[j][j] == 0)
            return -1;
        b[j] /= A[j][j];
        for (int i = j+1; i < n; i++)
            b[i] -= A[i][j]*b[j];
    }
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

int backrow (int n, double A[][nmax], double b[], int trans) {
    clock_t beg = clock();
    for (int j = n-1; j >= 0; j--) {
        if (trans) {
            for (int i = j+1; i < n; i++)
                b[j] -= A[i][j]*b[i];
        }

        if (A[j][j] == 0)
            return -1;

        b[j] /= A[j][j];

        if (!trans) {
            for (int i = j-1; i >= 0; i--)
                b[i] -= A[i][j]*b[j];
        }
    }
    printf("%.2f\n", (double)((clock()-beg))/(double)(CLOCKS_PER_SEC));
    return 0;
}

#endif
