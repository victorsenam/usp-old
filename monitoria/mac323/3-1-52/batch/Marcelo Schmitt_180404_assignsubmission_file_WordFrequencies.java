import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * @author Marcelo Schmitt
 * Número USP: 9297641
 *
 */
public class WordFrequencies {

	public static void main(String[] args) {
		
		Scanner scanner;
		//scanner = WordFrequencies.scannerDeArquivo("teste.txt");
		scanner = WordFrequencies.scannerDeConsole();
		ArrayList<String> palavras = new ArrayList<String>();
		ArrayList<Integer> frequencias = new ArrayList<Integer>();
		
		while (scanner.hasNext()) {
			//Obtendo o próximo token (que pode não ser necessáriamente 
			//uma única palavra)
			String token = scanner.next();
			//Substituindo sinais de pontuação por espaço
			token = token.replace('.', ' ');
			token = token.replace(',', ' ');
			token = token.replace('!', ' ');
			token = token.replace('?', ' ');
			token = token.replace(';', ' ');
			token = token.replace(':', ' ');
			
			//Obtendo palavras que estavam ligadas por sinais de pontuação
			String[] tokens = token.split(" ");
			//Se o token inicial não conter sinais de pontuação tokes[0] tem
			//a única palavra deste token, caso contrário temos mais de 
			//uma palavra.
			for (int i = 0; i < tokens.length; i++) {
				//Obtendo palavras concatenadas por hifens. Se não houver hifens
				//temos apenas contatenadas[0].
				String[] concatenadas = tokens[i].split("-");
				for (int j = 0; j < concatenadas.length; j++) {
					int p = encotraPosicaoInsercao(palavras, concatenadas[j]);
					if (palavras.contains(concatenadas[j].toLowerCase())) {
						//As duas palavras são iguais. Incrementar a frequência 
						//dessa palavra.
						frequencias.set(p, frequencias.get(p) + 1);
					} else {
						//As duas palavras são diferentes. Adicionar a nova palavra.
						//Adicionar um contador para essa nova palavra.
						palavras.add(p, concatenadas[j].toLowerCase());
						frequencias.add(p, 1);
					}
				}
			}
		}
		scanner.close();
		//Exibindo as palavras e suas frequências
		Iterator<String> iterator = palavras.iterator();
		Iterator<Integer> iterator2 = frequencias.iterator();
		while (iterator.hasNext() && iterator2.hasNext()) {
			System.out.println(iterator.next() + " " + iterator2.next());
		}
	}
	
	/**
	 * Encontra a posição p do ArrayList de palavras na qual a String token deve 
	 * ser inserida de modo que as palavras 0 .. p-1 sejam lexicamente menores
	 * ou iguais a token. A caixa das palavras não é considerada.
	 * Observação: As palavras / letras compostas de caracteres acentuados 
	 * são consideradas maiores que palavras / letras de mesmo comprimento 
	 * não acentuados.
	 * Exemplo: a letra "é" é considerada lexicamente maior que "e" (até maior 
	 * que z). 
	 * 
	 * @param palavras ArrayList de palavras
	 * @param token String a qual se quer saber a posição para inserir em palavras
	 * 
	 * @return a posição p na qual token deve ser inserida em palavras
	 */
	public static int encotraPosicaoInsercao(ArrayList<String> palavras, String token) {
		int p = 0;
		for (String palavra : palavras) {
			if (token.toLowerCase().compareToIgnoreCase(palavra) <= 0) {
				break;
			}
			p++;
		}
		return p;
	}
	
	/**
	 * Método usado para ler a entrada de arquivos.
	 * 
	 * @param file String representando o caminho do arquivo.
	 * 
	 * @return Um objeto Scanner configurado para ler do arquivo.
	 */
	public static Scanner scannerDeArquivo(String file) {
		try {
			return new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Método usado para ler a entrada do console (entrada padrão).
	 * 
	 * @return Um objeto Scanner configurado para ler do console.
	 */
	public static Scanner scannerDeConsole() {
		return new Scanner(System.in);
	}

}
