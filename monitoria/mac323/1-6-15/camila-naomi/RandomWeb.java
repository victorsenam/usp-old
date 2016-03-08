/* **************************************** */
/*  Nome: Camila Naomi Kodaira              */
/*  Numero USP: 4266602                     */
/* **************************************** */

/* **************************************** */
/*  Random Web                              */
/*  Esse programa gera m pares aleatorios   */
/*  entre 0 ... n-1 inteiros, depois ele    */
/*  adiciona hub hubs e aut authorities e   */
/*  gera n/10 pares de (aut, _) e (_, hub)  */
/*  Obs: Os pares sao printados no terminal */
/*                                          */
/*  Linha de comando:                       */
/*  java-introcs RandomWeb n m hub aut      */
/*                                          */
/*  Output:                                 */
/*  n + hub + aut                           */
/*  (_,_) <- Lista de pares                 */
/* **************************************** */

public class RandomWeb {
  public static void main (String[] args) {
      int n, m, hub, aut, cont, cont2;
    int [] aux = new int[2];
    double rand;
    n = Integer.parseInt (args[0]);
    m = Integer.parseInt (args[1]);
    hub = Integer.parseInt (args[2]);
    aut = Integer.parseInt (args[3]);

    StdOut.printf ("%d\n", (n + hub + aut));

    //Pares normais
    for (cont = 0; cont < m; cont++) {
      aux[0] = (int) (Math.random () * n);
      aux[1] = (int) (Math.random () * n);
      StdOut.printf ("%d %d\n", aux[0], aux[1]);
    }
    
    //Hubs
    for (cont = 0; cont < hub; cont++) {
      aux[1] = n + cont;
      for (cont2 = 0; cont2 < n / 10; cont2++) {
        aux[0] = (int) (Math.random () * n);
        StdOut.printf ("%d %d\n", aux[0], aux[1]);
      }
    }
    
    //Authorities
    for (cont = 0; cont < aut; cont++) {
      aux[0] = n + hub + cont;
      for (cont2 = 0; cont2 < n / 10; cont2++) {
        aux[1] = (int) (Math.random () * n);
        StdOut.printf ("%d %d\n", aux[0], aux[1]);
      }
    }
  }
}
