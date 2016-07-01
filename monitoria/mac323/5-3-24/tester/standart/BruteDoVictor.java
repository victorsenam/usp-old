public class BruteDoVictor {

    public static void main (String[] args) {
        pat = StdIn.readString();
        int n = StdIn.readInt();
        StdIn.readChar();

        for (int i = 2; i < n+2; i++) {
            for (int i : Brute.findAll(StdIn.readString(), pat))
                StdOut.print(i + " ");
            StdOut.println();
        }
    }
}
