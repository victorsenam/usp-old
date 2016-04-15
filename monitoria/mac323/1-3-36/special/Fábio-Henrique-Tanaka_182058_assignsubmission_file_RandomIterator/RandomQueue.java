import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Nome: Fabio Henrique Kiyoiti dos Santos Tanaka
 *  Numero Usp: 9297982
 *  Compilation:  javac RandomQueue.java
 *
 *  Esta classe cria, usando vetores, uma pilha de elementos genericos
 *
 ******************************************************************************/
public class RandomQueue<Item> {
    private Item[] vetor;
    //Tamanho eh a quantidade de elementos do vetor e N a capacidade dele
    private int tamanho = 0, N;

    public RandomQueue() {
        N = 10;
        vetor = (Item[]) new Object[N];
    }

    private void expandeVetor () {
        int cont;
        Item[] novo = (Item[]) new Object[2*N];
        for (cont = 0; cont < N; cont++)
            novo[cont] = vetor[cont];
        N *= 2;
        vetor = novo;
    }

    //Empilha
    public void enqueue(Item item) {
        tamanho++;
        if (tamanho > N) expandeVetor();
        vetor[tamanho - 1] = item;
    }

    //Desempilha
    public Item dequeue() {
        tamanho--;
        return vetor[tamanho+1];
    }

    //Remove o elemento na posicao n do vetor
    private void removeElemento (int n) {
        int cont;
        for (cont = n; cont < tamanho - 1; cont++) {
            vetor[cont] = vetor[cont + 1];
        }
        tamanho--;
    }


    public boolean isEmpty() { 
        return tamanho == 0; 
    }

    //Esta funcao seleciona aleatoriamente um elemento do vetor, o retira da
    //da pilha e o devolve
    public Item Aleatorio () {
        int n, cont;
        Item devolver;
        if (tamanho < 1) {
            System.out.println ("Nao ha objetos na lista");        
            return null;
        }
        else if (tamanho == 1) n = 0;
        else n = StdRandom.uniform(tamanho);

        devolver = vetor[n];
        removeElemento (n);
        return devolver;
    }

    // Devolve um vetor com os elementos da pilha em ordem aleatoria
    public Item[] Iterador () {
        int cont, aux = tamanho;
        Item[] memoria = (Item[]) new Object[tamanho];
        Item[] devolver = (Item[]) new Object[tamanho];

        for (cont = 0; cont < aux; cont++)
            memoria[cont] = vetor[cont];

        for (cont = 0; cont < aux; cont++)
            devolver[cont] = Aleatorio();

        for (cont = 0; cont < aux; cont++)
            vetor[cont] = memoria[cont];
        tamanho = aux;
        return devolver;
    }


}
