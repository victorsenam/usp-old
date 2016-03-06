DEPS := cholcol.cpp cholrow.cpp lucol.cpp lurow.cpp aux.cpp headers.cpp
GPP := g++ -std=c++11
GCC := gcc
FLAGS := -Wall -pedantic

cholcol.cpp: cholcol.h
cholrow.cpp: cholrow.h
lucol.cpp: lucol.h
lurow.cpp: lurow.h
aux.cpp: aux.h
headers.cpp: headers.h

main.o: main.cpp $(DEPS)
	$(GPP) $< -o $@

genmat.o: genmat.c
	$(GCC) $< -o $@
genmatsim.o: genmatsim.c
	$(GCC) $< -o $@

clear:
	rm main.o
