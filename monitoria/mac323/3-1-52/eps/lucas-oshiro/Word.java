//Nome: Lucas Seiki Oshiro
//Número USP: 9298228
import edu.princeton.cs.algs4.*;

public class Word {
    String s;
    int f;
    Word left;
    Word right;

    /* Construtor */
    public Word (String st) {
        s = st;
        f = 1;
        left = null;
        right = null;
    }

    /* Incrementa a frequencia */
    public void incFreq () {
        f++;
    }

    /* Compara o objeto com w2 lexicograficamente. Devolve um numero menor que
       zero quando for menor que w2, igual a zero quando forem iguais, ou maior
       que zero quando for maior que w2. */
    public int compareLex (Word w2) {
        return s.compareTo (w2.s);
    }

    /* Compara a frequência do objeto com w2. Devolve um numero menor que zero
       quando for menor que a de w2, igual a zero quando as frequências forem
       iguais ou maior que zero a frequencia do objeto for maior que a de w2. */
    public int compareFreq (Word w2) {
        return f - w2.f;
    }

    /* Devolve uma string com a palavra e sua frequencia. */
    public String toString () {
        return s + " " + f;
    }
}
