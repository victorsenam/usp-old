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
/* **************************************** */
/*  Nome: Camila Naomi Kodaira              */
/*  Numero USP: 4266602                     */
/* **************************************** */

/* ***************************************** */
/* Bridge                                    */
/* Esse programa devolve uma mao aleatoria   */
/* de Bridge, ou seja, ele devolve 13 cartas */
/* de baralho aleatoriamente e sem repeticao */
/*                                           */
/* Esse programa requer as classes a seguir  */
/* para funcionar:                           */
/* - Card.java                               */
/* - RandomQueue.java                        */
/*                                           */
/* Linha de Comando:                         */
/* $ java-algs4 Bridge                       */
/*                                           */
/* Output:                                   */
/* [Valor da Carta][Naipe]                   */
/* ^ como especificado em Card.java          */
/* ***************************************** */

public class Bridge {
  public static void main (String[] Args) {
    RandomQueue<Card> baralho = new RandomQueue<Card> ();
    int i;

    // Cria o baralho
    for (i = 0; i < 52; i++) {
      baralho.enqueue (new Card (i));
    }

    // Imprime uma mao
    for (i = 0; i < 13; i++) {
      StdOut.println (baralho.dequeue ());
    }
  }
}
