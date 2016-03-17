import edu.princeton.cs.algs4.*;
import java.util.*;

/* Exercise 3.1.52 (Word frequencies) 
 * Nome: Germano Huning Neuenfeld
 * Número USP: 9298340
 * Data: 12/03/2016*/

public class wordFrequencies {
    class Pair {
        private String str;
        private Integer value;
        public Pair(String s, Integer val){
            str = s;
            value = val;
        }
        public String key() { 
            return str; 
        }
        public Integer value() { 
            return value; 
        }
    }
    public static void main (String[] args) {
        char c;
        HashMap<String, Integer> hm = new HashMap<String, Integer> ();
        /* recebe as palavras do texto e coloca no treemap*/
        while (StdIn.hasNextChar ()) {
            String pal;
            do {
                if (StdIn.hasNextChar ()) 
                    c = StdIn.readChar ();
                else break; 
            } while (!Character.isLetter (c) && !Character.isDigit (c));
            int i = 0;
            do {
                pal = "" + Character.toLowerCase (c);
                if (StdIn.hasNextChar ())
                    c = StdIn.readChar ();
                else break;
            } while (Character.isLetter (c) && Character.isLetter (c));
            if (hm.containsKey (pal)) {
                hm.put (pal, hm.get (pal)+1);
            } else {
                hm.put (pal, 1);
            }
        }
        ArrayList< Pair > al = new ArrayList< Pair > ();
        Set mapset = hm.entrySet ();
        Iterator it = mapset.iterator ();
        while (it.hasNext ()) {
            Map.Entry b = (Map.Entry)it.next ();
            String str = new String (b.getKey ().toString ()); 
            int a = Integer.valueOf (b.getValue ().toString ());
            Integer val = new Integer (a);
            al.add (Pair (str, val));
        }
        Collections.sort (al, new Comparator<String> () {
            public int compare (String s1, String s2) {
                int freq1 = al.value (s1);
                int freq2 = al.value (s2);
                if (freq1 > freq2) return 1;
                else return 0;
            }            
        });
        for (int i = 0; i < al.size (); i++) {
            StdOut.println (al.key (i));
        }
    }
}







