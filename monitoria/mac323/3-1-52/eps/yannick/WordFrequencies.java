/* ********************************** *
 * Nome: Yannick Thomas Messias       *
 * NUsp: 8803834                     *
 * ********************************** */

import java.util.*;
import edu.princeton.cs.algs4.*;
public class WordFrequencies{
  public static void main(String[] args){
    ST<String, Integer> st  = new ST<String, Integer>();
    ST<Integer, String> st2 = new ST<Integer, String>();
    
    // read and save all the words in the text and count their appearances
    while(!StdIn.isEmpty()){
      String word = StdIn.readString().toLowerCase();
      if(st.contains(word))
        st.put(word, st.get(word)+1);
      else
        st.put(word, 1);
    }
    
    // classify the words by order of frequency
    for(String s : st.keys()){
      int count = st.get(s);
      if(st2.contains(count))
        st2.put(count, st2.get(count) + "\n" + s);
      else
        st2.put(count, s);
    }
    
    // print all the words in decreasing order of frequency
    while(!st2.isEmpty()){
      int max = st2.max();
      StdOut.println(st2.get(max) + " " + max);
      st2.delete(max);
    }
  }
}
