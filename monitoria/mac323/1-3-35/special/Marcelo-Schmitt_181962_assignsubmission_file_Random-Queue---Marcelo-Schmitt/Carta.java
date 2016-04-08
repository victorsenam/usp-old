import java.util.*;
import edu.princeton.cs.algs4.*;
/**
 * 
 * @author Marcelo Schmitt
 * Número USP: 9297641
 *
 */
public class Carta {
	
	private Nome nome;
	private Naipe naipe;
	
	public Carta(Nome nome, Naipe naipe) {
		this.nome = nome;
		this.naipe = naipe;
	}
	
	@Override
	public String toString() {
		return nome.toString() + naipe.toString();
	}
	
	/**
	 * Teste da classe Carta.
	 * @param args
	 */
	public static void main(String[] args) {
		Carta[] cartas = new Carta[52];
		Nome[] nomes = Nome.values();
		Naipe[] naipes = Naipe.values();
		for (int i = 0; i < nomes.length; i++) {
			for (int j = 0; j < naipes.length; j++) {
				cartas[i*4 + j] = new Carta(nomes[i], naipes[j]);
			}
		}
		for (int i = 0; i < cartas.length; i++) {
			System.out.println(cartas[i]);
		}
	}

}
