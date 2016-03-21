/**********************************************************
	André Luiz Abdalla Silveira
	8030353
	Depende de MyTree.java
	C: $ javac-algs4 WordFrequencies.java
	E: $ java-algs4 WordFrequencies < texto.txt

**********************************************************/

import edu.princeton.cs.algs4.*;

public class WordFrequencies {

	public static void main(String[] args) {
		String[] words = StdIn.readAllStrings();
		
		MyTree raiz = new MyTree(words[0].toLowerCase());
		
		for (int l = 1; l < words.length; l++) {
			MyTree.Insere_folha (words[l].toLowerCase(), raiz);		
		}

		MyTree.printaTree(raiz);	
	}
}