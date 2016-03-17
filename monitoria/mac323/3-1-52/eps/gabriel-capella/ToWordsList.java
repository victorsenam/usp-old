/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Numero USP: 8962078
 *  Tarefa: Creative Exercise 3.1.52 (Word frequencies; IntroCS)
 *  URL: http://introcs.cs.princeton.edu/java/31datatype/
 *  Data: 05/03/2016
 *
 *  Recebe na entrada padrao um arquivo texto e transforma suas palavras em uma
 *  lista, na ordem que aparecem no texto. 
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.*;
public class ToWordsList {
    public static void main(String[] args) {
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            String[] palavras = line.split("[\\p{Punct}\\s&&[^-]]+");
            for (int i = 0; i < palavras.length; i++)
                StdOut.println(palavras[i].toLowerCase());
        }
    }
}
