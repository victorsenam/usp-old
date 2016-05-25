#include "../testlib.h"

int main () {
    registerValidation();

    int n = inf.readInt();
    inf.readEoln();

    for (int i = 0; i < n; i++) {
        char c = inf.readChar();
        inf.readSpace();

        if (c == 'a') {
            inf.readInt();
            inf.readSpace();
            inf.readInt();
        } else if (c == 'd' || c == 'g' || c == 'f' || c == 'b' || c == 'c' || c == 'D') {
            inf.readInt();
        }

        inf.readEoln();
    }

    inf.readEof();
}
