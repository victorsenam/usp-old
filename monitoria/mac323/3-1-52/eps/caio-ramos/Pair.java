/* Caio Ramos - NUSP 9292991 - IME USP 2016
    Classe que realiza as operacoes com o objeto Pair, constituido de String + numero de repeticoes.
*/


import java.util.Arrays;
import java.util.Comparator;


public class Pair {
    private String s;
    private int n;

    //Construtor para os pares String + numero de aparicoes
    public Pair (String s, int n) {
        this.s = s;
        this.n = n;
    }

    public static void sort(Pair[] v, int M) {
        //ordena o vetor de pares segundo o criterio de repeticoes
        Arrays.sort(v, 0, M, PairComparator);

    }

    //incrementa o valor de n
    public void increment() {
        n++;
    }

    //formata a impressao do objeto
    public String toString() {
        String s = this.s + " " + n;
        return s;
    }

    //devolve o valor de n
    public int getn() {
        return n;
    }

    //metodos para a funcao de comparacao para a ordenacao segundo # de aparicoes
    public int compareTo(Pair compareFruit) {
		int compareQuantity = ((Pair) compareFruit).getn();
		return this.n - compareQuantity;
	}
	public static Comparator<Pair> PairComparator
                          = new Comparator<Pair>() {
	    public int compare(Pair a, Pair b) {
	      return b.compareTo(a);
	    }
	};
    
}
