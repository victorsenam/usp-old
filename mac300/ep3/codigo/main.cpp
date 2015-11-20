#include <cstdio>
#include <stdio.h>

void recebe_entrada (int * n, int * m, double ** A, double * b) {
    int i;              // iterador
    int x, y;           // posicoes da entrada
    scanf("%d %d", n, m);
    
    // alocando memória
    A = (double **) malloc(sizeof(double *)*(*n));
    for (i = 0; i < *n; i++)
        A[i] = (double *) malloc(sizeof(double)*(*m));
    b = (double *) malloc(sizeof(double)*(*n));

    // A
    for (i = 0; i < (*n)*(*m); i++) {
        scanf("%d %d", x, y);
        scanf("%lf", &A[x][y]);
    }

    // b
    for (i = 0; i < (*n); i++)
        scanf("%lf", b+i);
}

void aloca_auxiliares (int m, int * p, double * norma, double * escala, double * gamma) {
    p = (int *) malloc(sizeof(int *)*(*m));   
    norma = (double *) malloc(sizeof(double *)*(*m));   
    escala = (double *) malloc(sizeof(double *)*(*m));   
    gamma = (double *) malloc(sizeof(double *)*(*m));   
}

void pre_processa (int n, int m, double ** A, int * p, double * norma, double * escala) {
    // inicializando
    for (int i = 0; i < m; i++)
        p[i] = i;
    for (int i = 0; i < m; i++)
        norma[i] = escala[i] = 0;

    // calculando norma maxima de cada coluna
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++)
            escala[j] = std::max(escala[j], std::abs(A[i][j]));
    }

    // re-escalando as colunas enquanto calcula norma dois ao quadrado
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            A[i][j] /= escala[j];
            norma[j] += A[i][j]*A[i][j];
        }
    }
}

int decompoe_qr (int n, int m, double ** A, int * p, double * norma, double * escala, double * gamma) {
    int posto = 0;

    for (int k = 0; k < m; k++) {
        // buscando a coluna de maior norma
        int maxi = k;
        for (int i = k; i < m; i++)
            if (std::abs(norma[p[i]]) > std::abs(norma[p[maxi]])
                maxi = i;

        swap(p[k], p[maxi]);
        
        if (A[k][p[k]] > 0)
            A[k][p[k]] += norma[p[k]];
        else
            A[k][p[k]] -= norma[p[k]];
        gamma[p[k]] = A[k][p[k]]*A[k][p[k]];

        // colocando u na coluna k
        for (int i = k+1; i < n; i++)
            A[i][p[k]] /= A[k][p[k]];

        
    }
}

int main () {
    int n, m;           // quantidade de linhas e colunas, respectivamente
    double ** A;        // matriz A de entrada (nm)
    double * b;         // vetor b de entrada (n)
    int * p;            // vetor de permutação de colunas (m)
    double * norma;     // quadrado das normas 2 das colunas (m)
    double * escala;    // norma infinita das colunas (m)
    double * gamma;     // gammas (m)
    int posto;          // posto de A

    recebe_entrada(&n, &m, A, b);
    aloca_auxiliares(m, p, norma, escala, gamma);
    pre_processa(n, m, A, p, norma, escala);
    posto = decompoe_qr(n, m, A, b, p, norma, escala, gamma);
}
