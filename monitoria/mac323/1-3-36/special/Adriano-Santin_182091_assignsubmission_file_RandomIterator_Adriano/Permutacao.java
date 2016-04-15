import java.util.*;
import edu.princeton.cs.algs4.*;
/*///////////////////////////////////////////////////////////////////////
//                                                                     //
// Nome: Adriano Tetsuaki Ogawa Santin                                 //
// Número USP: 8944387                                                 //
// Creative Problem 1.3.36 (Random Iterator)                           //
//                                                                     // 
///////////////////////////////////////////////////////////////////////*/

// Classe permutação que contém uma string str que contém a
// permutação e a frequência freq dela. 
public class Permutacao {

   private String str;
   private int freq;

   public Permutacao (String a) {
      str = a;
      freq = 1;
   }

   public void incFreq () {
      freq++;
   }

   public String str () {
      return str;
   }

   public int freq () {
      return freq;
   }
}
