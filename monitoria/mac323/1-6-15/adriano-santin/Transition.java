/*///////////////////////////////////////////////////////////////////////
//                                                                     //
// Nome: Adriano Tetsuaki Ogawa Santin                                 //
// Número USP: 8944387                                                 //
// Creative exercise 1.6.15 (Hubs and Authorities)                     //
//                                                                     // 
///////////////////////////////////////////////////////////////////////*/

/* Programa recebe o número N de páginas e uma lista de pares ordenados 
// representando os links e imprime uma matriz NxN onde cada célula 
// ixj representa a frequencia em que se vai da página i para a j;
*/

public class Transition {

   public static void main (String[] args) {
      
      int N = StdIn.readInt();           // number of pages
      int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
      int[] outDegree = new int[N];      // outDegree[i] = # links from page i to anywhere
      
      // Accumulate link counts.  
      while (!StdIn.isEmpty())  {
         int i = StdIn.readInt(); 
         int j = StdIn.readInt(); 
         outDegree[i]++; 
         counts[i][j]++; 
      } 
      StdOut.println(N + " " + N); 
      
      
      // Print probability distribution for row i. 
      for (int i = 0; i < N; i++)  {
         
         // Print probability for column j. 
         for (int j = 0; j < N; j++) {
            if (outDegree[i] != 0) {
               double p = .90*counts[i][j]/outDegree[i] + .10/N;
               StdOut.printf("%7.5f ", p);
            }
            else {
               double p = 1.0/N;
               StdOut.printf("%7.5f ", p);
            }
         } 
         StdOut.println(); 
      } 
   } 
} 
