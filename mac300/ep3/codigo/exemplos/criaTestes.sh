mkdir testes

make gerador/gen
./gerador/gen < ./gerador/ex1.inp > testes/poly1.dat
./gerador/gen < ./gerador/ex2.inp > testes/poly2.dat
./gerador/gen < ./gerador/ex3.inp > testes/poly3.dat
