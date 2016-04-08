/* ***************************** *
 * Nome: Yannick Messias         *
 * N.USP: 8803834                *
 * ***************************** */

import java.util.NoSuchElementException;

public class RandomQueue<Item>{
  private Item[] a;         // array of items
  private int N;            // number of elements on queue
  
  //create an empty random queue
  public RandomQueue(){
    a = (Item[]) new Object[2];
    N = 0;
  }
  
  //is the queue empty?
  public boolean isEmpty(){
    return N == 0;
  }
  
  // resize the underlying array holding the elements
  private void resize(int capacity) {
    assert capacity >= N;
    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < N; i++) {
      temp[i] = a[i];
    }
    a = temp;
  }
  
  //add an item
  public void enqueue(Item item){
    if (N == a.length) resize(2*a.length);    // double size of array if necessary
    a[N++] = item;                            // add item
  }
  
  //remove and return a random item
  public Item dequeue(){
    if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    int i = (int)(Math.random()*N);
    Item item = a[i];
    a[i] = a[N-1];
    a[N-1] = null;                              // to avoid loitering
    N--;
    // shrink size of array if necessary
    if (N > 0 && N == a.length/4) resize(a.length/2);
    return item;
  }
  
  //return a random item, but do nor remove
  public Item sample(){
    if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    int i = (int)(Math.random()*N);
    return a[i];
  }
}