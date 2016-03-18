//Nome: Lucas Seiki Oshiro
//Numero USP; 9298228
import edu.princeton.cs.algs4.*;

/* Compile com javac-algs4 WordFrequencies.java
   Rode com java-algs4 WordFrequencies < in.txt para ler o arquivo in.txt,
   ou use java-algs4 para digitar o texto, usando Enter Ctrl-D para terminar. */

public class WordFrequencies {
    
    /* Main*/
    public static void main (String[] args) {
        Dict d = new Dict ();
        
        while (StdIn.hasNextChar ()) {
            String s = "";
            char c = StdIn.readChar ();

            while (!(Character.isLetter (c) || c == '-') && StdIn.hasNextChar ())
                c = StdIn.readChar ();
            
            while (Character.isLetter (c) || c == '-') {
                s = s + c;
                c = StdIn.readChar ();
            }

            d.addWord (s);
        }
        
        StdOut.print (d);
    }
}
