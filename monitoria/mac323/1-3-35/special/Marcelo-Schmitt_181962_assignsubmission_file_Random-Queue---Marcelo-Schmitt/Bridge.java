import java.util.*;
import edu.princeton.cs.algs4.*;
/**
 * 
 * @author Marcelo Schmitt
 * Número USP: 9297641
 *
 */
public class Bridge {

	public static void main(String[] args) {
		RandomQueue<Carta> randomQueue = new RandomQueue<>();
		Nome[] nomes = Nome.values();
		Naipe[] naipes = Naipe.values();
		//Criando as cartas do baralho e as adicionando à randomQueue.
		for (int i = 0; i < nomes.length; i++) {
			for (int j = 0; j < naipes.length; j++) {
				randomQueue.enqueue(new Carta(nomes[i], naipes[j]));
			}
		}
		//Sacando e imprimindo 13 cartas do baralho (uma mão de bridge).
		for (int i = 0; i < 13; i++) {
			System.out.println(randomQueue.dequeue());
		}
	}

}
