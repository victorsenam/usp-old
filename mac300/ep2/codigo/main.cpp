#include <cstdio>
#include <cstdlib>
#include <algorithm>

class linked_list {
public:
    linked_list * next;
    double val;
    int col;

    linked_list() {
        next = NULL;
    };
};

class input_els {
public:
    int lin, col;
    double val;

    bool operator < (const input_els & other) const {
        if (lin != other.lin)
            return lin < other.lin;
        if (col != other.col)
            return col > other.col;
        return val < other.val;
    }
};

void matrix_vector_product (const linked_list ** & A, const double * & b, double * & res, int n) {
    for (int i = 0; i < n; i++) {
        res[i] = 0.0;

        linked_list * el = A[i];
        while (el != NULL)
            res[i] += (el->val)*b[el->col];
    }
}

int get_input (linked_list ** & mat, int * & b) {
    int n, m;             // dimensões da matriz
    input_els * input;    // elementos da entrada
    scanf("%d %d", &n, &m);

    mat = (linked_list **) malloc(n*sizeof(linked_list *));
    vet = (int *) malloc(n*sizeof(int));
    input = (input_els *) malloc(m*sizeof(input_els));

    for (int i = 0; i < n; i++)
        mat[i] = (linked_list *) NULL;

    for (int i = 0; i < m; i++)
        scanf("%d %d %lf", &input[i].lin, &input[i].col, &input[i].val);

    std::sort(input, input+m);

    for (int i = 0; i < m; i++) {
        linked_list * aux = (linked_list *) malloc(sizeof(linked_list));
        aux->col = input[i].col;
        aux->val = input[i].val;
        aux->next = mat[input[i].lin];
        mat[input[i].lin] = aux;
    }

    for (int i = 0; i < n; i++)
        scanf("lf", b+i);

    free(input);
    return n;
}

void conjugate_gradients (const linked_list ** & A, double * & r, const int n) {
    double * x = (double *) malloc(n*sizeof(double));
    double * d = (double *) malloc(n*sizeof(double));

    for (int i = 0; i < n; i++) {
        x[i] = 0;
        d[i] = r[i];
    }

    
}

int main () {
    int n;                   // n
    linked_list ** A;        // matriz de entrada A
    double * b;              // vetor de entrada b

    // recebe a entrada
    n = get_input(A, b);

    // calcula x em b
    
}
