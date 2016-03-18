/*
   Exercício 52 de http://introcs.cs.princeton.edu/java/31datatype/

   Programa principal que lida com entrada e saida

   Gabriel Kuribara Lasso
   NUSP 9298016
 */
import java.util.*;
import edu.princeton.cs.algs4.*;

public class WordFrequencies {
    public static void main (String[] args) {
        Dicionario dic = new Dicionario ();
        char c;
        String pal = new String ();
        while (!StdIn.isEmpty()) {
            c = StdIn.readChar ();
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '-' || c >= 'À' && c <= 'Ö' || c >= 'Ø' && c <= 'ö' || c >= 'ø')
                pal = pal + c;
            else {
                if (!pal.isEmpty())
                    dic.insere (pal.toLowerCase());
                pal = new String ();
            }
        }
        if (!pal.isEmpty())
            dic.insere (pal.toLowerCase());
        dic.freqPrint ();
    }
}
