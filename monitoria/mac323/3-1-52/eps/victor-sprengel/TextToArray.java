// Victor Andreas Sprengel
// 9298002

import java.util.ArrayList;

public class TextToArray {

    // Recebe uma String que eh uma das linhas do texto e adciona as palavras
    // no arraylist de strings.
    static void addWordsFromLineToArray(String line, ArrayList<String> text) {
        line = line.toLowerCase();
        String[] allWords = line.split("[^0-9\\p{L}-]");

        for (int i = 0; i < allWords.length; i++)
	        if (allWords[i].length() > 0)
                text.add(allWords[i]);
    }

    // Funcao que le da entrada padrao um texto, transforma-o num arraylist
    // de Strings e devolve esse vetor.
    public static ArrayList<String> writeTextToArray() {
        ArrayList<String> text = new ArrayList<>();

        while(StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            addWordsFromLineToArray(line, text);
        }

        return text;
    }

}

