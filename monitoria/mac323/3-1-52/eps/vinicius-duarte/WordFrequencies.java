/* Nome: Vinicius Pessoa Duarte             */
/* Numero USP: 8941043                      */
/* Disciplina: MAC-0323                     */
/* Exercicio: Word frequencies/3.1.52       */

// Este programa recebe o endereco de um arquivo de texto na linha de comando e retorna um indice
// em ordem decrescente de ocorrencias das palavras.

import edu.princeton.cs.algs4.*;

public class WordFrequencies {
    // Recebe um endereco de arquivo de texto e retorna uma lista de todas as palavras. */
    public static String[] rawIndex () {
        String input = StdIn.readAll ().toLowerCase ();
        String[] words = input.split ("[^a-zA-Z_0-9-]");
        return words;
    }

    // Recebe uma lista de palavras e coloca em sequencia na lista as ocorrencias de uma mesma palavra.
    public static void joinEqualStr (String[] raw) {
        for (int i = 0; i < (raw.length - 1); i++) {
            for (int j = i + 1; j < raw.length; j++) {
                if (raw[i].compareTo (raw[j]) == 0) {
                    String temp = raw[j];
                    raw[j] = raw[++i];
                    raw[i] = temp;
                }
            }
        }
    }

    // Recebe uma lista de palavra com ocorrencias repetidas em sequencia e um vetor para contagem das
    // repeticoes dessas ocorrencias, retornando o novo tamanho da lista, sem as repeticoes de ocorrencias.  
    public static int trimCount (String[] index, int[] count) {
        int pos = 0, i = 0, j = 1;
        count[0] = 1;
        while (i < index.length) {
            if (j < index.length && index[i].compareTo (index[j]) == 0) {
                count[pos]++;
                j++;
            }
            else {
                index[pos++] = index[i];
                i = j;
            }
        }
        return pos;
    }

    // Recebe uma lista de palavras de tamanho "pos" e suas ocorrencias e a ordena
    // de forma decrescente em relacao ao numero de ocorrencias.
    public static void sortDescending (String[] index, int[] count, int pos) {
        for (int i = 0; i < pos - 1 ; i++) {
            int posMax = i;
            for (int j = i + 1; j < pos; j++) {
                int max = count[i];
                if (count[j] > max) {
                    int temp = count[j];
                    String tempIndex = index[j];
                    count[j] = count[i]; index[j] = index[i];
                    count[i] = temp; index[i] = tempIndex;
                }
            }
        }        
    }

    // Esta funcao recebe uma lista de palavras de tamanho "pos" e suas ocorrencias e a imprime.
    public static void printIndex (String[] index, int[] count, int pos) {
        for (int j = 0; j < pos; j++)
            StdOut.println (index[j] + " " + count[j]);  
    }

    // Funcao principal.
    public static void main (String[] args) {
        String[] index = rawIndex ();
        joinEqualStr (index);

        int[] count = new int[index.length];
        int pos = trimCount (index, count);
        
        sortDescending (index, count, pos);
        printIndex (index, count, pos);
    }
}
