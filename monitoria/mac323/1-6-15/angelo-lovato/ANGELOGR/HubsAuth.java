/******************************************************************************
 *  Nome: Ângelo Gregório Lovatto
 *  Numero USP: 9293435
 *  Compilaçao: javac-algs4 HubsAuth.java
 *  Execuçao: java-algs4 HubsAuth N M h a
 *  Durante os testes, com o programa Transition modificado, observou-se que
 *  os Hubs ficavam mais altos na classificação do que as Autorithies, apos 
 *  varias iteraçoes do programa RandomSurfer.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.*;
public class HubsAuth {
    public static void main (String[] args) {
	int N = Integer.parseInt(args[0]);
	int M = Integer.parseInt(args[1]);
	int h = Integer.parseInt(args[2]);
	int a = Integer.parseInt(args[3]);

	//Cria uma matriz que comporta todas as paginas, hubs e authorities.
	int[][] L  = new int[N+h+a][N+h+a];
	StdOut.printf("%d\n", N + h + a);

	//Gera os M links aleatórios entre as paginas, evitando links que
	//apontem para a mesma pagina em que estao.
	for (int i = 0; i < M; i++) {
	    int page = StdRandom.uniform(N);
	    L[page][(StdRandom.uniform(N-1) + page)%N]++;
        }

	//Com os M links prontos, basta gerar os restantes para Hubs
	//e Authorities.
	HandA (N, L, h, a);

	//Com a matriz pronta, basta imprimir os links para a saida padrao.
	int total = N + a + h;
	for (int i = 0; i < total; i++) {
	    for (int j = 0; j < total; j++) {
		for (int u = 0; u < L[i][j]; u++) {
		    StdOut.printf ("%d %d ", i, j);
		}
	    }
	    StdOut.println();
	}	
	StdOut.println();
    }
	  
    //A funçao HandA distribui, aleatoriamente, os links de 10% das paginas
    //para cada Hub e os links de cada Authority para 10% das paginas. 
    public static void HandA (int N, int L[][], int h, int a) {
	int buffer = 0, pos;
        int[] pages = new int[N];
	
	//A lista pages auxilia para que nao se repitam paginas.
	for (int i = 0; i < N; i++) pages[i] = i;

	for (int i = 0; i < h; i++) {
	    //Escolhe, aleatoriamente, 10% das paginas e cria links delas
	    //para o hub.
	    for (int j = 0; j < N/10; j++) {
		pos = StdRandom.uniform(N-j);
		buffer = pages[pos];
		pages[pos] = pages[N-j-1];
		pages[N-j-1] = buffer;
		L[buffer][N+i]++;
	    }
	}

	for (int i = 0; i < a; i++) {
	    //Escolhe, aleatoriamente, 10% das paginas e cria links para elas
	    //a partir da Authority.
	    for (int j = 0; j < N/10; j++) {
		pos = StdRandom.uniform(N-j);
		buffer = pages[pos];
		pages[pos] = pages[N-j-1];
		pages[N-j-1] = buffer;
		L[N+h+i][buffer]++;
	    }

	}
    }
}
