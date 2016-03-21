import edu.princeton.cs.algs4.*;

public class  MyTree {
	public  final String palavra;
	public  int cont;
	public  MyTree left;
	public  MyTree right;

	public MyTree (String parole) {
		palavra = parole;
		cont = 1;
	}

	public static void toString (MyTree t) {
		System.out.println (t.cont + " " + t.palavra);
	}

	private static MyTree encontra (String parole, MyTree t) {
		MyTree pai = null;
		MyTree filho = t;
		while (filho != null) {
			int cmp = parole.compareTo(filho.palavra);
			if (cmp == 0) return filho;
			if (cmp < 0) {
				pai = filho;
				filho = filho.left;
			}
			else {
				pai = filho;
				filho = filho.right;
			}
		}
		return pai;
	}

	public static void Insere_folha (String parole, MyTree t) {
		MyTree folha = encontra (parole, t);
		int com = parole.compareTo(folha.palavra);
		if (com == 0) {
			folha.cont++;
			return;
		}
		else if (com > 0) folha.right = new MyTree(parole);
		else folha.left = new MyTree(parole);
	}

	public static void printaTree (MyTree t) {
		if (t == null) return;
		printaTree (t.left);
		toString (t);
		printaTree (t.right);
	}

	public static void Preenche_vetor (MyTree t, MyTree[] vet, int i) {
		if (t == null) return;
		vet[i] = t;
		Preenche_vetor(t.left, vet, 2*i+1);
		Preenche_vetor(t.right, vet, 2*i+2);
	}

	public static int Conta_folha (MyTree t) {
		if (t == null) return 0;
		else return (Conta_folha(t.right) + Conta_folha(t.left));
	}

	public static void main(String[] args) {
		//In entrada = new In(args[0]);
		//String[] words = entrada.readAllStrings();
		String[] words = StdIn.readAllStrings();
		MyTree raiz = new MyTree(words[0]);
		for (int i = 1; i < words.length; i++) {
			Insere_folha(words[i], raiz);
		}
		printaTree(raiz);
		//int N = Conta_folha (raiz);
		//MyTree[] vector = new MyTree[N];
		//Preenche_vetor(raiz, vector, 0);
		//for (int t = 0;  < N; i++) {
		//	toString(vector[i]);
		//}
	}
}
