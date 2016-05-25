import edu.princeton.cs.algs4.StdIn;

public class MyTester {

    public static void main (String[] args) {
        List<Integer> ex = new List<Integer>();
        int indexed = 0;
        if (args.length > 0)
            indexed++;
        int n = StdIn.readInt();

        for (int i = 0; i < n; i++) {
            StdIn.readChar();
            char c = StdIn.readChar();
            StdIn.readChar();

            if (c == 'a') {
                int a = StdIn.readInt() + indexed;
                int b = StdIn.readInt();
                ex.add(a,b);
            } else if (c == 'd') {
                int a = StdIn.readInt() + indexed;
                StdOut.println(ex.delete(a));
            } else if (c == 'g') {
                int a = StdIn.readInt() + indexed;
                StdOut.println(ex.get(a));
            } else if (c == 'f') {
                int a = StdIn.readInt();
                ex.addFront(a);
            } else if (c == 'b') {
                int a = StdIn.readInt();
                ex.addBack(a);
            } else if (c == 'F') {
                StdOut.println(ex.deleteFront());
            } else if (c == 'B') {
                StdOut.println(ex.deleteBack());
            } else if (c == 's') {
                    StdOut.println(ex.size());
            } else if (c == 'e') {
                StdOut.println(ex.isEmpty());
            } else if (c == 'c') {
                int a = StdIn.readInt();
                StdOut.println(ex.contains(a));
            } else if (c == 'D') {
                Integer a = StdIn.readInt();
                ex.delete(a);
            } else if (c == 'i') {
                for (Integer el : ex)
                    StdOut.println(el);
            }
        }
    }
}
