/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Numero USP: 8962078
 *  Tarefa: Creative Exercise 3.1.52 (Word frequencies; IntroCS)
 *  URL: http://introcs.cs.princeton.edu/java/31datatype/
 *  Data: 05/03/2016
 *
 *  Recebe um arquivo de texto por entrada padrao no qual existem numero no
 *  final de cada linha. Organiza esse arquivo pela ordem decrescente desses
 *  numeros.
 *
 ******************************************************************************/
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.*;

public class SortNumberLast {
    public static void main(String[] args) {
        String[] palavras = StdIn.readAll().split("[\\n]+");
        Arrays.sort(palavras, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int ai = Integer.parseInt(a.substring(a.lastIndexOf(" ")+1));
                int bi = Integer.parseInt(b.substring(b.lastIndexOf(" ")+1));
                return bi-ai;
            }
        });
        for (int i = 0; i < palavras.length; i++)
            StdOut.println(palavras[i]);
    }
}
