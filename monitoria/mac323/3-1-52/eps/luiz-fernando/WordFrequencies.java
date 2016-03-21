/*////////////////////////////////////////////////////////////////
//
// Autor: Luiz Fernando Antonelli Galati                                                    
// Numero USP: 7557223                                                                                 
// Data: 12-03-2016                                             
//                                                               
// Creative Exercise 3.1.52 - Word frequencies
//                                                               
///////////////////////////////////////////////////////////////*/


public class WordFrequencies {

    public static void main (String[] args)
    {
        int SpaceWords, SpaceCh, Tdic, i, j, max;
        boolean psg;
        char c;
        char[] palvr = new char[15];
        String s;
        String[] word = new String[100];
        int[] freq = new int[100];
        
        SpaceWords = 100;
        SpaceCh = 15;
        Tdic = 0; 
        while (StdIn.hasNextChar()) {
            if (Tdic == SpaceWords) {
                word = expandePalavras (SpaceWords, word);
                freq = expandeFrequencias (SpaceWords, freq);
                SpaceWords = SpaceWords*2;
            }
            c = StdIn.readChar ();
            i = 0;
            while (Character.isLetter (c) || Character.isDigit (c)) {
                if (i == SpaceCh) {
                    palvr = expandeCaracteres (SpaceCh, palvr);
                    SpaceCh = SpaceCh*2;
                }                         
                palvr[i] = c;
                i++;
                if (StdIn.hasNextChar ())                    
                    c = StdIn.readChar ();
                else
                    break;
            }
            s = null;
            s = s.copyValueOf (palvr, 0, i);
            s = s.toLowerCase ();
            psg = false;
            for (j = 0; j < Tdic; j++) {
                if (s.equals (word[j])) {
                    freq[j]++;
                    psg = true;
                    break;
                }
            }
            if (!psg) {
                word[Tdic] = new String (s);
                freq[Tdic]++;
                Tdic++;
            }
        }       
        
        while (Tdic > 0) {
            max = 0;
            for (j = 1; j < Tdic; j++) {
                if (freq[j] > freq[max])
                    max = j;                
            }
            StdOut.printf ("%s %d\n", word[max], freq[max]);
            for (j = max; j < Tdic-1; j++) {
                word[j] = new String (word[j+1]);
                freq[j] = freq[j+1];
            }
            Tdic--;
        }
    }
    
    public static char[] expandeCaracteres (int SpaceCh, char[] palvr)
    {
        char[] novaPalvr = new char[SpaceCh*2];
        int i;
        for (i = 0; i < SpaceCh; i++)
            novaPalvr[i] = palvr[i];
        return novaPalvr;
    }

    public static String[] expandePalavras (int SpaceWords, String[] word)
    {
        String[] newWord = new String[SpaceWords*2];;
        int i;
        for (i = 0; i < SpaceWords; i++)
            newWord[i] = word[i];
        return newWord;
    }

    public static int[] expandeFrequencias (int SpaceWords, int[] freq)
    {
        int[] newFreq = new int[SpaceWords*2];;
        int i;
        for (i = 0; i < SpaceWords; i++)
            newFreq[i] = freq[i];
        return newFreq;
    }
}
