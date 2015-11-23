#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <cmath>

const double eps = 1e-9;

void recebe_entrada (int * n, int * m, double ** & A, double * & b) {
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
        scanf("%d %d", &x, &y);
        scanf("%lf", &A[x][y]);
    }

    // b
    for (i = 0; i < (*n); i++)
        scanf("%lf", b+i);
}

void aloca_auxiliares (int m, int * & p, double * & norma, double * & gamma) {
    p = (int *) malloc(sizeof(int)*(m));   
    norma = (double *) malloc(sizeof(double)*(m));
    gamma = (double *) malloc(sizeof(double)*(m));   
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
    return escala;
}

int decompoe_qr (int n, int m, double ** A, int * p, double * norma, double * gamma, double escala) {
    for (int k = 0; k < m; k++) {
        // pivoteamento de colunas
        int maxi = k;
        for (int i = k; i < m; i++)
            if (norma[i] > norma[maxi])
                maxi = i;

        if (norma[maxi] < eps)
            return k;
        
        if (maxi != k) {
            for (int i = 0; i < n; i++)
                std::swap(A[i][k], A[i][maxi]);
            std::swap(norma[k], norma[maxi]);
        }
        p[k] = maxi;
        
        // define o sinal de tau
        double tau = sqrt(norma[k]);
        if (A[k][k] < 0)
            tau = -tau;

        // calcula u
        A[k][k] += tau;
        gamma[k] = A[k][k]/tau;
        for (int i = k+1; i < n; i++)
            A[i][k] /= A[k][k];
        A[k][k] = 1.0;

        // atualiza o restante de A usando gamma como vetor auxiliar
        for (int j = k+1; j < m; j++)
            gamma[j] = 0;

        for (int i = k; i < n; i++)
            for (int j = k+1; j < m; j++)
                gamma[j] += gamma[k]*A[i][k]*A[i][j];
        
        for (int i = k; i < n; i++)
            for (int j = k+1; j < m; j++)
                A[i][j] -= A[i][k]*gamma[j];

        // atualiza o vetor de normas para a proxima iteração
        for (int j = k+1; j < m; j++)
            norma[j] -= A[k][j]*A[k][j];
        
        // coloca o -tau na posiçao certa
        A[k][k] = -tau;
    }

    // reescala o resultado
    for (int i = 0; i < n; i++)
        for (int j = i; j < m; j++)
            A[i][j] *= escala;

    return m;
}

void aplica_decomposicao(int n, int m, int posto, double ** A, double * b, int * p, double * gamma) {
    // aplica Q^T em b
    for (int k = 0; k < posto; k++) {
        double val = b[k]*gamma[k];
        for (int i = k+1; i < n; i++) {
            val += gamma[k]*A[i][k]*b[i];
        }

        b[k] -= val;
        for (int i = k+1; i < n; i++)
            b[i] -= A[i][k]*val;
    }
    

    // calcula x tal que Rx = Q^Tb
    for (int k = posto-1; k >= 0; k--) {
        for (int j = k+1; j < posto; j++)
            b[k] -= A[k][j]*b[j];

        b[k] /= A[k][k];
    }
    for (int i = posto; i < m; i++)
        b[i] = 0;

    // permuta x de acordo
    for (int i = posto-1; i >= 0; i--)
        std::swap(b[i], b[p[i]]);
}

void imprime_resposta(int n, double * x) {
    for (int i = 0; i < n; i++)
        printf("%f\n", x[i]);
}

void libera_memoria(int n, double ** A, double * b, double * norma, double * gamma, int * p) {
    for (int i = 0; i < n; i++)
        free(A[i]);
    free(A);
    free(b);
    free(norma);
    free(gamma);
    free(p);
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
    posto = decompoe_qr(n, m, A, p, norma, gamma, escala);
    aplica_decomposicao(n, m, posto, A, b, p, gamma);
    imprime_resposta(m, b);
    libera_memoria(n, A, b, norma, gamma, p);
}
