#include "../testlib.h"

#include <bits/stdc++.h>
using namespace std;

const int N = 1000007;

const char let[9] = {'a', 'd', 'g', 'p', 'q', 's', 'e', 'D', 'c', 'i'};
const int weight[4][9] = {
    {4, 1, 3, 0, 0, 0, 0, 0, 0, 0}, // facil
    {6, 1, 3, 6, 2, 0, 0, 0, 0, 0}, // corner
    {4300, 1000, 3000, 0, 0, 200, 100, 0, 0, 1}, // medio
    {9300, 0, 3000, 0, 0, 2, 1, 4000, 2000, 1} // dificil
};

struct fenwick_tree {
    int bit[N];
    int n;

    void reset (int siz)
    { n = siz; memset(bit, 0, sizeof bit); }

    void add (int i, int x) {
        for (i+=2; i < n+2; i+=(i&-i))
            bit[i] += x;
    }

    int get (int i) {
        int res = 0;
        for (i += 2; i >= 2; i-=(i&-i))
            res += bit[i];
        return res;
    }
};

bool validHeight (int * v, int n) {
    int hg = 0;
    map<int, double> mp;
    map<int, double>::iterator lo_b, up_b;
    double lo_k = 1.;
    double hi_k = 1.;

    double mini = 1.;

    for (int i = 0; i < n; i++) {
        lo_b = mp.upper_bound(v[i]);
        
        if (lo_b == mp.end()) {
            mp[v[i]] = hi_k;
            hi_k += 1.;
        } else if (lo_b == mp.begin()) {
            lo_k -= 1.;
            mp[v[i]] = lo_k;
        } else {
            up_b = lo_b--;
            double val = lo_b->second + (up_b->second - lo_b->second)*.5;
            mini = min(mini,up_b->second - lo_b->second);

            if (val <= lo_b->second || val >= up_b->second) {
                printf("mindist %.30f\n", mini);
                printf("%.50f < %.50f < %.50f\n", lo_b->second, val, up_b->second);
                return 0;
            }

            mp[v[i]] = val;
        }
    }

    printf("mindist %.30f\n", mini);

   return 1;
}

int main (int argc, char ** argv) {
    registerGen(argc, argv, 1);
    int level;
    sscanf(argv[1], "%d", &level);

    int lo_m, hi_m;
    sscanf(argv[2], "%d", &lo_m);
    sscanf(argv[3], "%d", &hi_m);

    long long m = rnd.next(lo_m, hi_m);
    
    int cnt[10], tot = 0, sum = 0;
    for (int i = 0; i < 10; i++)
        tot += weight[level][i];

    for (int i = 0; i < 10; i++) {
        cnt[i] = (tot/weight[level][i])*m;
        sum += cnt[i];
    }
    long long n = m-sum;

    int v[N];

    for (int i = 0; i < n; i++)
        v[i] = i;

    int cnt = 0;
    do {
        for (long long k = 0; k*k*k < n*n; k++) {
            int i = rnd.next(n);
            int j = rnd.next(n);
        
            swap(v[i], v[j]);
        }
        cnt++;
    } while (!validHeight(v, n));
    
    int ops = 0;
    int op[10];
    for (int i = 0; i < 10; i++)
        if (cnt[i])
            op[ops++] = i;

    int front_adds = 0;
    fenwick bit;
    bit.reset(n);
    int el_count = 0;

    for (int i = 0; i < m; i++) {
        if (el_count)
            swap(op[ops-1], op[rnd.next(ops)]);
        else
            for (int i = 0; i < ops; i++)
                if (!op[i])
                    swap(op[ops-1], op[i]);
           
        int now = op[ops-1];    

        printf("%c", let[now]);

        if (now == 0) { // add
            printf("%d %d", bit.get(v[i])+front_adds, rnd.next(INT_MAX));
            bit.add(v[i], 1);
            el_count++;
        } else if (now == 1) { // delete
            int it = rnd.next(el_count);
            printf("%d", bit.get(v[it]));
            bit.add(v[it], -1);
            el_count--;
        } else if (now == 2) { // get
        }
            
    }
}
