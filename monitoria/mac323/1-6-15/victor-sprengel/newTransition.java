// Victor Andreas Sprengel
// 9298002
// Programa copiado do Transition.java, apenas com uma pequena
// modificacao para deixar o RandomSurfer bem definido
import java.util.Scanner;


public class newTransition {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] count = new int[N][N];
        int[] outDegree = new int[N];

        while (sc.hasNext()) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            outDegree[i] += 1;
            count[i][j] += 1;
        }
        System.out.println(N + " " + N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double p;
                if (outDegree[i] == 0)  // Este e o caso em que o problema nao 
                    p = 1.0 / N;        // estava bem definido
                else
                    p = .90*count[i][j]/outDegree[i] + .10/N;
                System.out.printf("%7.5f ", p);
            }
            System.out.println();
        }
    }
}

