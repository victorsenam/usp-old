import java.util.*;
public class WordFrequencies {
    public static String format (String s) {
        return s.replaceAll ("[^a-zA-Z-]", "").toLowerCase ();
    }

    public static void main (String[] args) {
        String s;
        Map<String, Integer> ss = new HashMap<String, Integer> ();
        while (!StdIn.isEmpty ()) {
            s = format (StdIn.readString ());
            if (ss.containsKey (s)) {
                ss.put (s, ss.get (s) + 1);
            }
            else {
                if (!s.isEmpty ()) ss.put (s, 1);
            }
        }
        //Transforma o mapa em uma lista de pares
        ArrayList<Map.Entry<String, Integer>> lista = new ArrayList<Map.Entry<String, Integer>> (ss.entrySet ());
        //Ordena a lista com a funcao compare
        Collections.sort (lista, new Comparator<Map.Entry<String, Integer>> () {
            public int compare (Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                //Se as palavras apareceram em mesma quantidade, ordena lexicograficamente
                if (a.getValue ().intValue () == b.getValue ().intValue ()) {
                    return a.getKey ().compareTo (b.getKey ());
                }
                else return  b.getValue ().intValue () - a.getValue ().intValue ();
            }
        });
        for (int i = 0; i < lista.size (); i++) {
            StdOut.println (lista.get (i).getKey () + " " + lista.get (i).getValue ());
        }
    }
}
