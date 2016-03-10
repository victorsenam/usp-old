/* Nome: Victor Aliende da Matta *
 * NUSP: 9298145                 */
import java.util.Arrays;

public class HubsAndAuthorities {
    public static void main(String[] args) {
        int n = Integer.parseInt (args[0]);
        int m = Integer.parseInt (args[1]);
        int h = Integer.parseInt (args[2]);
        int a = Integer.parseInt (args[3]);

        StdOut.println (n + h + a);
        for (int i = 0; i < m; i++) {
            StdOut.println ((int) Math.floor (Math.random () * (n)) + " " + (int) Math.floor (Math.random () * (n)));
        }

        int pool;
        int[] flags = new int[n];
        for (int i = 0; i < h; i++) {
            pool = Math.max (n / 10, 1);
            Arrays.fill (flags, 0);
            while (pool > 0) {
                int v = (int) Math.floor (Math.random () * (n));
                if (flags[v] == 0) {
                    pool -= 1;
                    StdOut.println (v + " " + (n + i));
                    flags[v] = 1;
                }
            }
        }
        for (int i = 0; i < a; i++) {
            pool = Math.max (n / 10, 1);
            Arrays.fill (flags, 0);
            while (pool > 0) {
                int v = (int) Math.floor (Math.random () * (n));
                if (flags[v] == 0) {
                    pool -= 1;
                    StdOut.println ((n + h + i) + " " + v);
                    flags[v] = 1;
                }
            }
        }
    }
}

//Os PageRanks dos Hubs sao significativamente maiores que os dos authorities, 
//muitas vezes por uma ordem de magnitude.
