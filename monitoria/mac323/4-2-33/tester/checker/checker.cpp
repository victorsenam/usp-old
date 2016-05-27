#include "../testlib.h"
#include <string>

using namespace std;

set<pair<string, string> > s;
pair<string, string> a;

int main(int argc, char * argv[])
{
    setName("compare set of unordered pairs preceded by one integer");
    registerTestlibCmd(argc, argv);

    ja = ans.readWord();
    pa = ouf.readWord();

    if (ja != pa)
        quitf(_wa, "contagem de caminhos difere, esperando %s, mas encontrou %s", ja.c_str(), pa.c_str());

    while (!ans.eof()) {
        a.first = ans.readWord();
        a.second = ans.readWord();

        if (a.first.compare(a.second) > 0)
            swap(a.first, a.second);

        if (s.find(a) != s.end())
            quitf(_fail, "arestas repetidas no juiz");
        s.insert(a);
    }

    while (!ouf.eof()) {
        a.first = ouf.readWord();
        a.second = ouf.readWord();

        if (a.first.compare(a.second) > 0)
            swap(a.first, a.second);

        if (s.find(a) == s.end())
            quitf(_wa, "solucao tem arestas repetidas ou alguma aresta que o juiz nao tem");
        s.erase(a);
    }

    if (s.size())
        quitf(_wa, "juiz tem %d arestas a mais que solucao", s.size());

    quitf(_ok, "tudo certo");
}

