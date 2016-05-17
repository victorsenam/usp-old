#include "../testlib.h"
#include <string>

using namespace std;

const string YES = "SIM";
const string NO = "NAO";
const string ALTNO = "NãO";

int main(int argc, char * argv[])
{
    setName((YES + " or " + NO + " (case insensetive)").c_str());
    registerTestlibCmd(argc, argv);

    std::string ja = upperCase(ans.readWord());
    std::string pa = upperCase(ouf.readWord());

    if (ja == ALTNO) ja = NO;
    if (pa == ALTNO) pa = NO;

    if (ja != YES && ja != NO)
        quitf(_fail, "%s or %s expected in answer, but %s found", YES.c_str(), NO.c_str(), compress(ja).c_str());

    if (pa != YES && pa != NO)
        quitf(_pe, "%s or %s expected, but %s found", YES.c_str(), NO.c_str(), compress(pa).c_str());

    if (ja != pa)
        quitf(_wa, "expected %s, found %s", compress(ja).c_str(), compress(pa).c_str());

    quitf(_ok, "answer is %s", ja.c_str());
}

