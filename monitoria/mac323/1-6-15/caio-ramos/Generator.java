/* Caio Ramos - NUSP 9292991 - IME-USP 2016
 * 
 * Programa que gera as m ligacoes entre os n sites, e cria k hubs e k
 * 		autoridades.
 * 
 */


public class Generator {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); //numero de sites
		int m = Integer.parseInt(args[1]); //numero de conexoes
		int k = Integer.parseInt(args[2]); //numero de hubs/autoridades
		
		int total = n+(k*2);
		
		StdOut.println(total);
		
		//gera as conexoes entre os sites
		for(int i = 0; i < m; i++) {
			int a = (int)(Math.random()*n);
			int b = (int)(Math.random()*n);
			StdOut.	println(a + " " + b);
		}
		
		
		//adiciona os hub (links que apontam para ele)
		for(int j = 0; j < k; j++) {
			for(int i = 0; i < (int)(.40*total); i++) {
				int hub = (int)(Math.random()*total);
				StdOut.	println(hub + " " + (n+j));
			}
		}
		
		//adiciona as autoridade (aponta links para outros)
		for(int j = 0; j < k; j++) {
			for(int i = 0; i < Math.round(.10*(n+(k*2))); i++) {
				int aut = (int)(Math.random()*total);
				StdOut.	printf("%d %d\n", (n+k+j), aut);
			}
		}
		
	}
}
