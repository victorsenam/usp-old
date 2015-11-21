#include <bits/stdc++.h>

using namespace std;
int m;

int main () {
    scanf("%d", &m);

    printf("%d 3\n", m);
    for (int i = 0; i < m; i++) {
        double a;
        scanf("%lf", &a);

        printf("%d 0 %f\n", i, 1-a);
        printf("%d 1 %f\n", i, 1+a);
        printf("%d 2 %f\n", i, a*a);
    }

    for (int i = 0; i < m; i++) {
        double a;
        scanf("%lf", &a);
        printf("%f ", a);
    }
    printf("\n");
}
