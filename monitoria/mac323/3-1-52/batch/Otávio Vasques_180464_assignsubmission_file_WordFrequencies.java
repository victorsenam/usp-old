/* WordFrequencies
	MAC323 - Yoshiharu Kohayakawa
	Otávio Vasques - 8944665
	*/

import java.util.Scanner;

public class WordFrequencies {

	public static void ordenar(String[] palavras, int[] contagem, int N) {
		// Ordena o vetor contagem mantendo a paridade com o vetor palavras
		String auxs;
		int auxi;
		for (int i = 0; i < N; i++)
			for (int j = i+1; j < N; j++) {
				if (contagem[i] < contagem[j]) {
					auxi = contagem[i];
					contagem[i] = contagem[j];
					contagem[j] = auxi;
					auxs = palavras[i];
					palavras[i] = palavras[j];
					palavras[j] = auxs;
				}
			}
	}

	public static void ordenarstrings(String[] palavras, int N) {
		// Ordena alfabeticamente um vetor de strings ignorando strings vazias
		String aux = "";
		for (int i = 0; i < N; i++)
			for (int j = i+1; j < N; j++) {
				if (palavras[i].compareTo(palavras[j]) > 0) {
					aux = palavras[i];
					palavras[i] = palavras[j];
					palavras[j] = aux;
				}
			}
	}

	public static boolean estaEmchar(char c, String texto) {
		// Recebe um char c e uma string texto e verifica se o char c se 
		// encontra na string texto.
		int N = texto.length();
		for (int i = 0; i < N; i++)
			if (texto.charAt(i) == c)
				return true;
		return false;
	}

	// public static int estaEmstring(String string, String[] palavras) {
	// 	// Recebe um vetor de strings palavras e uma string string e verifica
	// 	// se a string string se encontra no vetor. Em caso afirmativo retorna-
	// 	// se o indice da posição, caso contrário retorna-se -1.
	// 	int N = palavras.length;
	// 	for (int i = 0; i < N; i++)
	// 		if (palavras[i].equals(string))
	// 			return i;
	// 	return -1;
	// }


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); //Objeto de leitura de strings
		int N = 1000000;                          // Numero maximo de elementos
		String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzáéíóúâêîôûãẽõĩũç-";
		// Caracteres que constium uma palavra
		String texto;							  // Texto a ser lido
		String trecho = ""; // Substring onde serão armazenadas as palavras
		String[] palavras = new String[N]; 
		// Vetor onde se armazena as palavras na etapa de classificação
		String[] palavrasFinal = new String[N]; 
		// Vetor onde se armazena as palavras na parte final
		int[] contagem = new int[N];   // Vetor onde se conta as palavras
		int j = 0, k = 0, l; 
		// Indices de controle dos tamanhos de palavras e palavrasFinal e texto


		// Inicialização dos vetores de strings
		for (int i = 0; i < N; i++) {
			palavras[i] = "";
			palavrasFinal[i] = "";
		}

		// Leitura do texto
		texto = scanner.nextLine();
		texto = texto + " ";
		l = texto.length();

		// Criação do vetor de palavras
		for (int i = 0; i < l; i++) {
			if (estaEmchar(texto.charAt(i), LETRAS))
				trecho += texto.charAt(i);
			else {
				if (trecho != "") {
					trecho = trecho.toLowerCase();
					palavras[j] = trecho;
					trecho = "";
					j++;
					}
			}
		}

		// Ordenando as palavras em ordem alfabética
		ordenarstrings(palavras, j);

		// Removendo as duplicatas e contando as frequencias
		k = 0;
		int i = 0;
		String aux = palavras[0];
		palavrasFinal[0] = aux;
		while (i < j) {
			if (palavras[i].equals(aux)) {
				contagem[k] += 1;
				i++;
			}
			else {
				k++;
				aux = palavras[i];
				palavrasFinal[k] = aux;
				contagem[k] = 1;
				i++;
			}
		}
		k++;

		// Ordenando segundo a contagem
		ordenar(palavrasFinal, contagem, k);

		// Impressão do resultado
		for (i = 0; i < k; i++) {
			System.out.println(palavrasFinal[i] + " " + contagem[i]);
		}
	}
}