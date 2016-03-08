/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Número USP: 8962078
 *  Tarefa: Creative Ex. 1.6.15 (Hubs and authorities) de IntroCS
 *  URL: http://introcs.cs.princeton.edu/java/16pagerank/
 *  Data: 27/02/2016
 *
 *  A entrada deve ser o numero de paginas (N), o numero de links (M), o numero
 *  de hubs (H) e authorities (A).
 *  Exemplo: java Generator 10 30 3 4
 *
 *  Executavel: java Generator 10 500 5 5 | java Transition | java Markov 400
 *
 ******************************************************************************/

public class Generator {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int H = Integer.parseInt(args[2]);
        int A = Integer.parseInt(args[3]);

        double porcentagem = 0.1;

        if (H*porcentagem > 1) {
            StdOut.println("Numero de hubs muito alto!");
            System.exit(-1);
        }
        if (A*porcentagem > 1) {
            StdOut.println("Numero de Authorities muito alto!");
            System.exit(-1);
        }

        int[] hubs = aleatorios (H, N);
        int[] authorities = aleatorios (A, N);

        StdOut.print("Hubs: ");
        for (int i =0; i < H; i++) StdOut.printf(" %d", hubs[i]);
        StdOut.println();  
        StdOut.print("Authorities: ");
        for (int i =0; i < A; i++) StdOut.printf(" %d", authorities[i]);
        StdOut.println();  

        int[] saidas = distribui(M, N, authorities, porcentagem);
        int[] entradas = distribui(M, N, hubs, porcentagem);

        StdOut.println(N);
        for (int i =0; i < M; i++) {
            StdOut.printf("%3d  %3d\n", saidas[i], entradas[i]);  
        }
    }

    // Retorna um vetor de tamanho X com numeros de 0 ate Y, sendo que os 
    // numeros contidos em K apresentam no minimo probabilidade (P) de acontecer
    public static int[] distribui (int X, int Y, int K[], double P) {
        int[] casas = new int[X];
        int[] data = new int[X];
        int countdipo = X;
        for (int i = 0; i < X; i++) casas[i] = i;
        for (int i = 0; i < K.length; i++) {
            for (int j = 0; j < X*P; j++) {
                int x = random(0, countdipo);
                data[casas[x]] = K[i];
                countdipo--;
                casas[x] = casas[countdipo];
            }
        }
        for (int i = 0; i < countdipo; i++) {
            data[casas[i]] = random(0, Y);
        }
        return data;
    }

    // Retorna um vetor com X numeros aleatorios de 0 a Y
    public static int[] aleatorios (int X, int Y) {
        int[] disponiveis = new int[Y];
        int[] data = new int[X];
        int countdipo = Y;
        for (int i = 0; i < Y; i++) disponiveis[i] = i;
        for (int i = 0; i < X; i++) {
            int x = random(0, countdipo);
            data[i] = disponiveis[x];
            countdipo--;
            disponiveis[x] = disponiveis[countdipo];
        }
        return data;
    }

    // Retorna um inteiro entre min (incluso) e max (excluso)
    public static int random (int min, int max) {
        int range = (max - min);
        return (int)(Math.random() * range) + min;
    }
}