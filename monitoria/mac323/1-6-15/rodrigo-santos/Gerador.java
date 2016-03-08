
/* 

   Nome: Rodrigo Ribeiro Santos de Carvalho
   NUSP: 9298037



*/

import edu.princeton.cs.algs4.*;

public class Gerador {

   public static void main (String args[]) {
   
      int N = Integer.parseInt (args[0]);
      int M = Integer.parseInt (args[1]);
      int H = Integer.parseInt (args[2]);
      int A = Integer.parseInt (args[3]);
      
      boolean[] visitados = new boolean[N];
      
      int count = N;
      
      StdOut.printf ("%d\n", N + H + A);
      
      /* Aqui irá gerar os pares estilo tiny.txt */
      for (int i = 0 ; i < M ; i++) {
      
         int x = StdRandom.uniform (N);
         int y = StdRandom.uniform (N);
                  
         while (y == x)
            y = StdRandom.uniform (N);   
         
         StdOut.printf ("%d %d\n", x, y);
         
      }      
      
      /* Hubs */
      for (int i = 0 ; i < H ; i++) {
          
         for (int j = 0 ; j < N/10 ; j++) {
         
            int x = StdRandom.uniform (N);
         
            while (visitados[x])
               x = StdRandom.uniform (N);
            
            visitados[x] = true;             
            StdOut.printf ("%d %d\n", x, count); 
         }
      
         count++;
      }
      
      visitados = new boolean[N];
      
      /* Autoridades */
      for (int i = 0 ; i < A ; i++) {
          
         for (int j = 0 ; j < N/10 ; j++) {
         
            int x = StdRandom.uniform (N);
         
            while (visitados[x])
               x = StdRandom.uniform (N);
            
            visitados[x] = true;             
            StdOut.printf ("%d %d\n", count, x); 
         }
      
         count++;
      }
      
      
      
   
   }
   
   



}
