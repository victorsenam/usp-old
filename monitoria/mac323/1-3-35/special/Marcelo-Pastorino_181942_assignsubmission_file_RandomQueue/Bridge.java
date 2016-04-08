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
 * Nome: Marcelo Baiano Pastorino Trylesinski
 * Número USP: 9297996
 *
 *  Compilation:  javac-algs4 Bridge.java
 *  Execution:    java-algs4 Bridge
 *
 *  O programa Bridge é um cliente de teste que imprime na tela uma mão de 
 *  bridge.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Bridge {
    public static void main (String[] args) {
        RandomQueue<Card> q = new RandomQueue<Card> ();

        for (int i = 0; i < 52; i++) {
            Card carta = new Card (i);
            q.enqueue (carta);
        }

        for (int i = 0; i < 13; i++)
            StdOut.println (q.dequeue ());
    }
}