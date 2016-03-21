/*
   Exercício 52 de http://introcs.cs.princeton.edu/java/31datatype/

   Implementa um contador para as palavras

   Gabriel Kuribara Lasso
   NUSP 9298016
 */
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Palavra {
    public int count;
    public final String pal;

    public Palavra (String p) {
        pal = p;
        count = 1;
    }
}
