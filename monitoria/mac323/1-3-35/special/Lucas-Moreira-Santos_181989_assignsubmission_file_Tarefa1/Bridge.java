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
/***************************************************************/
/**                                                           **/
/**   Lucas Moreira Santos                          9345064   **/
/**   Creative Problem 1.3.35                                 **/
/**   Professor:  Yoshiharu Kohayakawa                        **/
/**   MAC0323 - Algoritmos e Estruturas de Dados II           **/
/**                                                           **/
/***************************************************************/

import edu.princeton.cs.algs4.*;

public class Bridge {
  public static void main(String[] args) {
    RandomQueue<Card> cards = new RandomQueue<Card>();
    for (int i = 0; i < 52; i++) {
      cards.enqueue(new Card(i));
    }

    for (int i = 0; i < 13; i++) {
      StdOut.println(cards.dequeue());
    }
  }
}
