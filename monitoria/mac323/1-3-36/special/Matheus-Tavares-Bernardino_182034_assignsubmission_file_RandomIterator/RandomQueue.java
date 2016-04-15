import java.util.*;
import edu.princeton.cs.algs4.*;
/*******************************************************************************

RandomQueue

Nome:   Matheus Tavares Bernardino
Nº USP: 9292987
Data:   27/03/2016


Descrição:
Esta classe implementa uma Random Queue (fila aleatória) de Itens (genéricos).
A implementação é feita em um vetor redimensionável v, de tamanho N
(inicialmente valendo 10). Nesta fila, são validas as operações de enfilerar
(enqueue), desenfilerar (dequeue), realizar amostragem de um elemento aleatório
(sample) e saber se a fila está vazia (isEmpty).
A main desta classe é um modulo de testes que enfilera os numeros inteiros de 0
até 49 e depois desinfilera aleatoriamente todos eles até que a fila esteja
vazia.

Compilação:    javac-algs4 RandomQueue.java
Execução:      java-algs4 RandomQueue

Ex de execução:
    $ java-algs4 RandomQueue
Saída:
    25 31 45 38 18 35 7 30 37 16 24 4 27 40 15 46 47 1 41 14 17 32 8 12 19 20 13
    33 43 49 3 22 29 9 10 21 23 28 34 5 42 39 2 26 44 0 36 6 48 11

Nota de autoria: Esta classe foi implementada com base nos slides de Sedgewick
e Wayne sobre implementação de Queues em vetor redimensionável, encontrados em:
http://algs4.cs.princeton.edu/lectures/13StacksAndQueues.pdf/#32 e usando a API
proposta no exercício 1.3.35 do livro Algorithms, 4ed dos mesmos autores.
*******************************************************************************/


import edu.princeton.cs.algs4.*;
import java.util.Iterator;


public class RandomQueue<Item> implements Iterable<Item> {

    int N, front, tail;
    Item[] v;

    /*controi uma RandomQueue vazia, implementada em um vetor "redimensionável"
    v (de tamanho N, inicialmente valendo 10) onde front é o indice do primeiro
    elemento (a partir do momento em que ele é enfilerado) e tail é o próximo
    índice dispónivel para se inserir um elemento novo.*/
    public RandomQueue () {
        N = 10;
        front = 0;
        tail = 0;
        v = (Item[]) new Object[N];
    }


    //retorna true caso a RandomQueue esteja empty e false caso contrário
    public boolean isEmpty () {
        return front == tail;
    }


    //adiciona item na fila
    public void enqueue (Item item) {
        v[tail] = item;
        if ((tail+1) % N == front) {              //Se vetor cheio, redimensiona
            int newN = N*2;                       //para o dobro do tamanho
            Item[] s = (Item[]) new Object[newN]; //(N*2).
            for (int j = front; j < front + N; j++)
                s[j-front] = v[j%N];
            v = s;
            front = 0;
            tail = N;
            N = newN;
        }
        else tail = (tail+1)%N;
    }


    //retorna um item pseudo-aleatório da fila e o exclui dela
    public Item dequeue () {
        int scope, first;
        if (tail - front < 0) scope = tail - front + N;
        else scope = tail - front;
        int i = (front + (int)(Math.random()*scope)) % N;
        Item aux = v[i];
        v[i] = v[front];
        v[front] = null;
        front = (front+1)%N;
        scope--;

        if ((double) (scope) < N/4) {                    //se vetor está menos
            int newN = (int) (N/2);                      //de 1/4 preenchido,
            Item[] s = (Item[]) new Object[newN];        //redimensiona-se para
            for (int j = front; j < front + scope; j++)  //aproximadamente
                s[j-front] = v[j%N];                     //metade do tamanho
            v = s;                                       //atual (N/2).
            front = 0;
            tail = scope;
            N = newN;
        }

        return aux;
    }


    //retorna um item pseudo-aleatório da fila mantendo-o nela
    public Item sample () {
        int scope, first;
        if (tail - front < 0) scope = tail - front + N;
        else scope = tail - front;
        int i = (front + (int)(Math.random()*scope)) % N;
        return v[i];
    }


    //class que implementa um iterator para RandomQueue
        private class RandomIterator implements Iterator<Item> {

        int iteratorScope, iteratorScopeFixed;
        boolean[] jaObservado;

        //constroi novo Randomiterator
        public RandomIterator () {
            if (tail - front < 0) iteratorScope = tail - front + N;
            else iteratorScope = tail - front;
            iteratorScopeFixed = iteratorScope;
            jaObservado = new boolean[iteratorScope];
        }
        
        //remove. Operação não suportada
        public void remove() {}

        //retorna true caso ainda há elementos na lista que está sendo iterada
        public boolean hasNext () {
            return iteratorScope > 0;
        }
        
        //retorna o próximo elemento da iteração aleatória da lista
        public Item next () {           
            int i;
            do {
                i = (int)(Math.random()*iteratorScopeFixed);
            } while (jaObservado[i] == true);
            jaObservado[i] = true;
            i = (front + i) % N;
            iteratorScope--;
            return v[i];
        }
    }


    //retorna um novo RandomIterator de Item;
    public Iterator<Item> iterator () {
        return new RandomIterator ();
    }


    //unidade de testes
    public static void main (String[] args) {
        RandomQueue<Integer> x = new RandomQueue ();
        for(int i = 0; i < 50; i++) x.enqueue(i);
        for (int k : x)
            System.out.printf ("%d ", k);
        System.out.println ();
    }

}
