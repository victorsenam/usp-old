/*******************************************************************************
 * MAC0323 2016
 * Creative Exercise 3.1.52 (Word frequencies; IntroCS)
 * 
 * Arthur Vieira Barbosa
 * nUSP 6482041
 ******************************************************************************/

public class Word { 
    public final String string;
    private int freq;
    
    public Word(String string) {
        this.string = string;
        freq = 1;
    }
    
    public int getFreq() {
        return freq;
    }
    
    public void increaseFreq() {
        freq++;
    }
    
    public int compare(Word w) {
        if (freq != w.getFreq())
            return w.getFreq() - freq;
        return this.string.compareTo(w.string);
    }
    
    public void print() {
        System.out.println(string + " " + freq);
    }
}
