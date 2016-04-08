import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * NumUSP: 4343470
 * Nome: Mathias Van Sluys Menck
*/

public class Bridge
{

/* Bota o baralho numa RandomQueue */
  public static void monta(RandomQueue<String> rq)
  {
    String naipe = "CDHS";
    String valor = "A23456789TJQK";
    for(int i=0; i<4; i++)
      for(int j=0; j<13; j++)
      {
        String carta = valor.charAt(j)+""+naipe.charAt(i);
        rq.enqueue(carta);
      }
  }

/* Imprime uma mão de Bridge */
  public static void imprime(RandomQueue<String> rq)
  {
    for(int i=0; i<13; i++)
      StdOut.println(rq.dequeue());
  }


  public static void main(String[] args)
  {
    RandomQueue<String> rq = new RandomQueue<String>();
    monta(rq);
    imprime(rq);
  }
}
