/*
 * Nome:   Éderson Cássio Lacerda Ferreira
 * N. USP: 9297975
 * 
 * Uso: java-introcs Generator N M H A
 */

public class Generator {

   public static void main(String args[]) {
      int N = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      int H = Integer.parseInt(args[2]);
      int A = Integer.parseInt(args[3]);
      int links[][] = new int[N+H+A][N+H+A];
      
      // M aleatórios entre as N primeiras
      for (int k = 0; k < M; k++) {
         int i = StdRandom.uniform(N);
         int j = StdRandom.uniform(N);
         links[i][j] = 1;
      }
      
      // Hubs
      int dezPorcento = (int) Math.round(0.1 * N);
      if (dezPorcento == 0) dezPorcento = 1;
      int pagina = 0;
      for (int h = N; h < N+H; h++) {
         for (int l = 0; l < dezPorcento; l++) {
            links[pagina][h] = 1;
            if (pagina < N-1) pagina++;
         }
      }
      
      // Autoridades
      pagina = N - 1;
      for (int a = N+H; a < N+H+A; a++) {
         for (int l = 0; l < dezPorcento; l++) {
            links[a][pagina] = 1;
            if (pagina > 0) pagina--;
         }
      }
      
      // Podemos imprimir em ordem!
      StdOut.println(N+H+A);
      for (int i = 0; i < N+H+A; i++) {
         for (int j = 0; j < N+H+A; j++)
            if (links[i][j] == 1)
               StdOut.print(i + " " + j + "  ");
         StdOut.println();
      }
      
      
   }

}
