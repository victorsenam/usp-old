#include "headers.h"
#include "aux.h"
#include "cholcol.h"
#include "cholrow.h"
#include "lucol.h"

int lurow (int n, double A[][nmax], int p[]);
int ssrow (int n, double A[][nmax], int p[], double b[]);

int main (int argc, char ** argv) {
    int n;
    int x, y;
    double A[nmax][nmax];
    double b[nmax];
    int p[nmax];
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
        scanf("%lf", &A[x][y]);
    }
    for (int i = 0; i < n; i++)
        scanf("%lf", b+i);
    
    // Define mode à partir dos argumentos inseridos
    for (int i = 1; i < argc; i++) {
        if (strcmp(argv[i], "row") == 0)
            mode |= 2;
        else if (strcmp(argv[i], "lu") == 0)
            mode |= 4;
        else if (argv[i][0] == 'h') {
            if (strcmp(argv[i]+1, "inp") == 0)
                mode |= 1;
            else if (strcmp(argv[i]+1, "dec") == 0)
                mode |= 8;
            else if (strcmp(argv[i]+1, "res") == 0)
                mode |= 16;
        }
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
            printf("Matriz G: \n");
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
        // Decompoe PA = LU
        if (!(mode&2)) {
            testfail(lucol(n, A, p), 2);
            testfail(sscol(n, A, p, b), 2);
        } else;

        // Imprime a Decomposicao
        if (!(mode&8)) {
            printf("Matriz L: \n");
            printmat(n, A, ([](int i, int j, double A[][nmax]) -> double { return (i > j)?A[i][j]:(i==j)?1.0:0.0; }));

            printf("Matriz U: \n");
            printmat(n, A, ([](int i, int j, double A[][nmax]) -> double { return (i <= j)?A[i][j]:0.0; }));
        }

        // Imprime a solução do sistema
        if (!(mode&16)) {
            printf("Vetor de Saída: \n");
            printvect(n, b, ([](int i, double b[]) -> double { return b[i]; }));
        }
    }
}
