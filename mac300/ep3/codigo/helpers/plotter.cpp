#include <bits/stdc++.h>

using namespace std;

int m;
double xl, xr;
double delta;
int qt;
double ** q;
double * p;
double lx, rx;

double aval(int i, double x) {
    double val = 0.0;
    double t = 1.0;
    for (int j = 0; j < m; j++) {
        val += q[i][j]*t;
        t *= x;
    }
    return val;
}

double calc(double x) {
    double val = 0.0;
    for (int i = 0; i < m; i++)
        val += p[i]*aval(i, x);
    return val;
}

int main () {
    scanf("%d", &m);

    p = (double *) malloc(sizeof(double)*m);
    q = (double **) malloc(sizeof(double *)*m);
    for (int i = 0; i < m; i++)
        q[i] = (double *) malloc(sizeof(double)*m);

    scanf("%lf %lf %d", &lx, &rx, &qt);
    delta = (rx-lx)/qt;

    for (int i = 0; i < m; i++) {
        scanf("%lf", p+i);
    }

    double t = lx;
    for (int i = 0; i < qt; i++) {
        printf("%f,%f\n", t, calc(t));
        t += delta;
    }

    free(p);
    for (int i = 0; i < m; i++)
        free(q[i]);
    free(q);
}
