Nome: Nícolas Nogueira Lopes da Silva NºUSP: 9277541

Para fazer a tarefa 3.1.52 eu achei que seria de grande ajuda utilizar um
tipo de dado que está contido no pacote java.util que se trata do TreeMap.

O TreeMap consiste de um dicionário composto de varias entradas, sendo
cada entrada constituída de uma chave e um valor. Nesse dicionário cada
chave é única e as entradas são ordenadas de acordo com a ordem crescente
natural para as chaves, no caso das Strings a ordem é a lexicográfica.
Essa regra de ordenação pode ser alterada quando modificamos o Comparator
base utilizado na criação do TreeMap, para a tarefa o comparador conta com
uma "regra" para ordenar o TreeMap seguindo uma ordenação decrescente dos
valores (frequências das palavras) das entradas. Quando os valores de duas
ou mais entradas são iguais, a ordenação leva em consideração a ordem 
natural das chaves.