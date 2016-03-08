/*
 * Nome: Mathias Van Sluys Menck
 * numUSP: 4343470
*/


public class RandomWeb
{
  public static void main(String[] args)
  {
    int N, M, H, A;
    N = Integer.parseInt(args[0]);
    M = Integer.parseInt(args[1]);
    H = Integer.parseInt(args[2]);
    A = Integer.parseInt(args[3]);
    int p1, p2;

    StdOut.printf("%d\n", N);
    /*Cria a random Web base a ser usada*/
    for(int i = 0; i<M; i++)/*FAZER em stdout direto?*/
    {
      p1 = p2 = StdRandom.uniform(N);
      /*O while abaixo garante que x nao aponte pra x*/
      while (p1 == p2) p2 = StdRandom.uniform(N);
      StdOut.printf("  %d %d  ", p1, p2);
      if(i%5 == 0) StdOut.println();
    }

    StdOut.println();
StdOut.println();
    M = N/10;
    for(int i = 0; i<M; i++)
    {
      /*Authority*/
      p1 = N+H+ StdRandom.uniform(A);
      p2 = StdRandom.uniform(N);
      StdOut.printf("  %d %d  ", p1, p2);
      /*Hub*/
      p1 = StdRandom.uniform(N);
      p2 = N+ StdRandom.uniform(H);
      StdOut.printf("  %d %d  ", p1, p2);
      if(i%2 == 0) StdOut.println();
    }
  }
}
