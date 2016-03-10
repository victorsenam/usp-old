/*******************************************************************************

Generator

Nome:   Matheus Tavares Bernardino
Nº USP: 9292987
Data:   03/03/2016

Descrição:
Exercício 1.6.15 do site http://introcs.cs.princeton.edu/java/16pagerank/
Este programa trata de pagerank e tem utilidade de gerar dados fictícios
para a simulação de um algoritmo de pagerank.
Este programa recebe N, o total de páginas a serem criadas (descontando hubs e
athorits); M, o total de links a serem gerados; hubs e authorits, que
representam o numero total de paginas a serem criadas com cada uma dessas
classificações. Onde Hub é uma pagina que recebe links de 10% de todas as
paginas e authorits enviam links a 10% de todas as páginas. (Nota: devido ao
arredondamento do cálculo "10% de N", essa porcentagem pode ser um pouco maior
que dez por cento)
O programa gera um conjunto de dados para alimentar o programa Transition.java.
Esse conjunto de dados é composto do total de páginas (N + hubs + authorits),
seguido por M-úplas ordenadas que representam links da pagina equivalente ao
primeiro membro para o segundo.


Compilação:    javac-algs4 Generator.java
Execução:      java-algs4 Generator N M hubs authorits

Ex de execução:
   $java-algs4 Generator 9 2 1 1
Ex de Saída:
   11
 
   1 0
   6 7
   9 4
   4 10
(Nota: Os número podem variar por se tratar de pseudo-aleatoriedade)

            Conclusão dos testes do RandomSurferHistogram,
                   usando dados gerados neste gerador.

 Com uma quantidade suficiente alta de páginas, os hubs são classificados em
 níveis altos no ranking de paginas. Os authorits, por sua vez, permanecem em 
 níveis mais baixos que os "níveis normais", ou, nos melhores casos, 
 semelhantes.
 Com o aumento do numero de links, percebe-se que, para pequenas
 quantidades de paginas, os hubs não se destacam tanto. Mas isso pode ser 
 devido ao fato de que, com grande quantidade de links, outros hubs foram
 criados "aleatoriamente" entre as páginas.

*******************************************************************************/

import edu.princeton.cs.algs4.*;
//Biblioteca de http://introcs.cs.princeton.edu

public class Generator {
    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int M = Integer.parseInt (args[1]);
        int hubs = Integer.parseInt (args[2]);
        int authorits = Integer.parseInt (args[3]);
        int i, porcentagem, k, total_pags;
        StdOut.printf ("%d\n\n", N + hubs + authorits);
        for (i = 0; i < M; i++) {
            StdOut.printf ("%d %d\n", (int)(Math.random()*N), 
            (int)(Math.random()*N));
        }

        porcentagem = (int) (Math.ceil (0.1*N));
        total_pags = N;
        for (k = 0; k < hubs; k++)
            for (i = 0; i < porcentagem; i++)
                StdOut.printf ("%d %d\n", (int)(Math.random()*N),
                total_pags+k);
                
        total_pags = total_pags + k - 1;
        for (k = 0; k < authorits; k++)
            for (i = 0; i < porcentagem; i++)
                StdOut.printf ("%d %d\n", total_pags+1+k, 
                (int)(Math.random()*N));
    }
}
