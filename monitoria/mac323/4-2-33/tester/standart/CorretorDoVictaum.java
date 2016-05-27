public class CorretorDoVictaum {
    public void main (String[] args) {
        int n = parseInt(args[0]);
        int m = parseInt(args[1]);

        String[] words = new String[n];

        for (int i = 0; i < n; i++)
            words[i] = StdIn.readString();
    
        WordDag ex = new WordDag(words);
        ex.PrintDag();
        
        for (int i = 0; i < m; i++) {
            int a = StdRandom.uniform(n);
            int b = StdRandom.uniform(n);

            ex.PrintPathCount(a, b);
        }
    }
}
