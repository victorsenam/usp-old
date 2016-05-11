#include "../testlib.h"

int main () {
    registerValidation();

    int n = inf.readInt();
    inf.readDouble();
    inf.readEoln();

    for (int i = 0; i < n; i++) {
        inf.readDouble(-1., 1.);
        inf.readDouble(-1., 1.);
        inf.readEoln();
    }

    inf.readEof();
}
