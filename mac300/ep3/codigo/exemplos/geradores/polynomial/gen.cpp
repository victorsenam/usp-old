/*
* f_i é um polinômio de grau m tal que f_i(t) = \sum_{j=1}^{m} q_{i,j}*t^j
* g é o polinomio a ser interpolado tal que g(t) = \sum_{i=1}^{m} p_{i}*f_i(t)
* ou seja, os f foram uma base do polinomio g a ser interpolado
* t_i são os pontos a serem dados como entrada para podermos interpolar o polinomio
*
* Gera uma matriz A nxm tal que a_{i,j} = f_j(t_i)
* Gera um vetor b nxm b_{i} = g(t_i) + e, onde e é um erro gerado com desvio de acordo com a normal padrao sigma
*
* A ideia é que A*p = b-e, o programa deve calcular uma resposta próxima de p
*/

/* ENTRADA:
 * - Dois inteiros, n e m, respectivamente linhas e colunas da matriz
 * - Um real, sigma
 * - m linhas tal que a i-ésima linha contem m reais e o j-ésimo real é q_{i,j}
 * - uma linha contendo m reais p_i
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
double * y;                 // pontos calculados em g
double ** q;                // coeficientes das bases
double * p;                 // coeficientes do polinomio
double sigma;               // desvio padrao do erro

double aval(int i, double x) {
    double val = 0.0;
    double t = 1.0;
    for (int j = 0; j < m; j++) {
        val += q[i][j]*t;
        t *= x;
    }
    return val;
}

int main (int argc, char * argv[]) {
    FILE * baseout = fopen(argv[1], "w");
    FILE * pointsout = fopen(argv[2], "w");
    scanf("%d %d", &n, &m);
    fprintf(baseout, "%d\n", m);

    y = (double *) malloc(sizeof(double)*n);
    p = (double *) malloc(sizeof(double)*m);
    q = (double **) malloc(sizeof(double *)*m);
    for (int i = 0; i < m; i++)
        q[i] = (double *) malloc(sizeof(double)*m);

    scanf("%lf", &sigma);

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < m; j++) {
            scanf("%lf", &q[i][j]);
            fprintf(baseout, "%f ", q[i][j]);
        }
        fprintf(baseout, "\n");
    }

    for (int i = 0; i < m; i++) {
        scanf("%lf", p+i);
    }

    default_random_engine gerador;
    normal_distribution<double> normal(0.0, sigma);
    
    printf("%d %d\n", n, m);
    double mini, maxi;
    for (int i = 0; i < n; i++) {
        double t;
        scanf("%lf", &t);
        if (i) {
            mini = min(mini, t);
            maxi = max(maxi, t);
        } else {
            mini = maxi = t;
        }
        y[i] = 0.0;

        for (int j = 0; j < m; j++) {
            double val = aval(j, t);
            printf("%d %d %f\n", i, j, val);
            y[i] += p[j]*val;
        }

        y[i] += normal(gerador);
        fprintf(pointsout, "%f,%f\n", t, y[i]);
    }

    fprintf(baseout, "%f %f %d\n", mini-((maxi-mini)/10.0), maxi+((maxi-mini)/10.0), 1000);

    for (int i = 0; i < n; i++)
        printf("%f ", y[i]);
    printf("\n");

    free(y);
    free(p);
    for (int i = 0; i < m; i++)
        free(q[i]);
    free(q);
}
