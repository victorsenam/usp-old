#include "../testlib.h"
#include <cstdio>
#include <map>
#include <algorithm>
#include <cassert>
#include <random>

typedef long long int ll;

const int N = 1000000;

// params
ll n, m;
std::set<std::pair<int, int> > s;
std::pair<int, int> aux;
int qtd[N];
int p[N];

int get_int (char ** argv) {
    int res;
    sscanf(argv[0], "%d", &res);
    return res;
}

int main (int argc, char ** argv) {
    registerGen(argc, argv, 1);
    
    n = rnd.next(get_int(argv+1), get_int(argv+2));
    m = rnd.next(get_int(argv+3), get_int(argv+4));
    m = std::max(m, 0ll);
    m = std::min(m, n*(n-1));
    int q = n;

    for (int i = 0; i < n; i++) {
        p[i] = i;
        qtd[i] = n-1;
    }

    printf("%lld %lld\n", n, m);
    while (s.size() < m) {
        int ind = rnd.next(q);
        aux = std::pair<int, int>(p[ind], rnd.next(n));

        if (s.find(aux) == s.end()) {
            s.insert(aux);
            qtd[aux.first]--;

            if (qtd[aux.first] == 0) {
                std::swap(p[ind], p[q-1]);
                q--;
            }
        }
    }

    for (std::pair<int, int> v : s)
        printf("%d %d\n", v.first, v.second);

    return 0;
}
