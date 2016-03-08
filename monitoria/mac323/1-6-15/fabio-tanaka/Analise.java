/******************************************************************************
 *  Nome: Fabio Henrique Kiyoiti dos Santos Tanaka
 *  Numero Usp: 9297982
 *  Compilation:  javac Analise.java
 *  Execution:    java Analise N A H < input.txt
 *
 *  Este programa le a media de acessos de N paginas, A autorithies e H hubs
 *  e imprime a media aritmetica simples das paginas "comuns", das autorithies
 *  e dos hubs.
 *
 *  % more entrada.txt
 *  0.02499
 *  0.07282
 *  0.02484
 *  0.10513
 *  0.13179
 *  0.21198
 *  0.04739
 *  0.10259
 *  0.04723
 *  0.02472
 *  0.02482
 *  0.02477
 *  0.08433
 *  0.07259
 *
 *  % java Analise < entrada.txt
 *                   Quantidade           Media de acessos por pagina
 *  Normais:         10                   0.083363
 *  Autorithies:      2                   0.02895
 *  Hubs:             2                   0.05424

 *
 ******************************************************************************/
public class Analise { 
    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        int A = Integer.parseInt(args[1]);
        int H = Integer.parseInt(args[2]);
        int cont;
        double media;
        double links[], hubs[], autorithies[];
        links = new double[N];
        autorithies = new double[A];
        hubs = new double[H];

        for (cont = 0; cont < N; cont++)
            links[cont] = StdIn.readDouble();
        for (cont = 0; cont < A; cont++)
            autorithies[cont] = StdIn.readDouble();
        for (cont = 0; cont < H; cont++)
            hubs[cont] = StdIn.readDouble();


        System.out.printf("             Quantidade              Media de acessos por pagina\n");
        media = 0.0;
        for (cont = 0; cont < N; cont++)
            media = media + links[cont];
        media = media/N;
        System.out.printf("Normais:%15d %40f", N, media);
        

        media = 0.0;
        for (cont = 0; cont < A; cont++)
            media = media + autorithies[cont];
        media = media/A;
        System.out.printf("\nAutorithies:%11d %40f", A, media);


        media = 0.0;
        for (cont = 0; cont < H; cont++)
            media = media + hubs[cont];
        media = media/H;
        System.out.printf("\nHubs:%18d %40f", H, media);

        System.out.println();    

    }
}