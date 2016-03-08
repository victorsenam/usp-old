/*///////////////////////////////////////////////////////////////////////
//                                                                     //
// Nome: Adriano Tetsuaki Ogawa Santin                                 //
// Número USP: 8944387                                                 //
// Creative exercise 1.6.15 (Hubs and Authorities)                     //
//                                                                     // 
///////////////////////////////////////////////////////////////////////*/


/* Programa recebe como parâmetros N (número de páginas), M (número de 
// links), H (número de Hubs) e A (número de Authorities). Imprime
// como saída o número total de arestas (N+H+A) e os pares ordenados 
// representando links entre as páginas.
*/

public class Generator {

   // Função imprime os links de 10% das páginas indo para cada Hub
   public static void addHubs (int H, int N) {
      int[] a = new int[N/10];
      for (int i = 0; i < H; i++) {
         for (int j = 0; j < N/10; j++) {
            a[j] = (int) (Math.random() * N);
            for (int k = 0; k < j; k++)
               if (a[k] == a[j]) {
                  a[j] = (int) (Math.random() * N);
                  k = 0;
               }
            StdOut.print (a[j] + " " + (int)(N+i) + "  ");
         }
         StdOut.println ();
      }
      StdOut.println ();
   }

   // Função imprime os links de cada Authority indo para 10% das páginas
   public static void addAuthorities (int A, int N, int H) {
      int[] a = new int[N];
      for (int i = 0; i < A; i++) {
         for (int j = 0; j < N/10; j++) {
            a[j] = (int) (Math.random() * N);
            for (int k = 0; k < j; k++)
               if (a[k] == a[j]) {
                  a[j] = (int) (Math.random() * N);
                  k = 0;
               }
            StdOut.print (N+H+i + " " + a[j] + "  ");
         }
         StdOut.println ();
      }
      StdOut.println ();
   }

   
   public static void main (String[] args) {
      // Na saída, 0 ~ N-1 = páginas; N ~ N+H-1 = Hubs; N+H ~ N+H+A-1 = Authorities.
      int N = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      int H = Integer.parseInt(args[2]);
      int A = Integer.parseInt(args[3]);
      StdOut.println (N+H+A);

      // Cria os M pares das N páginas e as imprime
      for (int i = 1; i <= M; i++) {
         StdOut.print ((int) (Math.random() * N) + " " + (int) (Math.random() * N) + "  ");
         if (i % 4 == 0)
            StdOut.println ();
      }
      StdOut.println ("\n");
      
      addHubs (H, N);
      addAuthorities (A, N, H);
   }
}
