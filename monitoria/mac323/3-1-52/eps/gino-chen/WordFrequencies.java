/*************************************
 * 
 * MAC0323 - 1º sem de 2016
 * 
 * São Paulo, 12 de março de 2016
 * 
 * Ex. 3.1.52 (Word frequencies) 
 *
 * Gino Chen Hsiang-Jan
 * Número USP: 748536
 * 
*************************************/

/*
 * Será criado uma classe de objetos WordCount, privada que instanciado irá armazernar a palavra e a ocorrência.
 * Essa classe será um "nó" da classe genérica Map, que é um Hash table, onde a chave será a palavra a ser contada.
 * 
 * O parse para cada palavra será feito caracter por caracter. Através do método WordCount.parseText.
 * 
 * A ordenação será feita pelo método WordCount.retriveSortedListByCount, que irá retornar a lista de palavras 
 * ordenadas reversamente pelo contador de palavras.
 * 
 * Há um método WordCount.sortWordCountArrayByCount para fazer um quick sort, como o objetivo não é avaliar algoritmos de
 * ordenação, adaptei o código forneceido pelo livro: Sedgewick and Wayne. Algorithms, 4th edition.
 * 
 * Não está especificado como deve ser o comportamento hifen, eu estou supondo que deve ser precedido de uma letra válida. 
 * Caso contrário não será parte de uma palavra.
 * 
 * O método WordCount.showWordCountArray mostra na saída padrão as palavras e contadores ordenados reversamente pela sua 
 * ocorrência.
 * 
 */

import java.util.*;
import edu.princeton.cs.algs4.*;

// Classe que armazena um agregado de uma palavra e um contador
class WordCount {
	// Palavra a ser contada
	private String word ;
	// Número de ocorrências da palavra word
	private int    count;
	
	// Construtor, inicia a classe com a palavra e inicia o contador com 1
	public WordCount(String word) {
		this.word  = word;
		this.count = 1   ;
	}
	// Retorna a Palavra 
	public String getWord() {
		return word;
	}
	// Retorna o número de ocorrências da palavra
	public int getCount() {
		return count;
	}
	// Incrementa o número de ocorrências da palavra
	public void incrementCount() {
		++count;
	}
	// Retorna a concatenação entre a palavra e seu número de ocorrências
	public String toString() {
		return word + " " + count;
	}
	// Comparação entre dois objetos WordCount, como o exercício pede ordenação por ocorrências
	// está sendo comparado, em ordem reversa, apenas o contador
	public int compareTo(WordCount w) {
		//return w.count - count;
		
		 // Caso queiramos ordernar as palavras se o contador for igual, descontar esse código.
		 int n = w.count - count;
		 
		 if (n == 0)
		 	return word.compareTo(w.word);
		 
		 return n;
		
	}
}

// Classe principal.
public class WordFrequencies {
	// Hash table onde estão armazenados as palavras, objetos da classe WordCount
	static private Map<String, WordCount> hashWord;
	
	static public void main(String[] args) {
		// Constrói do Hash table dos objetos WordCount (palavras e contadores)
		// Chave do hash é a palavra a ser contada.
		hashWord = new HashMap<String, WordCount>();
		// Analisa o texto e popula hashWord
		parseText();
		
		// Retorna um vetor de WordCount, contendo a tabela das palavras e contadores ordenadas por contador em ordem reversa 
		WordCount[] wordCountArray = retriveSortedListByCount();
		
		// Mostra na saída padrão as palavras ordenadas em ordem reversa por contador
		showWordCountArray(wordCountArray);
				
	}
	
	// Analisa a entrada padrão, filtra somente as palavras, verifica se ela está em hashWord se tiver, incrementa o contador, 
	// caso constrário, cria um novo, com contador 1
	static public void parseText() {
		// Caracter a ser lido
		char    c             ;
		// Se a análise da palavra lida atualmente está dentro do filtro de palavra ou não
		boolean inWord = false;
		// Sequência contínua de caracteres considerada palavra
		String  Word   = ""   ;

		while (!StdIn.isEmpty()) {
			c = StdIn.readChar();
			// Se tem uma sequência de letras anteriormente
			if (inWord) {
				// Hífem nesse caso só é aceito se for seguido de letra (não foi especificado, implementei assim)
				if (c == '-' || Character.isLetter(c))
					// Adiciona uma letra ou hífem
					Word += Character.toLowerCase(c);
				// cc, indica que a palavra terminou
				else {
					// Fim da palvra contínua, foi encontra algum caracter não letra
					inWord = false;
					// Verifica se a palavra está em hashWord 
					if (hashWord.containsKey(Word))
						// Incrementa o contador da palavra existente em hashWord
						hashWord.get(Word).incrementCount();
					// Se a palavra não está em hashWord, contrói um WordCount 
					else
						hashWord.put(Word, new WordCount(Word));
					//System.out.println(Word);
				}
			}
			// Se foi encontrada uma letra no começo ou posterior a um não letra (hífem "solto" não está implementado para ser início de palavra) 
			else {
				if (Character.isLetter(c)) {
					inWord = true;
					// é um "cast" mais fácil de caracter para String
					Word = "" + Character.toLowerCase(c);
				}
				// else não faz nada, ignora o caracter.
			}
		}
	}
	
	// Retorna um vetor de WordCount ordenado por contador em ordem reversa 
	static public WordCount[] retriveSortedListByCount() {
		// Cria um WordCount[] com o número de palavras encontradas e popula os elementos  
		WordCount[] wordCountArray = hashWord.values().toArray(new WordCount[0]);
		
		// Ordena por contador em ordem reversa
		sortWordCountArrayByCount(wordCountArray);
		
		return wordCountArray;
	}
	
	// quick sort
	static public void sortWordCountArrayByCount(WordCount[] a) {
		sortWordCountArrayByCount(a, 0, a.length - 1);
    }
	
	// exibe na saida padrão o vetor WordCount[] 
	static public void showWordCountArray(WordCount[] a) {
		for (int i = 0; i < a.length; ++i)
			StdOut.println(a[i].toString());
	}

    // quicksort the subarray from a[lo] to a[hi]
	static private void sortWordCountArrayByCount(WordCount[] a, int lo, int hi) { 
        if (hi <= lo) 
        	return;
        
        int j = partition(a, lo, hi);
        
        sortWordCountArrayByCount(a, lo, j-1);
        sortWordCountArrayByCount(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(WordCount[] a, int lo, int hi) {
        int       i = lo    ;
        int       j = hi + 1;
        WordCount v = a[lo] ;
        
        while (true) { 
            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }


   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    static private boolean less(WordCount v, WordCount w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    static private void exch(WordCount[] a, int i, int j) {
    	WordCount swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
	
}
