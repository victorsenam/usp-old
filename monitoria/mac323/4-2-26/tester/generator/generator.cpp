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

int get_int (char ** argv) {
    int res;
    sscanf(argv[0], "%d", &res);
    return res;
}

int main (int argc, char ** argv) {
    registerGen(argc, argv, 1);
    
    n = rnd.next(get_int(argv+1), get_int(argv+2));
    m = rnd.next(get_int(argv+3), get_int(argv+4));

    printf("%lld %lld\n", n, m);
    for (int i = 0; i < m; i++) {
        int a = rnd.next(n)+1;
        if (rnd.next(2) == 0) a = -a;

        int b = rnd.next(n)+1;
        if (rnd.next(2) == 0) b = -b;
        printf("%d %d\n", a, b);
    }

    return 0;
}
