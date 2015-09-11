DEPS := cholcol.h cholrow.h aux.h headers.h
GCC := g++ -std=c++11
FLAGS := -Wall -pedantic

default: main.o

cholcol.h: cholcol.cpp
cholrow.h: cholrow.cpp
aux.h: aux.cpp
headers.h: headers.cpp

main.o: main.cpp $(DEPS)
	$(GCC) $< -o $@

clear:
	rm main.o
