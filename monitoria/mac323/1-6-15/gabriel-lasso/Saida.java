/*
Para compilar:
javac-introcs Saida.java

Para rodar:
java-introcs Saida N H A
X_1 X_2 ... X_N Y_1 Y_2 .. Y_H Z_1 Z_2 ... Z_A
em que 
N é a quantidade de páginas,
H é a quantidade de hubs,
A é a quantidade de autoridades,
X_n é a quantidade de acessos na página n,
Y_h é a quantidade de acessos na hub h,
Z_a é a quantidade de acessos na autoridade a
*/

public class Saida {
    static public void main (String args[]) {
        int i, N, H, A;
        double sN = 0, sH = 0, sA = 0, mN, mH, mA;
        N = Integer.parseInt (args[0]);
        H = Integer.parseInt (args[1]);
        A = Integer.parseInt (args[2]);
        for (i = 0; i < N; i++)
            sN += StdIn.readDouble ();
        mN = sN / N;
        for (i = 0; i < H; i++)
            sH += StdIn.readDouble ();
        mH = sH / H;
        for (i = 0; i < A; i++)
            sA += StdIn.readDouble ();
        mA = sA / A;

        StdOut.printf ("Média de acesso das páginas normais:\t%8.5f\n", mN);
        StdOut.printf ("Média de acesso dos Hubs:\t\t%8.5f\n", mH);
        StdOut.printf ("Média de acesso das Authorities:\t%8.5f\n", mA);

        if (mA > mH) {
            StdOut.println ("Authorities tiveram mais acessos que Hubs.");
        }
        else {
            StdOut.println ("Hubs tiveram mais acessos que Authorities."); // Esperado
        }
    }
}
