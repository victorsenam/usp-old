/***************************************************************/
/**                                                           **/
/**   Lucas Moreira Santos                          9345064   **/
/**   Creative Exercise 1.6.15                                **/
/**   Professor:  Yoshiharu Kohayakawa                        **/
/**   MAC0323 - Algoritmos e Estruturas de Dados II           **/
/**                                                           **/
/***************************************************************/


public class HubsAuthorities {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]),
			m = Integer.parseInt(args[1]),
			h = Integer.parseInt(args[2]),
			a = Integer.parseInt(args[3]),
			i = 0,
			x, y;

		StdOut.println(n + h + a);

		for (i = 0; i < m; i++) {
			// Nó de origem
			x = (int)(Math.random() * n); // Um valor no intervalo [0, n[

			// Nó destino
			y = (int)(Math.random() * n); // Um valor no intervalo [0, n[

			// Imprime na saída padrão (famoso printar na tela)
			StdOut.println(String.valueOf(x) + " " + String.valueOf(y));
		}

		for (i = n; i < (n + h); i++) {
			// Nó de origem é o próprio i, porque ele é o hub.
			// Vamos pegar 10% das páginas e criar um link para o hub.
			// Note que: pode ocorrer de pegarmos uma mesma página repetidas vezes.

			for (x = 0; x < (0.1 * n); x++) {
				// Nó destino:
				y = (int)(Math.random() * n); // Um valor no intervalo [0, n[

				// Imprime na saída padrão
				StdOut.println(String.valueOf(i) + " " + String.valueOf(y));
			}
		}

		for (i = (n + h); i < (n + h + a); i++) {
			// Nó de destino é o próprio i, porque ele é o authority.
			// Vamos pegar 10% das páginas e criar um link para o authority.
			// Note que: pode ocorrer de pegarmos uma mesma página repetidas vezes.

			for (x = 0; x < (0.1 * n); x++) {
				// Nó origem:
				y = (int)(Math.random() * n); // Um valor no intervalo [0, n[

				// Imprime na saída padrão
				StdOut.println(String.valueOf(y) + " " + String.valueOf(i));
			}
		}
	}
}
