#include "../testlib.h"
#include <cstdio>
#include <map>
#include <algorithm>
#include <cassert>

// limits
const int N = 1000007;
const int level_N = 4;
const int operation_N = 12;

// aux structures
struct BIT {
    int bit[N];
    int n;

    BIT (int siz)
    { n = siz; memset(bit, 0, sizeof bit); }

    void add (int i, int x) {
        for (i += 2; i <= n+2; i+=(i&-i))
            bit[i] += x;
    }

    int get (int i) {
        int res = 0;
        for (i += 2; i > 0; i-=(i&-i))
            res += bit[i];
        assert(res >= 0);
        return res;
    }
};

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
    sscanf(argv[1], "%d", &level);
    sscanf(argv[2], "%d", &m_lo);
    sscanf(argv[3], "%d", &m_hi);

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
    int n = operation[0].cnt;
    std::map<int, double> mp;
    std::map<int, double>::iterator lo_b, up_b;
    double lo_k = 1., hi_k = 1.;

    for (int i = 0; i < n; i++) {
        lo_b = mp.upper_bound(order[i]);

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
    long long n = operation[0].cnt;
    for (int i = 0; i < n; i++)
        order[i] = i;

    do {
        int i, j;
        for (long long k = 0; k*k*k < n*n; k++) {
            i = rnd.next(n);
            j = rnd.next(n);
            std::swap(order[i], order[j]);
        }
    } while (!checkOrder());
}

// generator
int getNewValue (std::map<int, int> & used, int element) {
    int value;
    do {
        value = rnd.next(INT_MAX);
    } while (used.find(value) != used.end());
    used[value] = element;
    return value;
}

int getUsedValue (std::map<int, int> & used) {
    int ref = rnd.next(INT_MAX);

    std::map<int, int>::iterator it = used.lower_bound(ref);
    if (it == used.end())
        it = used.begin();
    return it->first;
}

void generate (int m) {
    int n = operation[0].cnt;
    BIT positions = BIT(n);
    std::map<int, int> used;
    std::map<int, int> contained;

    int count_add = 0, count_front = 0, count_back = 0, size = 0, count_deleted = 0;
    
    printf("%d\n", m);
    for (int i = 0; i < m; i++) {
        std::swap(operation_queue[operation_queue_s-1], operation_queue[rnd.next(operation_queue_s)]);
        Operation current = operation[operation_queue[operation_queue_s-1]];
        
        if ( !size && (current.name != 'a' && current.name != 'f' && current.name != 'b') ) {
            i--;
            continue;
        }

        putchar(current.name);
        putchar(' ');

        if (current.name == 'a') {
            int element = order[count_add++];

            int position = positions.get(element) + count_front;
            positions.add(element, 1);
            
            int value = getNewValue(used, element);
            contained[value] = element;
            
            printf("%d %d", position, value);
            size++;
        } else if (current.name == 'd') {
            int chosen = rnd.next(size);

            if (chosen < count_front) {
                printf("%d", chosen);
                count_front--;
                size--;
            } else if (chosen < count_front + count_back) {
                chosen -= count_front;
                printf("%d", size-1-chosen);
                count_back--;
                size--;
            } else {
                chosen += count_deleted - count_front - count_back;
                std::swap(order[chosen], order[count_deleted]);

                int element = order[count_deleted];
                int position = positions.get(element) + count_front - 1;
                printf("%d", position, element);
                positions.add(element, -1);

                size--;
                count_deleted++;
            }
        } else if (current.name == 'g') {
            printf("%d", rnd.next(size));
        } else if (current.name == 'f') {
            int value = getNewValue(used, -1);
            printf("%d", value);
            count_front++;
            size++;
        } else if (current.name == 'b') {
            int value = getNewValue(used, -2);
            printf("%d", value);
            count_back++;
            size++;
        } else if (current.name == 'F') {
            count_front--;
            size--;
        } else if (current.name == 'B') {
            count_back--;
            size--;
        } else if (current.name == 'c') {
            int value = getUsedValue(used);
            printf("%d", value);
        } else if (current.name == 'D') {
            int value = getUsedValue(used);
            printf("%d", value);

            int chosen = used[value];

            if (chosen == -1)
                count_front--;   
            else if (chosen == -2)
                count_back--;
            else {
                std::swap(order[chosen], order[count_deleted]);
                int element = order[count_deleted];

                positions.add(element, -1);

                count_deleted++;
            }

            size--;
        }

        current.cnt--;
        if (!current.cnt)
            operation_queue_s--;

        putchar('\n');
    }
}

int main (int argc, char ** argv) {
    int m = buildOperations(argv);
    setOrder();
    generate(m);
}
