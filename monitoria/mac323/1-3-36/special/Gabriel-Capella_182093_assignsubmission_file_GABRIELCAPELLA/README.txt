# Informações da tarefa

- MAC0323 - Estrutura de Dados 2
- Aluno: Gabriel Capella
- Numero USP: 8962078
- Tarefa: Creative Problem 1.3.36 (Random iterator; Algs4)
- Data: 20/03/2016

# Executar

java-algs4 TesteVisual N T
Exemplo: java-algs4 TesteVisual 5 10000000

# Ideias Utilizadas

Para resolver o problema foi necessário que cada permutação gerada tivesse um
inteiro equivalente. Para isso foi utilizado o sistema de contagem fatorial e
uma conversão dele para o decimal. Para mais informações:

https://oeis.org/wiki/Factorial_numeral_system
https://en.wikipedia.org/wiki/Factorial_number_system

# Relatório

A primeira parte a ser analisada é a probabilidade de ocorrência de uma única 
sequencia. Em RandomQueue é escolhido um número entre N números presentes na 
fila com mesma probabilidade (1/N). Se inserirmos K números e tirarmos 1 por 
vez, vamos ter que a ocorrência do evento tem probabilidade 
1/K * 1/(K-1) * ... 1/1 = 1/K!.
Isso mostra que a probabilidade de ocorrência de uma permutação é 1/K!. Quando 
estamos trabalhando com K números, existem K! permutações possíveis, fato que 
condiz com a probabilidade de ocorrência de cada uma. 
Como cada permutação tem a mesma probabilidade, o gráfico deve tender a uma 
distribuição uniforme, como é verificado no programa. Note que para T pequenos 
o gráfico pode não aparentar ser uniforme.
