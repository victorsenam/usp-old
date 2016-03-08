// Victor Andreas Sprengel
// 9298002
// Para testar: (exemplo)
// java HubsAndAuthorities 50 100 3 3 | java newTransition | java RandomSurfer 1000000
//
// Resposta sobre efeitos de Hubs e Authorities:
//
// Hubs tendem a receber mais link que a maioria das pages, logo elas tem um
// PageRank alto e diminuem o PageRank do resto
//
// Authorities tendem a receber menos links que a maioria das pages, logo
// elas tem um PageRank baixo e aumentam o PageRank do resto das paginas
// 
// Porem, 0.1*n eh uma quantidade baixa de links recebidos/enviados
// Nos exemplos dos pdfs de transparencia, os exemplos de Hubs ou 
// Authorities sempre contiveram pelo menos cerca de 0.2*n.
//
// Logo, usando esse gerador o efeito descrito acima nao sera
// muito percebido.

public class HubsAndAuthorities {

    // p for the proportion of links recieved/sent by Hubs or Authorities
    public static double p = 0.1;

    // Returns random integer in interval [min;max)
    public static int randomInt(int min, int max) {
        return (min + (int) (Math.random() * (max-min)));
    }

    // Add m random transitions to a graph represented by a n*n matrix
    public static void addRandomTransitions(int[][] c, int n, int m) {
        int pointer, pointed;

        for (int i = 0; i < m; i++) {
            pointer = randomInt(0, n);
            pointed = randomInt(0, n);
            c[pointer][pointed] += 1;
        }
    }

    // Add random transitions to a number h of hubs from 10% of
    // all other nodes for each hub. Graph represented by the nxn
    // matrix c
    public static void addHubsTransitions(int[][] c, int n, int h) {
        int k = (int) Math.round(n * p);
        int count, candidate;
        boolean[] alreadyPointer;

        for (int i = n; i < n + h; i++) {
            count = 0;
            alreadyPointer = new boolean[n];
            while (count < k) {
                candidate = randomInt(0, n);
                if (!alreadyPointer[candidate]) {
                    alreadyPointer[candidate] = true;
                    c[candidate][i] += 1;
                    count += 1;
                }
            }
        }
    }

    // For each one of the a Authorities, add to them links to 10% of 
    // the other nodes. Graph represented by the nxn matrix c
    public static void addAuthoritiesTransitions(int[][] c, int n, int a) {
        int nha = c.length;
        int k = (int) Math.round(n * p);
        int count, candidate;
        boolean[] alreadyPointed;

        for (int i = nha - 1; i >= nha - a; i--) {
            count = 0;
            alreadyPointed = new boolean[n];
            while (count < k) {
                candidate = randomInt(0, n);
                if (!alreadyPointed[candidate]) {
                    alreadyPointed[candidate] = true;
                    c[i][candidate] += 1;
                    count += 1;
                }
            }
        }
    }

    // Prints all arcs of graph represented by matrix m
    // in the way specified for Transition.java
    public static void printAll(int[][] m) {
        int n = m.length;
        boolean printed = false;

        System.out.printf("%d\n\n", n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m[i][j]; k++) {
                    printed = true;
                    System.out.printf("%d %d  ", i, j);
                }
            }
            if (printed)
                System.out.printf("\n");
            printed = false;
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]); // number of nodes
        int M = Integer.parseInt(args[1]); // number of arcs
        int H = Integer.parseInt(args[2]); // number of inserted hubs
        int A = Integer.parseInt(args[3]); // number of inserted authorities

        int count[][] = new int[N+H+A][N+H+A];

        addRandomTransitions(count, N, M);

        addHubsTransitions(count, N, H);

        addAuthoritiesTransitions(count, N, A);

        printAll(count);

    }
}



