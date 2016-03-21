/*
 * @author: Eduardo Pinheiro
 * NUSP:    8936798
 */

public class Word implements Comparable<Word>
{
  private String word;
  private int frequence;
  
  public Word(String word) { /* Construtor */
    this.setWord(word);
    this.setFrequence(1);
  }

  /* getters e setters */
  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public int getFrequence() {
    return frequence;
  }

  public void setFrequence(int frequence) {
    this.frequence = frequence;
  }

  public String toString() {
    return word + " " + frequence; /* utilizado pelo método print */
  }

  @Override
  public int compareTo(Word o) { /* Método utilizado para fazer a ordenação */
    return o.frequence - this.frequence; /* retorna um número positivo caso this.frequence seja menor, negativo caso contrário e 0 caso iguais*/
  }
}
