/******************************************************************************
 *  Compilacao:   javac Generator.java
 *  Execucao:     java Generator N M H A
 *                   N: quantidade de paginas
 *                   M: quantidade de links
 *                   H: quantidade de hubs
 *                   A: quantidade de autoridades
 *  Dependencias: StdOut.java
 *
 *  Este programa gera: M pares aleatorios de inteiros, de 0 a N-1; 0.1*N*H
 *  pares aleatorios de inteiros, estando o primeiro inteiro do par entre
 *  0 e N-1 e o segundo entre N e N+H-1; 0.1*N*A pares aleatorios de inteiros,
 *  estando o primeiro inteiro do par entre N+H e N+H+A-1 e o segundo entre
 *  0 e N-1.
 *  Imprime N na primeira linha. Na segunda imprime o valor inteiro de 0.1*N.
 *  Na terceira linha sao imprimidos os pares entre as paginas, na quarta
 *  entre paginas e hubs e na quinta entre paginas e autoridades.
 *  OBS: O valor 0.1*N e arredondado pra baixo (floor). Caso 0.1*N < 1, o
 *  valor e tomado como 1.
 *
 *  % java Generator 5 10 2 2
 *  9
 *  4 3  3 0  3 4  3 0  4 2  0 2  0 4  1 3  3 4  3 3  
 *  2 5  2 6  
 *  7 1  8 3  
 *
 ******************************************************************************/

public class Generator
{
   public static void main(String[] args)
   {
      int N = Integer.parseInt(args[0]); // quantidade de paginas
      int M = Integer.parseInt(args[1]); // quantidade de links
      int H = Integer.parseInt(args[2]); // quantidade de hubs
      int A = Integer.parseInt(args[3]); // quantidade de autoridades
      int M2;                            // quantidade de links entre paginas e hubs/autoridades

      M2 = (int) Math.floor(.10 * N);
      if (M2 == 0) M2 = 1;
      StdOut.println(N+H+A);

      // gera e imprime os M links entre as N paginas
      // pagina i tem o link para j
      for (int k = 0; k < M; k++) {
         int i = (int) (Math.random() * N);
         int j = (int) (Math.random() * N);
         StdOut.print(i + " " + j + "  ");
      }
      StdOut.println();

      // gera hubs e imprime os links
      for (int i = N; i < N+H; i++) {
         for (int k = 0; k < M2; k++) {
            int j = (int) (Math.random() * N);
            StdOut.print(j + " " + i + "  ");
         }
      }
      StdOut.println();

      // gera autoridades e imprime os links
      for (int i = N+H; i < N+H+A; i++) {
         for (int k = 0; k < M2; k++) {
            int j = (int) (Math.random() * N);
            StdOut.print(i + " " + j + "  ");
         }
      }
      StdOut.println();
   }
}
