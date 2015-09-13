DEPS := cholcol.cpp cholrow.cpp lucol.cpp aux.cpp headers.cpp
GCC := g++ -std=c++11 -g
FLAGS := -Wall -pedantic

default: main.o

cholcol.cpp: cholcol.h
cholrow.cpp: cholrow.h
lucol.cpp: lucol.h
aux.cpp: aux.h
headers.cpp: headers.h

main.o: main.cpp $(DEPS)
	$(GCC) $< -o $@

clear:
	rm main.o
