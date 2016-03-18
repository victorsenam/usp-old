/*
 * @author: Eduardo Pinheiro
 * NUSP:    8936798
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.*;

public class WordFrequencies
{
  public static List<Word> WordsIn() {
    String line;
    String[] Words;
    boolean flag = true;
    List<Word> list = new ArrayList<Word>(); /* utiliza o tipo ArrayList do próprio java, que possui uma série de otimizações */

    while (!StdIn.isEmpty()) {
        line = StdIn.readLine();

        /* Remove pontuação */
        line = line.replace(",", "");
        line = line.replace("!", "");
        line = line.replace(".", "");
        line = line.replace("?", "");
        line = line.replace("...", "");
        line = line.replace("(", "");
        line = line.replace(")", "");
        line = line.replace("*", "");
        line = line.replace("[", "");
        line = line.replace("]", "");

        line = line.toLowerCase();

        Words = line.split(" "); /* Separa cada palavra em uma String */

        /* Coloca cada palavra em uma lista não ordenada, e remove repetições calculando a frequência de cada palavra no texto */
        for (int i = 0; i < Words.length; i++) {
          for (int j = 0; j < list.size(); j++) {
            if (list.get(j).getWord().equals(Words[i])) {
              list.get(j).setFrequence(list.get(j).getFrequence() + 1);
              flag = false;
              break;
            }
          }
          if (flag)
            list.add(new Word(Words[i]));

          flag = true;
        }
    }
    return list;
  }

  public static void main(String[] args) {
    List<Word> Sorted = WordsIn();

    Collections.sort(Sorted); /* Garante desempenho da ordem de O(N*logN) */

    for (int i = 0; i < Sorted.size(); i++)
      System.out.println(Sorted.get(i));
  }
}
