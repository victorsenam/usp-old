/*

   Nome: Rodrigo Ribeiro Santos de Carvalho
   NUSP: 9298037

*/

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class WordFrequence {

   public static void ordena (String[] pal, int[] count, int pos) {
   
      for (int i = 0; i < pos; i++) {
      
         String x = new String (pal[i]);
         
         for (int j = i + 1; j < pos; j++) {
         
            String y = new String (pal[j]);
            
            if (count[i] < count[j]) {
            
               String temp = new String (x);
               pal[i] = new String (y);
               pal[j] = new String (temp);
               
               int tempInt = count[i];
               count[i] = count[j];
               count[j] = tempInt;
            }
         }
      }
   }

   public static void main (String[] args) {
      
      
      In in = new In (args[0]);
      int count[];
      int pos = 0;
      String[] pal = in.readAllStrings();
      String[] temp;
      String ant = "";
      
      /* Ordena todas as palavras */
      Arrays.sort (pal);
      
      /* Minúscula */
      for (int i = 0; i < pal.length; i++)
         pal[i] = new String (pal[i].toLowerCase());
      
     temp = new String [pal.length];
     count = new int [pal.length];
    
     /* count */    
     for (int i = 0; i < pal.length; i++) { 
         
         if (!ant.equals (pal[i])) {
         
            temp[pos++] = new String (pal[i]); 
            ant = new String (pal[i]);
         }
                  
         count[pos-1]++;
     }
          
     /* Ordena por count */
     ordena (temp, count, pos);
     
     for (int i = 0; i < pos; i++)
       StdOut.println (temp[i] + " " + count[i]);
      
     in.close ();
      
   }
}
