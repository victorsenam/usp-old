mkdir testes

make geradores/polynomial/gen
./geradores/polynomial/gen < ./geradores/polynomial/ex1.inp > testes/poly1.dat
./geradores/polynomial/gen < ./geradores/polynomial/ex2.inp > testes/poly2.dat

# make geradores/senoidal/gen
# ./geradores/senoidal/gen < ./geradores/senoidal/ex1.inp > testes/sen1.dat
