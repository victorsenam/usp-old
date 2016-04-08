import java.util.*;
import edu.princeton.cs.algs4.*;
/* #########################################################################
   ############################   Header   #################################
   #########################################################################
   #                                                        			      # 
   #  MAC-0323                                              			      #
   #  Creative Problem 1.3.35 (Random queue; Algs4)    			            #
   #  Name: Leonardo Araujo Benicio dos Santos              			      #
   #  USP Number: 8536110                                   			      #
   #                                                        			      #
   #                                                        			      #
   #########################################################################
   ##########################   Debugging   ################################
   #########################################################################
   # Compiled: javac-algs4 Bridge.java                     	    		      #
   # Executed: java-algs Bridge	                           			      #
   #                                                            	     	   #
   #                                                           			   #
   #########################################################################

   Problem to be solved:
   http://paca.ime.usp.br/mod/assign/view.php?id=30945

   (1.3.35)
      Write a class RandomQueue that implements this API. Hint: Use an array 
   representation (with resizing). To remove an item, swap one at a random 
   position (indexed 0 through N-1) withh the one at the last position 
   (index N-1). Then delete and return the last object, as in ResizingArrayStack. 
   Write a client that deals bridge hands (13 cards each) using RandomQueue<card>

*/

public class Bridge {
   public static void main (String[] args){
      RandomQueue<Card> hand = new RandomQueue<Card>();

      for (int i = 0; i < 52; i++){
         Card card = new Card(i);
         hand.enqueue(card);
      }

      for (int i = 0; i < 13; i++){
         Card card = hand.dequeue();

         StdOut.println(card);
      }
   }
}