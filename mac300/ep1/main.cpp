#include <cstdio>
#include <cstring>
#include <cmath>

const int nmax = 700;

// Funções Auxiliares para imprimir matrizes e vetores
void printmat (int n, double A[][nmax], bool (*shouldprint)(int, int, double A[][nmax]));
void printvect (int n, double b[nmax], bool (*shouldprint)(int, double b[]));

int cholcol (int n, double A[][nmax]);
int forwcol (int n, double A[][nmax], double b[]);
int backcol (int n, double A[][nmax], double b[], int trans);

int cholrow (int n, double A[][nmax]);
int forwrow (int n, double A[][nmax], double b[]);
int backrow (int n, double A[][nmax], double b[], int trans);

int lucol (int n, double A[][nmax], int p[]);
int sscol (int n, double A[][nmax], int p[], double b[]);

int lurow (int n, double A[][nmax], int p[]);
int ssrow (int n, double A[][nmax], int p[], double b[]);

void printmat (int n, double A[][nmax], double (*toprint)(int, int, double A[][nmax])) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++)
            printf("%lf ", (*toprint)(i, j, A));
        printf("\n");
    }
}

void printvect (int n, double b[], double (*toprint)(int, double b[])) {
    for (int i = 0; i < n; i++)
        printf("%lf ", (*toprint)(i, b));
    printf("\n");
}


int cholcol (int n, double A[][nmax]) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < j; k++)
                A[i][j] -= A[i][k]*A[j][k];
            A[i][j] /= A[j][j];

            A[i][i] -= A[i][j];
        }
        
        if (A[i][i] <= 0.0)
            return -1;
        A[i][i] = sqrt(A[i][i]);

    }
    return 0;
}

int main (int argc, char ** argv) {
    int n;
    int x, y;
    double A[nmax][nmax];
    double b[nmax];
    double p[nmax];
    int mode = 0;
    bool fail;

    /* mode é uma flag
     *  p : (0, 1)
     *  1 : (cholesky, lu)
     *  2 : (colunas, linhas)
     *  4 : (nao imprime, imprime) o resultado
     *  8 : (nao imprime, imprime) a entrada
     */

    // Lê entrada
    scanf("%d", &n);
    for (int i = 0; i < n*n; i++) {
        scanf("%d %d", &x, &y);
        scanf("%lf", &A[x-1][y-1]);
    }
    for (int i = 0; i < n; i++)
        scanf("%lf", b+i);
    
    // Define mode à partir dos argumentos inseridos
    for (int i = 1; i < argc; i++) {
        if (strcmp(argv[i], "d") == 0)
            mode |= 8;
        else if (strcmp(argv[i], "cholcol") == 0)
            mode |= 0;
    }
    
    // Imprime a entrada
    if (mode&8) {
        printf("Matriz de Entrada: \n");
        printmat(n, A, ([](int i, int j, double A[][nmax]) -> double { return A[i][j]; }));

        printf("Vetor de Entrada: \n");
        printvect(n, b, ([](int i, double b[]) -> double { return b[i]; }));
    }

    // Escolhe o será feito
    if (!(mode&1)) {
        // Cholesky
        if (!(mode&2))
            fail = cholcol(n, A);
        else;
            // colrow

        // Imprime saída
        if (!(mode&4)) {
            if (!fail) {
                printf("Matriz de Saída: \n");
                printmat(n, A, ([](int i, int j, double A[][nmax]) -> double { return (i >= j)?A[i][j]:0.0; }));
            } else
                printf("Não foi possível fazer a decomposição de Cholesky.\n");
        }
    }
}
