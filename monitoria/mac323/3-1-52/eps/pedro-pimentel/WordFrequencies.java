/******************************************************************************
 *  Compilation:  javac WordFrequencies.java
 *  Execution:    java WordFrequencies
 *
 *  Problema 3.1.52 - Web 
 *  Pedro Sola Pimentel RM 9298079
 *
 ******************************************************************************/

/* Esta e a classe principal do programa, e alocada
   lista de palavras do tipo Word, e ordenada, e em seguida
   impressa na tela. Para informacoes sobre os metodos criados,
   verifique as classes WordList.java e Word.java */

public class WordFrequencies {

    public static void main (String args[]) {
            WordList wordl = new WordList (); /* Cria Lista */
            wordl.sort (); /* Ordena Lista */
            wordl.print(); /* Imprime a lista */
    }

}