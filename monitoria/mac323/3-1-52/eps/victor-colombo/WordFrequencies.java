import java.util.*;
import edu.princeton.cs.algs4.*;


public class WordFrequencies {

  private static class Pair implements Comparable<Pair> {
    private String word;
    private int occurences;
    private Pair(String word, int occurences) {
      this.word = word;
      this.occurences = occurences;
    }
    @Override
    public int compareTo(Pair pair) {
      if(occurences == pair.occurences)
        return word.compareTo(pair.word);
      else
        return (occurences > pair.occurences) ? -1 : ((occurences == pair.occurences) ? 0 : 1);
    }
  }

  public static void main(String[] args) {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    Scanner in  = new Scanner(System.in);
    while(in.hasNext()) {
      String word = in.next();
      word = word.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}\\-]+", "");
      if(word.isEmpty())
        continue;
      if(map.get(word) == null)
        map.put(word, 1);
      else
        map.put(word, map.get(word) + 1);
    }
    ArrayList<Pair> list = new ArrayList<Pair>();
    for(Map.Entry<String, Integer> pair : map.entrySet())
      list.add(new Pair(pair.getKey(), pair.getValue()));
    Collections.sort(list);
    for(Pair pair: list)
      StdOut.printf("%s %d\n", pair.word, pair.occurences);
  }

}
