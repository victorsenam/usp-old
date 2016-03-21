/***************************************************************/
/**                                                           **/
/**   Lucas Moreira Santos                          9345064   **/
/**   Creative Exercise 3.1.52                                **/
/**   Professor:  Yoshiharu Kohayakawa                        **/
/**   MAC0323 - Algoritmos e Estruturas de Dados II           **/
/**                                                           **/
/***************************************************************/

import java.util.*;

public class Pair {
  private int frequency;
  private String word;

  public Pair(int freq, String str) {
    frequency = freq;
    word = str;
    char c = word.charAt(word.length() - 1);

    if (c != '-' && !Character.isLetter(c)) {
      word = word.substring(0, word.length() - 1);
    }
  }

  // Getter para o atributo word
  public String getWord() {
    return this.word;
  }

  // Getter para o atributo word
  public int getFrequency() {
    return this.frequency;
  }

  public void increaseFrequency() {
    this.frequency++;
  }
}

class PairComparator implements Comparator<Pair> {
  public int compare(Pair x, Pair y) {
    if (x.getFrequency() == y.getFrequency()) return x.getWord().compareTo(y.getWord()); // Que coisa feia, né não?
    else return (y.getFrequency() - x.getFrequency());
  }
}
