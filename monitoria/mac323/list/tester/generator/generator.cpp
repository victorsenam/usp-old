#include "../testlib.h"
#include <cstdio>

// limits
const int N = 10000007;
const int level_N = 4;
const int operation_N = 12;

// aux structures
struct BIT {
    int bit[N];
    int n;

    BIT (int siz)
    { n = siz; memset(bit, 0, sizeof bit); }

    void add (int i, int x) {
        for (i += 2; i < n+2; i+=(i&-i))
            bit[i] += x;
    }

    int get (int i) {
        int res = 0;
        for (i += 2; i >= 2; i-=(i&-i))
            res += bit[i];
        return res;
    }
}

// operations
struct Operation {
    char name;
    int cnt;

    Operation () : name(0), cnt(0) {}
    Operation (char n, double c, int m) : name(n), cnt(c*m) {}
};

Operation operation[operation_N];
int operation_queue[operation_N];
int operation_queue_s;

int buildOperations (char ** argv) {
    int level, m_lo, m_hi;
    sscanf(argv[0], "%d", &level);
    sscanf(argv[1], "%d", &m_lo);
    sscanf(argv[2], "%d", &m_hi);

    operation_queue_s = 0;

    int m = rnd.next(m_lo, m_hi);
    int used = 0;

    // valores positivos são relativos
    // valores negativos são absolutos
    const double proportions[operation_N][level_N] = {
        {0., 0., 0., 0.},
        {0.2, 0.2, 0.1, 0.03},
        {0.3, 0.3, 0.3, 0.3},
        {0., -500., 0., 0.},
        {0., -500., 0., 0.},
        {0., -100., 0., 0.},
        {0., -100., 0., 0.},
        {0., 0., 0.09, 0.04},
        {0., 0., 0.01, 0.01},
        {0., 0., 0, 0.05},
        {0., 0., 0, 0.07},
        {0., 0., -10., -10.}
    };

    const char names[operation_N] = {'a', 'd', 'g', 'f', 'b', 'F', 'B', 's', 'e', 'c', 'D', 'i'};
        
    for (int i = 0; i < operation_N; i++) {
        if (proportions[i][level] >= 0.) {
            operation[i] = Operation(names[i], proportions[i][level], m);
        } else {
            operation[i] = Operation(names[i], 1., -proportions[i][level]);
            m += operation[i].cnt;
        }
        used += operation[i].cnt;
    }

    operation[0] = Operation(names[0], 1., m-used);

    for (int i = 0; i < operation_N; i++)
        if (operation[i].cnt)
            operation_queue[operation_queue_s++] = i;

    return m;
}

// order
int order[N]; 

bool checkOrder () {
    int n = operations[0].cnt;
    map<int, double> mp;
    map<int, double>::iterator lo_b, up_b;
    double lo_k = 1., hi_k = 1.;

    for (int i = 0; i < n; i++) {
        lo_b = mp.upper_bound(v[i]);

        if (lo_b == mp.end()) {
            mp[order[i]] = hi_k;
            hi_k += 1.;
        } else if (lo_b == mp.begin()) {
            lo_k -= 1.;
            mp[order[i]] = lo_k;
        } else {
            up_b = lo_b--;
            double lo = lo_b->second;
            double hi = up_b->second;
            double val = lo + (hi-lo)*.5;
            if (val <= lo || val >= hi)
                return 0;
            mp[order[i]] = val;
        }
    }
    return 1;
}

void setOrder () {
    long long n = operations[0].cnt;
    for (int i = 0; i < n; i++)
        order[i] = i;

    do {
        int i, j;
        for (long long k = 0; k*k*k < n*n; k++) {
            i = rnd.next(n);
            j = rnd.next(n);
            swap(order[i], order[j]);
        }
    } while (!checkOrder());
}

// generator
void generate (int m) {
    int n = operations[0].cnt;
    BIT positions = BIT(n);
    mp<int, int> used;

    int count_add = 0, count_front = 0, count_back = 0, size = 0;
    
    for (int i = 0; i < m; i++) {
        swap(operations_queue[operations_queue_s-1], operations_queue[rnd.next(operations_queue_s)]);
        Operation current = operations[operations_queue[operations_queue_s-1]];
        
        if ( !size && (current.name != 'a' || current.name != 'f' || current.name != 'b') ) {
            i--;
            continue;
        }

        putchar(current.name);
        putchar(' ');

        if (current.name == 'a') {
            int element = order[count_add++];

            int position = positions.get(element) + count_front;
            positions.add(element, 1);

            int value;
            do {
                value = rnd.next(INT_MAX);
            } while (used.find(value) != used.end());
            used[value] = element;
            
            printf("%d %d\n", position, value);
            size++;
        } else if (current.name == 'd') {
            int element = rnd.next(size);

            if (element < count_front) {
                printf("%d\n", element);
                count_front--;
                size--;
            } else if (element < count_front + count_back) {
                printf("%d\n", size-1-count_back);
                count_back--;
                size--;
            } else {
                element -= count_front;
                printf("%d\n", positions.get(element) + count_front);
                size--;
            }
        } else if (current.name == 'g') {
            printf("%d\n", rnd.next(size));
        } 
    }
}

int main (int argc, char ** argv) {
    int m = buildOperations(argv);
    setOrder();
    generate(m);
}
