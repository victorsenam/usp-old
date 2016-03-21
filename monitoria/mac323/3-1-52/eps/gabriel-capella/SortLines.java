/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Numero USP: 8962078
 *  Tarefa: Creative Exercise 3.1.52 (Word frequencies; IntroCS)
 *  URL: http://introcs.cs.princeton.edu/java/31datatype/
 *  Data: 05/03/2016
 *
 *  Recebe um arquivo de texto por entrada padrao e ordena as linhas por ordem
 *  lexografica.
 *
 ******************************************************************************/
import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class SortLines {
    public static void main(String[] args) {
        String[] palavras = StdIn.readAll().split("[\\s]+");
        Arrays.sort(palavras);
        for (int i = 0; i < palavras.length; i++)
            StdOut.println(palavras[i]);
    }
}
