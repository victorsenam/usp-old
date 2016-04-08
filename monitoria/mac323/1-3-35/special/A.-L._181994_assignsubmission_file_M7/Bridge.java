import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
/* Name: Antonio Carlos Santos de Lima
 * USP Number 8515986
 */
public class Bridge 
{
    static RandomQueue<Card> hand = new RandomQueue<Card>();
    static boolean[] selectedCards = new boolean[52];
    
    public static int searchCard (int random)
    {
        int begin = (random + 1) % 52;
        int end = random % 52;
        
        while (begin != end) //while the loop doesn't verify the whole array
        {
            if (!selectedCards[begin]) return begin; //there is an available card 
                
            begin++;
            begin %= 52;
        }      
        return -1; //there is not any available card 
    }
        
    public static void chooseCards ()
    {
       Card randomCard;
       int randomNumber;
       for (int i = 0; i < 13; i++)
       {
            randomNumber = StdRandom.uniform (0, 52);
            if (selectedCards[randomNumber]) randomNumber = searchCard (randomNumber);
            selectedCards[randomNumber]  = true;
            
            randomCard = new Card (randomNumber);
            hand.enqueue (randomCard);
       } 
    }
    
    public static void printCards ()
    {
        for (int i = 0; i < 13; i++)
            StdOut.println (hand.dequeue ());
    }
    
    public static void main(String[] args) 
    { 
        chooseCards ();
        printCards ();
    }
}
