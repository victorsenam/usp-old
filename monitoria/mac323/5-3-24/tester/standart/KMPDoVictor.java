public class KMPDoVictor {

    public static void main (String[] args) {
        KMP ex = new KMP(StdIn.readString());

        int n = StdIn.readInt();
        StdIn.readChar();

        for (int i = 2; i < n+2; i++) {
            for (int i : ex.findAll(StdIn.readString()))
                StdOut.print(i + " ");
            StdOut.println();
        }
    }
}
