# Informacoes da tarefa

- MAC0323 - Estrutura de Dados 2
- Aluno: Gabriel Capella
- Número USP: 8962078
- Tarefa: Creative Ex. 1.6.15 (Hubs and authorities) de IntroCS
- URL: http://introcs.cs.princeton.edu/java/16pagerank/
- Data: 27/02/2016

# Como executar

Para gerar um arquivo com paginas e links aleatorios eh necessario executar o 
Generetor, com os seguintes argumentos: numero de paginas, o numero de links, 
o numero de hubs e o numero authorities. Um exemplo eh: 

java Generator 10 30 3 4

Esse programa ira retornar duas linhas contendo informacoes sobre os hubs e 
authorities, uma linha contendo o numero de links e, depois, os links - sendo o 
primeiro numero a saida e o segundo a entrada.

Para gerar a matriz de transicao eh necessario redireceionar a saida do 
Generator para o Transition. O programa ira retornar duas linhas contendo 
informacoes sobre os hubs e authorities, uma linha contendo as dimensoes da 
matriz e em seguida a matriz.

O ultimo programa eh o Markov, o qual le a saida do Transition e retorna duas 
linhas contendo informacoes sobre os hubs e authorities e uma lista contendo o 
valor do PageRank de cada pagina.

Exemplo: para gerar um grafo com 20 paginas, 1000 links, 5 hubs e 2 authorities.
Posteriormente fazer a matriz de transicao e gerar o PageRank.

java Generator 20 1000 5 2 | java Transition | java Markov 400

# Consideracoes

Foi adotado que podem existir links que redirecionam uma pagina para ela mesma.

# Resultado

Foi executado uma sequencia de testes e observouse que as authorities apresentam
pontuacao no pagerank semelhante a qualquer outra pagina. 
Ja os hubs, apresentam maior valor (estao melhores classificadas) do que as 
outras paginas do grafo.
