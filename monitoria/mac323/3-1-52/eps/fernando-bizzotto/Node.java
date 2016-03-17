/*Nesta classe implementou-se o método compareTo(), que será usado pelo método
 * sort da classe Quick do Sedgewick. Aqui definiu-se que queremos priorizar
 * a frequencia da palavra e, em caso de empate, priorizar a menor ordem
 * lexicográfica da palavra
 */
public class Node implements Comparable<Node>{
	/*public Node(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}*/
	String word = new String();
	int frequency;
	
	public int compareTo(Node node) {
		if (this.frequency < node.frequency) {
			return 1;
		}
		if (this.frequency > node.frequency) {
			return -1;
		}
		else {
			if (this.word.compareTo(node.word) < 0) {
				return -1;
			}
			if (this.word.compareTo(node.word) > 0) {
				return 1;
			}
			return 0;
		}
		
	}

}
