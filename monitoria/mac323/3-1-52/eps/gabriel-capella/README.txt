# Informacoes da tarefa

- MAC0323 - Estrutura de Dados 2
- Aluno: Gabriel Capella
- Numero USP: 8962078
- Tarefa: Creative Exercise 3.1.52 (Word frequencies; IntroCS)
- URL: http://introcs.cs.princeton.edu/java/31datatype/
- Data: 05/03/2016

# Executar

java ToWordsList < t.txt| java SortLines| java CountLines| java SortNumberLast

t.txt eh o arquivo de texto de entrada

# Explicacao

ToWordsList -> Recebe na entrada padrao um arquivo texto e transforma suas 
palavras em uma lista, na ordem que aparecem no texto. Utilizamos uma expressao
regex para identicar caracteres que nao sao texto e separar as palavras.

SortLines -> Recebe um arquivo de texto por entrada padrao e ordena as linhas 
por ordem lexografica. Utiliza a biblioteca nativa do java (java.util.Arrays)
e a funcao sort para organizar e mostrar a lista organizada.

CountLines -> Une as linhas repetidas de um arquivo texto e coloca no final o 
numero de linhas repetidas. Ignora linhas brancas. Foi utilizado um laco para 
verificar o padrao repetido e contar as repeticoes.

SortNumberLast -> Recebe um arquivo de texto por entrada padrao no qual existem 
numero no final de cada linha. Organiza esse arquivo pela ordem decrescente 
desses numeros. Funciona transformando o numero no final da linha em inteiro e 
realizando uma subtracao para comparacao.
