public class CorretorDoVictor {

    public static void main (String[] args) {
        TrieST<Integer> ex = new TrieST<Integer>();
        int indexed = 0;
        if (args.length > 0 && args[0].charAt(0) == 'i')
            indexed++;
        int n = StdIn.readInt();

        for (int i = 2; i < n+2; i++) {
            StdIn.readChar();
            char c = StdIn.readChar();
            StdIn.readChar();

            if (c == 'a') {
                ex.put(StdIn.readString(), i);
            } else if (c == 'r') {
                StdOut.println(ex.rank(StdIn.readString()) - indexed);
            } else if (c == 'f') {
                StdOut.println(ex.floor(StdIn.readString()));
            } else if (c == 'c') {
                StdOut.println(ex.ceil(StdIn.readString()));
            } else if (c == 's') {
                StdOut.println(ex.select(StdIn.readInt() + indexed));
            }
        }
    }
}
