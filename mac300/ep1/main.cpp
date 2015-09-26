#include "headers.h"
#include "aux.h"
#include "cholcol.h"
#include "cholrow.h"

int lucol (int n, double A[][nmax], int p[]);
int sscol (int n, double A[][nmax], int p[], double b[]);

int lurow (int n, double A[][nmax], int p[]);
int ssrow (int n, double A[][nmax], int p[], double b[]);

int main (int argc, char ** argv) {
    int n;
    int x, y;
    double A[nmax][nmax];
    double b[nmax];
    double p[nmax];
    int mode = 0;

    /* mode é uma flag
     *  p  : (0, 1)
     *  1  : (imprime, nao imprime) a entrada
     *  2  : (colunas, linhas)
     *  4  : (cholesky, lu)
     *  8  : (imprime, nao imprime) o resultado da decomposição
     *  16 : (imprime, nao imprime) x : A.x = b
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
            mode |= 1;
        else if (strcmp(argv[i], "row") == 0)
            mode |= 2;
        else if (strcmp(argv[i], "lu") == 0)
            mode |= 4;
        else if (strcmp(argv[i], "hidedec") == 0)
            mode |= 8;
        else if (strcmp(argv[i], "hideres") == 0)
            mode |= 16;
    }
    
    // Imprime a entrada
    if (!(mode&1)) {
        printf("Matriz de Entrada: \n");
        printmat(n, A, ([](int i, int j, double A[][nmax]) -> double { return A[i][j]; }));

        printf("Vetor de Entrada: \n");
        printvect(n, b, ([](int i, double b[]) -> double { return b[i]; }));
    }

    // Escolhe o tipo de decomposição
    if (!(mode&4)) {
        // Cholesky
        // Descobre G : A = G.Gt
        if (!(mode&2))
            testfail(cholcol(n, A), 1);
        else
            testfail(cholrow(n, A), 1);

        // Imprime decomposição
        if (!(mode&8)) {
            printf("Matriz de Saída: \n");
            printmat(n, A, ([](int i, int j, double A[][nmax]) -> double { return (i >= j)?A[i][j]:0.0; }));
        }
        
        // Forward Substitution
        // Descobre y : G.y = b
        // Backwards Substitution
        // Descobre x : Gt.x = y
        if (!(mode&2)) {
            testfail(forwcol(n, A, b), 2);
            testfail(backcol(n, A, b, 1), 2);
        } else {
            testfail(forwrow(n, A, b), 2);
            testfail(backrow(n, A, b, 1), 2);
        }

        // Imprime a solução do sistema
        if (!(mode&16)) {
            printf("Vetor de Saída: \n");
            printvect(n, b, ([](int i, double b[]) -> double { return b[i]; }));
        }
    } else {
        // LU
    }
}
