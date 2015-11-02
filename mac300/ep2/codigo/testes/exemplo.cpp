#include <bits/stdc++.h>

using namespace std;

const double eps = 1e-9;

int main () {
    srand(time(NULL)); rand(); rand();
    int n;
    double tau;
    scanf("%d %lf", &n, &tau);
    printf("%d\n", n);

    for (int i = 0; i < n; i++)
        printf("%d %d %.7f\n", i, i, 1.0);
    
    int cnt = 0;
    for (int i = 1; i < n; i++) {
        for (int j = i+1; j < n; j++) {
            double g = rand(); // D-O-double G!
            g -= .5*RAND_MAX;
            g /= RAND_MAX;
            if (g > tau || g < -tau)
                continue;
            printf("%d %d %.7f\n", i, j, g);
            cnt++;
        }
    }
    printf("%d\n", cnt);
}
