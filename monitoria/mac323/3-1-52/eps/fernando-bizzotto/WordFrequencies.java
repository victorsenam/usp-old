import edu.princeton.cs.algs4.*;
/*Fernando Câmara Bizzotto, nº USP 7991211*/


public class WordFrequencies {
	public static void main(String args[]) {
		String[] text;
		String roughText, current;
		
		int freqCount = 0;
		int combinations = 0;
		int N;
		Node[] wordsNfreq;
		
		/*Transformou-se todo texto da entrada padrão em uma string, que será
		 * filtrada por uma expressão regular. Basicamente remove-se os principais
		 * caracteres especiais e espaços em branco. Também deixa-se todas as palavras
		 * em letra minúscula. Após isso, cada palavra estará em uma posição
		 * do array text
		 */
		roughText = StdIn.readAll();
		roughText = roughText.toLowerCase();
		text = roughText.split("[\\n\\t(\\s+)\"'!:*&#$;?\\,.\\\\\\/\\(\\)\\{\\}\\[\\]0-9]+");
		
		/*Ordenamos text lexicograficamente para calcularmos a frequencia de cada palavra
		 */
		Quick.sort(text);
		/*System.out.println(Arrays.toString(text));*/
		
		/*O array wordsNfreq[] será o array que será impresso no final do programa.
		 * Ele é composto de Nodes, que por sua vez possui uma String e um inteiro.
		 * Optou-se por fazer dessa forma para associarmos cada string a uma frequencia
		 * de maneira rápida.
		 */
		current = text[0];
		N = text.length;
		wordsNfreq = new Node[N];
		for (int i = 0; i < N; i++) {
			wordsNfreq[i] = new Node();
		}
		
		/*Esse laço popula o array wordsNfreq[]. A variável combinations
		 * Será usada no laço de impressão, e guarda a quantidade real de
		 * palavras não-repetidas a serem impressas*/
		for(int i = 0; i < N; i++) {
			if (!current.equals(text[i])) {
				wordsNfreq[i-1].word = current;
				wordsNfreq[i-1].frequency = freqCount;
				combinations++;
				freqCount = 0;				
			}
			current = text[i];
			freqCount++;
			if (i == N-1) {
				wordsNfreq[i].word = current;
				wordsNfreq[i].frequency = freqCount;
				combinations++;
			}
		}
		
		/*Finalmente ordena-se o array usando os critérios de frequência e 
		 * lexicográficos definidos na classe Node.
		 */
		Quick.sort(wordsNfreq);
		
		for (int i = 0; i < combinations; i++) {
			System.out.println(wordsNfreq[i].word + " " + wordsNfreq[i].frequency);
		}
	}
}
