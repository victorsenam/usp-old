// Victor Andreas Sprengel
// 9298002

import java.util.ArrayList;
import java.util.Collections;

public class WordFrequencies {

    public static void printDictionaryAndFrequency(ArrayList<Word> dic) {
	    for (int j = 0; j < dic.size(); j++)
	        System.out.println(dic.get(j));
    }

    // Le da entrada padrao um texto e imprime as palavras contidas nele
    // em ordem decrescente de frequencia, com a respectiva frequencia
    // ao lado.
    //
    // Metodo de resolucao:
    //
    // Pega cada linha da entrada, joga em uma string, da split por
    // nao eh alfanumerico ou hifen, e depois joga cada palavra numa
    // arraylist.
    //
    // Depois, ordena lexicograficamente essa arraylist
    //
    // Em seguida, remove as repeticoes de palavras e decide a frequencia
    // de cada uma. Transforma o arraylist de String num arraylist de Word
    //
    // Por fim, ordena pela frequencia e imprime.
    public static void main(String[] args) {
        ArrayList<String> dictionary = TextToArray.writeTextToArray();
    	Collections.sort(dictionary);
        ArrayList<Word> finalDictionary = Repetitions.removeRepetitions(dictionary);
	    Collections.sort(finalDictionary);
        printDictionaryAndFrequency(finalDictionary);
    }

}

