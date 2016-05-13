import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.lang.String;
import java.util.TreeMap;
import java.math.BigInteger;

public class WordDAG {
    private Out out;
    private TreeMap<String,Integer> mp;
    private ArrayList<Integer>[] adj;
    private String[] inp;

    private Integer[] lastCalc;
    private BigInteger[] countPaths;

    public WordDAG (String[] strings) {
        inp = strings;
        mp = new TreeMap<String,Integer>();
        //adj = (ArrayList<Integer>[]) new Object[inp.length];
        adj = new ArrayList[inp.length];
        for (int i = 0; i < inp.length; i++)
            adj[i] = new ArrayList<Integer>();

        for (int i = 0; i < inp.length; i++) {
            mp.put(inp[i], i);

            int len = inp[i].length();

            for (int j = 0; j <= len; j++) {
                char[] aux;
                if (j == len)
                    aux = (inp[i] + (char) 1).toCharArray();
                else
                    aux = inp[i].toCharArray();

                for (char k = 32; k < 127; k++) {
                    aux[j] = k;
                    String s = String.valueOf(aux).trim();
                    if (k == 32 && j != len-1) continue;

                    if (inp[i].compareTo(s) == 0) continue;

                    Integer val = mp.get(s);
                    if (val != null) {
                        if (inp[i].compareTo(inp[val]) < 0)
                            adj[val].add(i);
                        else
                            adj[i].add(val);
                    }
                }
            }
        }

        out = new Out("saida.txt");
        countPaths = new BigInteger[inp.length];
        lastCalc = new Integer[inp.length];
    }

    public void PrintDag () {
        for (int i = 0; i < adj.length; i++)
            for (int j = 0; j < adj[i].size(); j++)
                out.println(inp[i] + " " + inp[adj[i].get(j)]);
    }

    BigInteger PathCount (int u, int v) {
        if (u == v) return BigInteger.valueOf(1);
        if (lastCalc[u] != null && lastCalc[u] == v) 
            return countPaths[u];
        lastCalc[u] = v;

        countPaths[u] = BigInteger.valueOf(0);

        for (int i = 0; i < adj[u].size(); i++) {
            countPaths[u] = countPaths[u].add(PathCount(adj[u].get(i), v));
        }

        return countPaths[u];
    }

    public void PrintPathCount (String orig, String dest) {
        int u = mp.get(orig);
        int v = mp.get(dest);
        out.println(PathCount(u, v));
    }

    public static void main (String args[]) {
        String a, b;
        a = StdIn.readString();
        b = StdIn.readString();
        WordDAG dag = new WordDAG(StdIn.readAllStrings());
 //       dag.PrintDag();
        dag.PrintPathCount(a, b);
    }
};
