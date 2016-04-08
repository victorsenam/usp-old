import java.util.*;
import edu.princeton.cs.algs4.*;
/* Name: Antonio Carlos Santos de Lima
 * USP Number 8515986
 */

/******************************************************************************
 *  Compilation:  javac Card.java
 *  Execution:    java -cp .:cards.jar Card     (Unix, OS X)
 *  Execution:    java -cp .;cards.jar Card     (Windows)
 *  Dependencies: StdDraw.java
 *
 *  Implement a playing card in a standard 52 card deck.

 *  The sample client assumes cards.jar is in the current directory
 *  and contains images of the cards named: 0.gif through 51.gif.
 *
 *  The sample client display two blackjack hands.
 *
 ******************************************************************************/

public class Card 
{ 
    private int suit;      // clubs = 0, diamonds = 1, hearts = 2, spades = 3
    private int rank;      // deuce = 0, three = 1, four = 2, ..., king = 11, ace = 12
    
    // create a new card based on integer 0 = 2C, 1 = 3C, ..., 51 = AS
    public Card (int card) 
    {
        rank = card % 13;
        suit = card / 13;
    }

    public int rank () { return rank; }
    public int suit () { return suit; }

    // for sorting cards by suit, then rank, as in Bridge or Hearts
    public boolean less (Card c) 
    {
        if      (suit < c.suit) return true;
        else if (suit > c.suit) return false;
        else if (rank < c.rank) return true;
        else                    return false;
    }

    // represent cards like "2H", "9C", "JS", "AD"
    public String toString () 
    {
        String ranks = "23456789TJQKA";
        String suits = "CDHS";
        return ranks.charAt (rank) +  "" + suits.charAt (suit);
    }
}