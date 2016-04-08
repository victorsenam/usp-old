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
/*
 * NumUSP: 4343470
 * Nome: Mathias Van Sluys Menck
*/

public class RandomQueue<Item>
{

  private int N;
  private int i;
  private Item[] rq;
  
/* Inicializa uma RandomQueue */
  public RandomQueue(){
    rq = (Item[]) new Object[20];
    i = 0;
    N = 20;
  }
/* Checa se a Random queue está vazia */
  public boolean isEmpty()
  {
    return i==0;
  }
/* Aumenta o tamanho da Random queue se necessario */
  public Item[] resize(Item[] rq)
  {
    Item[] aux = (Item[]) new Object[N*2];
    for(int j=0; j<N; j++) aux[j] = rq[j];
    N*=2;
    return aux;
  }
/* Bota um item em Random queue */
  public void enqueue(Item item)
  {
    rq[i] = item;
    i++;
    if(i>=N) rq = resize(rq);
  }
/* Retorna um item randomico da Random queue*/
  public Item dequeue()
  {
    int ponto = StdRandom.uniform(i);
    Item ret = rq[ponto];
    rq[ponto] = rq[i-1];
    i--;
    return ret;
  }

  public Item sample()
  {
    return rq[StdRandom.uniform(i)];
  }

}
