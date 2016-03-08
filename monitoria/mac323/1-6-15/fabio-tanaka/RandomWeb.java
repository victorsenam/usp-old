/******************************************************************************
 *  Nome: Fabio Henrique Kiyoiti dos Santos Tanaka
 *  Numero Usp: 9297982
 *  Compilation:  javac RandomWeb.java
 *  Execution:    java RandomWeb N M A H
 *
 *  Este programa cria N paginas e M links entre elas, apos isso sao criadas A
 *  autorithies que tem N/10 links para outras paginas e H hubs que tem N/10 
 *  links apontados para eles.
 *
 *  java RandomWeb 10 10 2 2
 *  14
 *  
 *  5 3
 *  2 1
 *  9 1
 *  8 4
 *  4 5
 *  7 3
 *  3 7
 *  6 6
 *  1 3
 *  3 1
 *  10 1
 *  11 7
 *  5 12
 *  2 13
 *
 ******************************************************************************/
public class RandomWeb { 
    public static void main(String[] args) {
        int cont, aux;
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int A = Integer.parseInt(args[2]);
        int H = Integer.parseInt(args[3]);
        int aleatorio;
        double n = N;

        //Impressao do numero total de pags
        System.out.println ((N+A+H) + "\n");

        //Criacao dos links das paginas "normais"
        for (cont = 0; cont < M; cont ++) {
            aleatorio = StdRandom.uniform(N);
            System.out.print(aleatorio + " ");
            aleatorio = StdRandom.uniform(N);
            System.out.println(aleatorio);
        }

        //Criacao e impressao das autorithies
        for (cont = 0; cont < A; cont++) {
            for (aux = 0; aux < Math.ceil(n/10); aux++) {
                System.out.print((N+cont) + " ");
                aleatorio = StdRandom.uniform(N);
                System.out.println(aleatorio);

            }
        }

        // Criacao e impressao dos Hubs 
        for (cont = 0; cont < H; cont++) {
            for (aux = 0; aux < Math.ceil(n/10); aux++) {
    
                aleatorio = StdRandom.uniform(N);
                System.out.print(aleatorio + " ");
                System.out.println((N+A+cont));

            }
        }

    } 
} 
