#include <bits/stdc++.h>

using namespace std;

const int nmax = 700;

int main () {
    int n;
    double A[nmax][nmax];

    scanf("%d", &n);
    for (int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            scanf("%lf", &A[i][j]);

    printf("%d\n", n);
    for (int i = 0; i < n; i++){ 
        for(int j = 0; j < n; j++) {
            printf("%d %d %lf\n", i, j, A[i][j]);
        }
    }

    for (int i = 0; i < n; i++) {
        scanf("%lf", &A[0][0]);
        printf("%lf ", A[0][0]);
    }
}
