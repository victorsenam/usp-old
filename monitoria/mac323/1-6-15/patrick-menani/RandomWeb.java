/*
// Nome: Patrick Abrahão Menani
// N USP: 8941050
// RandomWeb
*/
public class RandomWeb {
   public static void main (String[] args) {
      int N = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      int A = Integer.parseInt(args[2]);
      int H = Integer.parseInt(args[3]);
      int p = 0, q, d;
      int[][] random = new int[N][N];

      d = M - (int) (A * (N/10) + H * (N/10));
      StdOut.printf("%d\n", N);

      // Numeros aleatórios sem Hubs e Authorities //
      for (int i = 0; i < d; i++) {
         p = ((int) ((N - A) * Math.random())) + A; // linha
         q = (int) ((N - H) * Math.random()); // coluna
         random[p][q]++;
      }
      StdOut.printf("%d\n", N - 1 - A);
      StdOut.printf("%d\n", N - 1 - H);

      // Authorities //
      for (int i = 0; i < A; i++) {
         p = (int) (N / 10);
         for (int j = 0; j < p; j++) { 
            q = (int) ((N - 1) * Math.random());
            random[i][q]++;
         }
      }

      // Hubs //
      for (int i = N - H; i < N; i++) {
         p = (int) (N / 10);
         for (int j = 0; j < p; j++) { 
            q = (int) ((N - 1) * Math.random());
            random[q][i]++;
         }
      }

      // Impressão dos números aleatórios no terminal //
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            if (random[i][j] > 0) {
               for(int k = 0; k < random[i][j]; k++) StdOut.printf ("%d %d ", i, j);
            }
         }
         StdOut.printf ("\n");
      }
   }
}
