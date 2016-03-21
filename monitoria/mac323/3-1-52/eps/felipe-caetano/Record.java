/******************************************************************************
*  Nome: Felipe Caetano Silva
*  USP: 9293223
*  Classe Record.java usada no livro com uma alteracao abaixo comentada
******************************************************************************/
import edu.princeton.cs.algs4.*;

public class Record implements Comparable<Record> {
    public final String name;
    public final long val;

    public Record(String name, long val) {
        this.name = name;
        this.val = val;
    }

    public int compareTo(Record that) {
        if      (this.val < that.val) return -1;
        else if (this.val > that.val) return +1;
        else return 0; 
    }
    // val vai para string como -val, pois utilizei freq
    // negativa no programa WordFrequencies
    public String toString() {
            return String.format("%s %d", name, -val);
    }
}
