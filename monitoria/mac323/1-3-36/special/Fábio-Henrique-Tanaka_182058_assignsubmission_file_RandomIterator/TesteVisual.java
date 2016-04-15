import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Nome: Fabio Henrique Kiyoiti dos Santos Tanaka
 *  Numero Usp: 9297982
 *  Compilation:  javac TesteVisual.java
 *  Execution:    java TesteVisual N T
 *
 *  Este programa produz, aleatoriamente, T permutacoes de N elementos
 *  representados por inteiros de 0 a N-1 e imprime um histograma com a
 *  frequencia de cada permutacao com frequencia maior que 0;
 *  
 *  As permutacoes sao representadas por inteiros da seguinte forma para N = 3:
 *  2 0 1 = 201. Caso a permutacao inicie por 0, ele nao eh representado
 *  (Ex: 0 2 1 = 21);
 *
 ******************************************************************************/

public class TesteVisual {
    // tamanho guarda o numero de elementos diferentes em int[][]frequencia
    // (o numero de permutacoes diferentes);
    private static int tamanho;

    // Converte um vetor de objects em um int da forma dita no cabecalho
    public static int ObjectToInt (Object[] vetor, int tamanho) {
        int valor = 0, cont, cont2 = 1;
        for (cont = tamanho-1; cont >=0; cont--) {
            valor += ((Integer)vetor[cont])*cont2;
            cont2 *= 10;
        }
        return valor;
    }

    // Esta funcao recebe um inteiro x e um vetor crescente v[0..n-1]
    // e devolve um indice j em 0..n tal que v[j-1] < x <= v[j].
    public static int bb (int x, int n, int v[][]) { 
        int e, m, d; 
        e = -1; d = n;
        while (e < d-1) {  
            m = (e + d)/2;
            if (v[0][m] < x) e = m;
            else d = m; 
        }
        return d;
    }

    // Insere x no vetor v[][]
    public static void insereInt (int x, int posicao, int v[][]) {
        int cont;
        for (cont = tamanho; cont > posicao; cont--) {
            v[0][cont] = v[0][cont - 1];
            v[1][cont] = v[1][cont-1];
        }
        v[0][cont] = x;
    }

    // insere x no vetor v, caso x ja esteja no vetor, sua frequencia eh acrescentada
    public static void frequencia (int[][] v, int x) {
        int posicao;

        if (tamanho == 0) {
                v[0][0] = x;
                v[1][0] = 1;
                tamanho++;
        }

        else {
            posicao = bb (x, tamanho, v);
            if (v[0][posicao] == x) 
                v[1][posicao]++;
            else {
                insereInt (x, posicao, v);
                v[1][posicao] = 1;
                tamanho++;
            }
        }
    }

    

    public static void main (String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        int cont, cont2, aux, posicao;
        int[][] frequencia = new int[2][T];
        tamanho = 0;

        // Criacao da Pilha
        RandomQueue<Integer> stack = new RandomQueue<Integer>();
        for (cont = 0; cont < N; cont++)
            stack.enqueue(cont);

        // Faz T permutacoes e as guarda no vetor frequencia
        for (cont = 0; cont < T; cont++) {
            aux = ObjectToInt (stack.Iterador(), N);
            frequencia (frequencia, aux);            
        }

        // Imprime as permutacoes e suas respectivas frequencias
        // "Descomente" caso queira testar
        /*for (cont = 0; cont < tamanho; cont++)
            System.out.println (frequencia [0][cont] + " " + frequencia [1][cont]);*/

        //Desenha o histograma
        Histogram desenho = new Histogram(tamanho);
        for (cont = 0; cont < tamanho; cont++) {
            for (cont2 = 0; cont2 < frequencia [1][cont]; cont2++)
                desenho.addDataPoint(cont);
        }
        desenho.draw();

        // Realiza um teste de aderencia para ver se a distribuicao foi "justa"
        // "Descomente" para testar, mais informacoes no relatorio
        /*Teste.TesteAderencia(N, T, frequencia, tamanho);*/

    }
}