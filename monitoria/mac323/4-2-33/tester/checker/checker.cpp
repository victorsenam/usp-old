#include "../testlib.h"
#include <string>

using namespace std;

set<string> s;
string a;

int main(int argc, char * argv[])
{
    setName("compare set of strings succeded by sequence of integers");
    registerTestlibCmd(argc, argv);

    inf.readWord();
    int ty = inf.readInt();
    inf.readInt();
    int n = inf.readInt();

    if (ty) {
        do {
            a = ans.readString();
            ensuref(s.find(a) == s.end(), "arestas repetidas no juiz");
            s.insert(a);
        } while (a.length());

        do {
            a = ouf.readString();
            quitif(s.find(a) == s.end(), _wa, "montagem incorreta, aresta na resposta aparece repetida ou não existe");
            s.erase(a);
        } while (a.length());

        quitif(s.size(), _wa, "montagem incorreta, faltou alguma aresta");
    }

    for (int i = 0; i < n; i++) {
        string ja = ans.readString();
        string pa = ans.readString();
        quitif(ja.compare(pa) != 0, _wa, "reposta da query %d errada. %s esperado, recebeu %s.", i, ja.c_str(), pa.c_str());
    }

    quitf(_ok, "ok");
}

