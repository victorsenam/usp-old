#include <cstdio>

const double eps = 1e-9;

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

void aloca_auxiliares (int m, int * p, double * norma, double * gamma) {
    p = (int *) malloc(sizeof(int *)*(*m));   
    norma = (double *) malloc(sizeof(double *)*(*m));   
    gamma = (double *) malloc(sizeof(double *)*(*m));   
}

double pre_processa (int n, int m, double ** A, int * p, double * norma) {
    // inicializando
    for (int i = 0; i < m; i++)
        p[i] = i;
    for (int i = 0; i < m; i++)
        norma[i] = 0;
    double escala = 0;

    // calculando o módulo máximo
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            escala = std::max(escala, std::abs(A[i][j]));

    // re-escalando as colunas enquanto calcula norma dois ao quadrado
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            A[i][j] /= escala;
            norma[j] += A[i][j]*A[i][j];
        }
    }
}

int decompoe_qr (int n, int m, double ** A, int * p, double * norma, double * gamma) {
    double aux = (double *) malloc(sizeof(double)*n);

    for (int k = 0; k < m; k++) {
        // buscando a coluna de maior norma
        int maxi = k;
        for (int i = k; i < m; i++)
            if (norma[p[i]] > norma[p[maxi]])
                maxi = i;

        if (norma[maxi] < eps)
            return k;
        
        for (int i = 0; i < n; i++)
            swap(A[i][k], A[i][maxi]);
        swap(norma[k], norma[maxi]);
        p[k] = maxi;
        
        double tau = norma[k];
        if (A[k][k] < 0)
            tau = -tau;

        gamma[k] = (A[k][k] + tau)/tau;
        for (int i = k; i < n; i++)
            A[i][k] /= A[k][k]+tau;

        for (int i = k+1; i < m; i++)
            gamma[i] = 0;

        for (int i = k+1; i < n; i++)
            for (int j = k+1; j < m; j++)
                gamma[j] += aux[i]*gamma[k]*A[i][j];

        for (int i = k+1; i < n; i++)
            for (int j = k+1; j < m; j++)
                A[i][j] -= A[i][k]*gamma[j];

        A[k][k] = -tau;
    }

    return m;
}

int main () {
    int n, m;           // quantidade de linhas e colunas, respectivamente
    double ** A;        // matriz A de entrada (nm)
    double * b;         // vetor b de entrada (n)
    int * p;            // vetor de permutação de colunas (m)
    double * norma;     // quadrado das normas 2 das colunas (m)
    double * gamma;     // gammas (m)
    int posto;          // posto de A
    double escala;      // escalamento inicial do problema

    recebe_entrada(&n, &m, A, b);
    aloca_auxiliares(m, p, norma, gamma);
    escala = pre_processa(n, m, A, p, norma);
    posto = decompoe_qr(n, m, A, b, p, norma, gamma);

    printf("%lf %d\n", escala, posto);
}
