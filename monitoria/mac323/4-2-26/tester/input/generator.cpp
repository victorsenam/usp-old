/****************
 * Gerador de Exercício Bônus - Number of Paths in a DAG
 * @param lim maior caractere gerado pelas strings
 * @param n tamanho máximo das strings geradas
 * 
 * Gera um conjunto de strings tal que a quantidade de
 * caminhos entre a primeira e a ultima string gerada
 * é O((lim-'a')^n)
 ****************/

#include <bits/stdc++.h>
using namespace std;

#include "../testlib.h"

int main (int argc, char ** argv) {
    registerGen(argc, argv, 1);

    char lim;
    int n;
    sscanf(argv[1], "%c", &lim);   
    sscanf(argv[2], "%d", &n);

    assert(lim > 'a');
    assert(lim <= 'z');
    assert(n > 0);

    // vertices
    for (int i = 0; i < n; i++) {
        for (char k = 'a'; k <= lim; k++) {
            for (int p = 0; p < i; p++)
                putchar(lim);

            putchar(k);
            putchar('\n');
        }
    }
}


