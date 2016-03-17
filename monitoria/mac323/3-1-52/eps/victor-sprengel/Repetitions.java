// Victor Andras Sprengel
// 9298002

import java.util.ArrayList;

public class Repetitions {

    // Recebe uma ArrayList de Strings e devolve um ArrayList de Word
    // cada palavra aparece uma vez e com sua frequencia correta.
    public static ArrayList<Word> removeRepetitions(ArrayList<String> dic) {
	    ArrayList<Word> newdic = new ArrayList<>();
        int firstO = 0; // de firstOccurrence
        int f = 1; // de frequency

        while (firstO < dic.size()) {
            while (firstO + f < dic.size() && dic.get(firstO).equals(dic.get(firstO + f)))
                f += 1;
            Word w = new Word(dic.get(firstO), f);
            newdic.add(w);
            firstO += f;
            f = 1;
        }

        return newdic;
    }

}

