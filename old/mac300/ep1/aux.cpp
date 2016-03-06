#ifndef AUX_CPP
#define AUX_CPP

#include "aux.h"

void printmat (int n, double A[][nmax], double (*toprint)(int, int, double A[][nmax])) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++)
            printf("%lf ", (*toprint)(i, j, A));
        printf("\n");
    }
}

void printvect (int n, double b[], double (*toprint)(int, double b[])) {
    for (int i = 0; i < n; i++)
        printf("%lf ", (*toprint)(i, b));
    printf("\n");
}

void testfail (int flag, int type) {
    if (flag) {
        if (type == 1)
            printf("A matriz não é definida positiva\n");
        else
            printf("A matriz é não singular\n");
        exit(flag);
    }
}

#endif
