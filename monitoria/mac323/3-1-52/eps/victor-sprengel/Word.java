// Victor Andreas Sprengel
// 9298002

public class Word implements Comparable<Word> {
    String content;
    int frequency;

    public Word(String w, int frequency) {
        this.content = w;
        this.frequency = frequency;
    }

    public void addToFrequency(int n) {
	    this.frequency += n;
    }

    public String toString() {
        return (this.content + ' ' + this.frequency);
    }

    public int compareTo(Word otherword) {
	    return (otherword.frequency - this.frequency);
    }

    public static void main(String[] args) {
        Word myWord = new Word("abacate", 4);
        myWord.addToFrequency(1); // abacate 5
        System.out.println(myWord);
    }

}

