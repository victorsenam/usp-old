/*
 * Autor: Luis Gustavo Bitencourt Almeida
 * Numero USP: 9298207
 */


import java.util.HashMap;
import java.util.TreeMap;
import java.util.Comparator;
import java.text.Normalizer;

public class WordFrequencies {

    public static void main (String[] args) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        TreeMap<String, Integer> treeMap;

        while (!StdIn.isEmpty()) {
            String in = new String();
            in = StdIn.readString();
            in = in.toLowerCase();
            in = stripAccents(in);
            if (map.containsKey(in)) {
                int count = map.remove(in);
                map.put(in, count+1);
            } else {
                map.put(in, 1);
            }
        }

        treeMap = new TreeMap<String, Integer>(new MapComparator(map));
        treeMap.putAll(map);
        for (HashMap.Entry<String, Integer> entry : treeMap.entrySet())
            StdOut.println(entry.getKey() + " " + entry.getValue());
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

}

class MapComparator implements Comparator<String> {
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    public MapComparator(HashMap<String, Integer> map) {
        this.map.putAll(map);
    }

    @Override
    public int compare(String a, String b) {
        return (map.get(a) < map.get(b)) ? 1 : -1;
    }
}
