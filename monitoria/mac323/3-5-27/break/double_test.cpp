#include <bits/stdc++.h>

using namespace std;

int n;
double l, r;

int main () {
    scanf("%lf %lf", &l, &r);

    while (l < r) {
        l = (l+r)*0.5l;
        n++;
    }

    printf("%d\n", n);

}
