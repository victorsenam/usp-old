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
/******************************************************************************
 *  Nome: Andre Victor dos Santos Nakazawa
 *  NUSP: 9298336
 *
 *  Compilation:  javac Bridge.java
 *  Execution:    java Bridge
 *
 *  Print out 13 cards randomly picked from the stack of cards.
 *
 ******************************************************************************/

public class Bridge {
    public static void main(String[] args) {
        RandomQueue<Integer> q = new RandomQueue<Integer>();
        for (int i = 0; i < 52; i++)
            q.enqueue(i);

        /*
        StdDraw.setCanvasSize(400, 180);
        StdDraw.setXscale(0, 400);
        StdDraw.setYscale(0, 180);
        StdDraw.clear(StdDraw.GRAY);
        */

        for (int i = 0; i < 13; i++) {
            int c = q.dequeue();
            Card card = new Card(c, "cards/" + c + ".gif", "cards/back.gif");

            /* card.drawFront(80 + 20*i, 80); */

            StdOut.println(card);
        }
    }
}

