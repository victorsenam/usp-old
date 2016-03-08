import edu.princeton.cs.algs4.*;

public class Generator {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int M = Integer.parseInt(args[1]);
		int H = Integer.parseInt(args[2]);
		int A = Integer.parseInt(args[3]);
		/*Nessa simulação o número de links que hubs recebem está limitado a 10% de N e o
		 * número de links que authorities possuem também */
		int n = (int)Math.ceil(.1*N);
		int[] hubs = new int[H];
		int[] authorities = new int[A];
		int page = 0;
		int i = 0, j = 0;
		int temp = 0;
		
		if (H + A > N) {
			StdOut.println("Hubs e Authorities ultrapassam o número de páginas");
			return;
		}
		StdOut.println(N);
		/*As únicas restrições aplicadas nesse modelo são o número de links que hubs recebem e que authorities enviam e
		 * que uma página não pode ter link para ela mesma.
		 * Por isso, authorities podem ter links para outros hubs e authorities, e hubs podem receber
		 * links de outros hubs e authorities.
		 * Os vetores authorities[] e hubs[] contam quantos links cada authority já enviou e cada hub já recebeu. Assim,
		 * authorities[i] indica a quantidade de links que a página i tem e hubs[i] a quantidade de links para a página
		 * i+N-H.
		 * É importante salientar que as páginas [0,A-1] são authorities e as páginas [N-H,N-1] são hubs.
		 */
		
		/*Cada iteração desse for irá fazer n links do authority i para qualquer outra página (que não ela mesma)
		 * e n links de qualquer página para o hub i. Por isso só são necessárias max(A,H) iterações.
		 */
		for (i = 0; i < Math.max(A, H); i++) {
			for (j = 0; j < n; j++) {
				
				if (i < A) {
					if (authorities[i] < n) {	/*Se o authority i ainda não tem n links*/
						authorities[i]++;
						StdOut.print(i + " ");
						temp = i;
						while(true) {
							page = (int)(Math.random()*(N));
							if ((page < A) && (page != temp)) break;		/*Manda para outro authority que não ele mesmo*/
							else if ((page >= A) && (page < N-H)) break;	/*Manda para uma página normal*/
							else if ((page >= N-H) && hubs[page-N+H] < n) {	/*Manda para um hub e incrementa*/
								hubs[page-N+H]++;							/*hubs[page-N+H]. A transformação -N+H é feita*/
								break;										/*pois page pertence a [N-H,N-1] e os índices*/
							}												/*de hubs pertencem a [0,H-1].*/	
						}
						StdOut.println(page + " ");
					}
				}
				/*Aqui, segue-se a mesma lógica de cima*/
				if (i < H) {
					if (hubs[i] < n) {			/*Se ainda não há n links para o hub i*/
						while(true) {
							page = (int)(Math.random()*(N));
							if ((page < A) && (authorities[page] < n)) {
								authorities[page]++;
								break; 
							}
							else if ((page >= A) && (page < N-H)) break;
							else if ((page >= N-H) && (page != i+N-H)) break;
						}
						StdOut.print(page + " ");
						hubs[i]++;
						StdOut.println((i+N-H) + " ");						
					}
				}
			}			
		}
		/*Para facilitar a geração de números aleatórios, convencionou-se que as páginas de 0 a A-1 seriam
		 * authorities e as páginas de N-H a N-1 seriam hubs. Assim, sempre que precisei gerar números
		 * aleatórios num intervalo, esse intervalo era contínuo.
		 * Aqui se faz os links restantes necessários para se ter M links na Web. (n*(A+H)) é a quantidade
		 * de links realizados por hubs e authorities */
		temp = M-(n*(A+H));
		for (i = 0; i < temp; i++) {
			page = (int)(Math.random()*(N-A) + A); /*Authorities não podem mais ter links*/
				StdOut.print(page + " ");
				j = page;
			while(true) {
				page = (int)(Math.random()*(N-H-1)); /*Não pode se ter mais links para hubs*/
				if (page != j) break;
			}
				StdOut.print(page + "\n");				
		}		
	}
}
