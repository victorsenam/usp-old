import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Compilation:  javac Card.java
 *
 *  Problema 1.3.35 - Web 
 *  Pedro Sola Pimentel RM 9298079
 *
 *  Implement a playing card in a standard 52 card deck.
 *
 *  The sample client assumes cards.jar is in the current directory
 *  and contains images of the cards named: 0.gif through 51.gif.
 *
 *  The sample client display two blackjack hands.
 *
 ******************************************************************************/

public class Card { 
    private int suit;      // clubs = 0, diamonds = 1, hearts = 2, spades = 3
    private int rank;      // deuce = 0, three = 1, four = 2, ..., king = 11, ace = 12

    // create a new card based on integer 0 = 2C, 1 = 3C, ..., 51 = AS
    public Card (int card) {
        rank = card % 13;
        suit = card / 13;
    }

    // represent cards like "2H", "9C", "JS", "AD"
    public String toString() {
        String ranks = "23456789TJQKA";
        String suits = "CDHS";
        return ranks.charAt(rank) +  "" + suits.charAt(suit);
    }
}