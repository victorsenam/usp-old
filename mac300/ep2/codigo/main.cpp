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

int get_input (linked_list ** mat) {
    int n, m;             // dimensões da matriz
    input_els * input;    // elementos da entrada
    scanf("%d %d", &n, &m);

    mat = (linked_list **) malloc(n*sizeof(linked_list *));
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

    free(input);
}

int main () {
    int n;                                      // dimensões da matriz
    linked_list ** mat;                         // quantidade de elementos não nulos da matriz

    n = get_input(mat);

    for (int i = 0; i < n; i++) {
        linked_list * el = mat[i];
        while (el != (linked_list *) NULL) {
            printf("(%d -> %.7lf) ", el->col, el->val);
            linked_list * prev = el;
            el = el->next;
            free(prev);
        }
        printf("fim linha %d\n", i);
    }
}
