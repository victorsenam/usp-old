import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Numero USP: 8962078
 *  Tarefa: Creative Problem 1.3.36 (Random iterator; Algs4)
 *  Data: 20/03/2016
 *
 *  Dependencias: StdDraw.java StdOut.java StdStats.java RandomQueue.java
 *
 *  Execucao: java-algs4 TesteVisual 6 2000000
 *
 ******************************************************************************/
public class TesteVisual {
    public static int fatorial (int N) {
        int p = 1;
        for (int i = 0; i <= N; i++) 
            if (i > 0) p *= i;
        return p;
    }

    /* Recebe o vetor numeros com numeros de 0 a N permutados. Retorna um 
     * inteiro de 0 a N-1 representando qual permutacao eh. */
    public static int PermutationToInt (int[] numeros) {
        int N = numeros.length;
        int[] factoradic = new int[N];
        int[] ndisponiveis = new int[N];
        int count, resposta, i, j;

        for (j = 0; j < N; j++) ndisponiveis[j] = 1;
        for (i = 0; i < N; i++) {
            count = 0;
            for (j = 0; j < N; j++) {
                if (ndisponiveis[j] == 1) count++;
                if (j + 1 == numeros[i]) {
                    ndisponiveis[j] = 0;
                    factoradic[i] = count - 1;
                    break;
                }
            }
        }

        count = 1;
        resposta = 0;
        for (i = 0; i < N; i++) {
            if (i > 0) count *= i;
            resposta += count * factoradic[N - 1 - i];
        }
        return resposta;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        double x, m, max = 0;
        int total = fatorial(N);
        int[] resultado = new int[N];
        double[] resultados = new double[total];
        RandomQueue<Integer> ranqueue;
        int i, j, k;

        ranqueue = new RandomQueue<Integer>();

        for (j = 0; j < T; j++) {
            for (i = 1; i <= N; i++) ranqueue.enqueue(i);
            i = 0;
            for (int s: ranqueue) resultado[i++] = s;
            k = PermutationToInt (resultado);
            resultados[k] += 1;
            if (resultados[k] > max) max = resultados[k];
        }

        StdDraw.setYscale(-5, max + 5);
        StdStats.plotBars(resultados);
    }
}
