// TODO: Tirar funções de print



// Victor Sena Molero - 8941317

#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <cassert>

// ==== CLASSES ======
/* Linked List for Sparse Matrix Representation */
class linked_list {
public:
    double value;
    int position;
    linked_list * next;

    linked_list(int pos, double x) {
        value = x;
        position = pos;
        next = NULL;
    }

    ~linked_list() {
        if (next != NULL)
            delete next;
    }
    void print () {
        linked_list * el = this;

        while (el != NULL) {
            printf("(%d, %lf) ", el->position, el->value);
            el = el->next;
        }
        printf("\n");
    }
};

/* Vector Representation */
class vector {
public:
    int size;
    double * vals;

    vector(int n) {
        size = n;
        vals = (double *) malloc(size*sizeof(double));
    }

    ~vector() {
        free(vals);
    }

    double & operator [] (int i) {
        return vals[i];
    }

    void print() {
        for (int i = 0; i < size; i++)
            printf("%f ", vals[i]);
        printf("\n");
    }

    double operator * (const vector & other) const {
        double res = 0.0;
        for (int i = 0; i < size; i++)
            res += vals[i] * other[i];
    }
};

/* Sparse Matrix Representation */
class matrix {
public:
    int dimension;
    linked_list ** mat;

    matrix(int n) {
        dimension = n;
        mat = (linked_list **) malloc(dimension*(sizeof(linked_list *)));
        for (int i = 0; i < dimension; i++)
            mat[i] = (linked_list *) NULL;
    }
    
    ~matrix() {
        for (int i = 0; i < dimension; i++)
            if (mat[i] != NULL)
                delete mat[i];
        free(mat);
    }

    linked_list * operator [] (int i) {
        return mat[i];
    }

    void insertItem (int line, int column, double value) {
        linked_list * el = new linked_list(column, value);   
        el->next = mat[line];
        mat[line] = el;
    }
    
    void print () {
        for (int i = 0; i < dimension; i++) {
            if (mat[i] == NULL)
                printf("line %d nil\n", i);
            else
                mat[i]->print();
        }
    }

    void product (vector & r, vector & b) {
        assert(b.size == dimension);
        assert(r.size == dimension);
        for (int i = 0; i < dimension; i++) {
            r[i] = 0;
            linked_list * el = mat[i];
            while (el != NULL) {
                r[i] += el->value*(b[el->position]);
                el = el->next;
            }
        }
    }
};

// ==== FUNCTIONS ====
void getInput(matrix * & A, vector * & b) {
    int n, m;
    scanf("%d %d", &n, &m);

    A = new matrix(n);
    b = new vector(n);

    for (int i = 0; i < m; i++) {
        int lin, col;
        double val;
        scanf("%d %d %lf", &lin, &col, &val);
        
        A->insertItem(lin, col, val);
    }

    for (int i = 0; i < n; i++) {
        double aux;
        scanf("%lf", b->vals+i);
    }
}

// ==== MAIN =========
void conjugateGradients(matrix & A, vector & r) {
    vector x(r->size);
    vector d(r->size);
    vector g(r->size);
    double lastNorm = (r*r);

    for (int i = 0; i < r->size; i++) {
        x[i] = 0;
        d[i] = r[i];
    }

    for (int k = 0; k < r->size; k++) {
        A->product(g, d);
        double alpha = lastNorm/(d*g);
        for (int i = 0; i < r->size; i++) {
            x[i] += alpha*d[i];
            r[i] -= alpha*g[i];
        }
        double beta = lastNorm;
        lastNorm = r*r;
        beta /= lastNorm;
        for (int i = 0; i < r->size; i++)
            d[i] = r[i] + beta*d[i];
    }
}

int main () {
    matrix * A;
    vector * b;

    getInput(A, b);
    conjugateGradients(*A, *b);

    b->print();

    delete b;
    delete A;
}
