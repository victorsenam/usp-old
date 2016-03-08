/*
Exercício 14 de http://introcs.cs.princeton.edu/java/16pagerank/

Para compilar:
javac-introcs RandomWeb.java

Para rodar:
java-introcs RandomWeb N M H A
em que 
N é a quantidade de páginas,
M é a quantidade de links,
H é a quantidade de hubs,
A é a quantidade de autoridades
*/

public class RandomWeb {
    public static void main (String args[]) {
        int i, j, 
        N = Integer.parseInt (args[0]), 
        M = Integer.parseInt (args[1]), 
        H = Integer.parseInt (args[2]), 
        A = Integer.parseInt (args[3]);
        StdOut.printf ("%d\n", N + H + A);

        // Random Web
        for (i = 0; i < M; i++) 
            StdOut.printf ("%d %d\n", StdRandom.uniform (N), StdRandom.uniform (N));
        
        StdOut.println ();

        // Hubs
        for (i = 0; i < H; i++)
            for (j = 0; j < N / 10; j++)
                StdOut.printf ("%d %d\n", StdRandom.uniform (N), N + i);
        
        StdOut.println ();

        // Autoridades
        for (i = 0; i < A; i++)
            for (j = 0; j < N / 10; j++)
                StdOut.printf ("%d %d\n", N + H + i, StdRandom.uniform (N));
    }
}
