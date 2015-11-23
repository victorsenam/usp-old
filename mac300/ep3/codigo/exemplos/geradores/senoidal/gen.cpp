/*
* O objetivo é aproximar uma função senoidal numa base polinomial
* f_i é um polinômio de grau m tal que f_i(t) = \sum_{j=1}^{m} q_{i,j}*t^j
* ou seja, os f foram uma base polinomial
*
* Gera uma matriz A nxm tal que a_{i,j} = f_j(t_i)
* Gera um vetor b nxm b_{i} = sen(coef*t_i)
*
* Deve ser gerado um polinomio que aproxime bem a função sen(coef*t)
*/

/* ENTRADA:
 * - Dois inteiros, n e m, respectivamente linhas e colunas da matriz
 * - Um real, coef
 * - m linhas tal que a i-ésima linha contem m reais e o j-ésimo real é q_{i,j}
 * - uma linha contendo n reais t_i
 *
 * SAÍDA:
 * - n e m
 * - a matriz A dada como nos eps anteriores
 * - o vetor b dado como nos eps anteriores
 */

#include <bits/stdc++.h>

using namespace std;

int n, m;                   // dimensoes
double * t;                 // pontos calculados em g
double ** q;                // coeficientes das bases
double coef;               // desvio padrao do erro

double aval(int i, double x) {
    double val = 0.0;
    double t = 1.0;
    for (int j = 0; j < m; j++) {
        val += q[i][j]*t;
        t *= x;
    }
    return val;
}

int main () {
    scanf("%d %d", &n, &m);

    t = (double *) malloc(sizeof(double)*n);
    q = (double **) malloc(sizeof(double *)*m);
    for (int i = 0; i < m; i++)
        q[i] = (double *) malloc(sizeof(double)*m);

    scanf("%lf", &coef);

    for (int i = 0; i < m; i++)
        for (int j = 0; j < m; j++) {
            scanf("%lf", &q[i][j]);
        }

    printf("%d %d\n", n, m);
    for (int i = 0; i < n; i++) {
        scanf("%lf", t+i);

        for (int j = 0; j < m; j++) {
            double val = aval(j, t[i]);
            printf("%d %d %f\n", i, j, val);
        }
    }

    for (int i = 0; i < n; i++)
        printf("%f ", sin(coef*t[i]));
    printf("\n");

    free(t);
    for(int i = 0; i < m; i++)
        free(q[i]);
    free(q);
}
