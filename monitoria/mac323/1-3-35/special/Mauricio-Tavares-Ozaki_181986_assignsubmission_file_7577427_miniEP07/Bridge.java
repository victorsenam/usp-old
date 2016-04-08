import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Prof: Yoshiharu Kohayakawa
 *  Aluno: Mauricio Tavares Ozaki
 *  N USP: 7577427
 *  Data de Entrega: 27/03/2016
 *  Creative Problem 1.3.35 (Random queue; Algs4)
 *  Obs.: Eu criei uma fila seguindo a dica dada pelo autor no 
 *        enunciado e tive certa dificuldade em entender o Iterator.
 *        Na coompilacao, apareceu um warning:
 *        Note: RandomQueue.java uses unchecked or unsafe operations.
 *        Note: Recompile with -Xlint:unchecked for details.
 *        Um colega tambem comentou sobre isso no forum, acredito que 
 *        nesse ep isso nao sera um problema.
 *  Depedencias: RandomQueue
 ******************************************************************************/


public class Bridge {
    public static void main(String[] args) { 
        RandomQueue<String> r = new RandomQueue<String>();
        //Sem misterios, eu enfilero cada uma das 52 cartas do baralho
        for (int i=0; i<52; i++) {
            Card card = new Card(i);
            String buffer = card.toString();
            r.enqueue(buffer);
        }
        //Imprimo 13 cartas distintas
        for (int i=0; i<13; i ++) {
            System.out.println(r.dequeue());            
        }        
    }
}