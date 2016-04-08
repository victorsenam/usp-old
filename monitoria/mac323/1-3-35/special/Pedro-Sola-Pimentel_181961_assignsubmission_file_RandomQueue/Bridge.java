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
/******************************************************************************
 *  Compilation:  javac Bridge.java
 *  Execution:    java Bridge
 
 *  Problema 1.3.35 - Web 
 *  Pedro Sola Pimentel RM 9298079
 *
 *  % java Bridge
 *
 *	Este programa sorteia uma mao de bridge (13 cartas) com o auxilio
 *	da classe "Card.java" utilizando a fila presente em "RandomQueue.java"
 *
 ******************************************************************************/

public class Bridge {
    public static void main (String[] args) {
        RandomQueue<Card> queue = new RandomQueue<Card> ();

        for (int i = 0; i < 52; i ++) 
            queue.enqueue (new Card (i));

        for (int i = 0; i < 13; i++)
            System.out.println (queue.dequeue().toString());
    }
}
