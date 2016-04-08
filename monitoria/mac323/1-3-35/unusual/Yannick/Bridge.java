/* ***************************** *
 * Nome: Yannick Messias         *
 * N.USP: 8803834                *
 * ***************************** */

public class Bridge{
  public static void main(String[] args){
    RandomQueue<Card> rq = new RandomQueue<Card>();
    for(int i = 0; i < 52; i++){
      rq.enqueue(new Card(i));
    }
    
    for(int i = 0; i < 13; i++){
      StdOut.println(rq.dequeue());
    }
  }
}