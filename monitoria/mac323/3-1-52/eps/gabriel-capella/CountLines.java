/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Numero USP: 8962078
 *  Tarefa: Creative Exercise 3.1.52 (Word frequencies; IntroCS)
 *  URL: http://introcs.cs.princeton.edu/java/31datatype/
 *  Data: 05/03/2016
 *
 *  Une as linhas repetidas de um arquivo texto e coloca no final o numero de
 *  linhas repetidas. Ignora linhas brancas.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.*;
public class CountLines {
    public static void main(String[] args) {
        String anterior = "";
        int count = 0;
        while (StdIn.hasNextLine()) {
            String atual = StdIn.readLine();
            if (atual.length() == 0) continue;
            if (atual.equals(anterior)) count++;
            else {
                if (count > 0) StdOut.println(anterior+" "+count);
                anterior = atual;
                count = 1;
            }
        }
        if (count > 0) StdOut.println(anterior+" "+count);
    }
}
