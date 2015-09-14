# Compilar
`make main.o`

# Rodar
`./main.o <argumentos>`  
Os argumentos são:
- row : usar orientação a colunas
- lu : usar decomposição LU
- hres : esconder o resultado de Ax = b
- hdec : esconder o resultado da decomposição
- hinp : esconder o echo da entrada

# Tablas
Todas no arquivo relatorio.pdf

# Comentários Sobre os Tempos
Já que as matrizes são armazenadas linhas em C, ele favorece algoritmos orientados a colunas. É facil ver pelos tempos observados nesse EP que isso faz uma diferença significativa na velocidade de execução do programa.  
Ou seja, é mais fácil para o processador iterar por espaços contínuos de memória, portanto, algortimos que se aproveitam dessa propriedade tendem a ser mais eficientes.
