import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Nome: Fabio Henrique Kiyoiti dos Santos Tanaka
 *  Numero Usp: 9297982
 *  Compilation:  javac Teste.java
 *
 *  Esta classe realiza testes de aderencia para 1 <= N <= 6.
 *
 ******************************************************************************/

public class Teste {

    private static int fatorial (int n) {
        int valor = n;
        while (n > 1) {
            valor *= n-1;
            n--;
        }
        return valor;
    }

    //Calcula o qui2 para os dados observados
    private static double Qui2 (int N, int T, int[][] Observacao, int tamanho) {
        int cont;
        double dadosEsperados, dadosObservados, qui2 = 0, aux;
        dadosEsperados = ((double) T)/fatorial(N);
        System.out.println ("Dados Esperados = " + dadosEsperados);
        


        for (cont = 0; cont < fatorial(N); cont ++) {
            if (cont < tamanho) {
                dadosObservados = Observacao[1][cont];
            }
            //Caso o valor nao exista no vetor Observacao
            else dadosObservados = 0;
    
            aux = (dadosEsperados - dadosObservados);
            aux *= aux;
            aux = aux/dadosEsperados;
            qui2 += aux;
        }
        return qui2;
    }

    // Esta funcao imprime a conclusao baseada na tabela qui^2, que foi consultada
    // por mim de forma manual para todos os N no intervalo
    private static void imprimeResultado (double qui2, int N) {

        if (N == 1) System.out.println ("H0 eh aceito para alfa = 5%");

        else if (N == 2) {
            if (qui2 < 3.841) System.out.println("H0 eh aceito para alfa = 5%");
            else System.out.println("H0 eh rejeitado para alfa = 5%");
        }

        else if (N == 3) {
            if (qui2 < 11.07) System.out.println("H0 eh aceito para alfa = 5%");
            else System.out.println("H0 eh rejeitado para alfa = 5%");
        }

        else if (N == 4) {
            if (qui2 < 35.172) System.out.println("H0 eh aceito para alfa = 5%");
            else System.out.println("H0 eh rejeitado para alfa = 5%");
        }

        else if (N >= 5) {
            if (qui2 < 124.3) System.out.println("H0 eh aceito para alfa = 5%");
            else System.out.println("H0 eh rejeitado para alfa = 5%");
        }

    }

    public static void TesteAderencia(int N, int T, int[][] dadosObservados, int tamanho) {
        double qui2;
        qui2 = Qui2(N, T, dadosObservados, tamanho);
        System.out.println ("Qui^2 = " + qui2);
        System.out.println ("com " + (fatorial(N)-1) + " graus de liberdade");
        imprimeResultado(qui2, N);


    }
}