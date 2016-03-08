/*Nome: Rodrigo Zanette de Magalhães       */
/*Nº USP: 9298090                          */
/****************************************************************************/
/*Compile: javac-algs4 hubsandauthorities.java                              */
/*         javac-algs4 Transition.java                                      */
/*         javac-algs4 RandomSurfer.java                                    */
/*Execute: java-algs4 hubsandauthorities > "file".txt                       */
/*         java-algs4 Transition < "file".txt | java-algs4 RandomSurfer 10^6*/
/****************************************************************************/
public class hubsandauthorities {
    public static void main (String args[]) {

        int N = Integer.parseInt (args[0]); /*total de vértices*/
        int M = Integer.parseInt (args[1]); /*total de arestas*/
        int H = Integer.parseInt (args[2]); /*total de Hubs*/
        int A = Integer.parseInt (args[3]); /*total de Authorities*/
        
        int i, j, aux1, aux2, cont=0;
        /*i, j: índices;
        aux1, aux2: variáveis auxiliares;
        cont: conta quantas arestas já foram criadas*/
        
        int connections[][] = new int[N][N];
        /*matriz que vai contar as conexões entre as arestas na hora de
        gerar a web aleatória;
        -> connections[i][j] -> quantidade de arestas que partem de i e 
        "chegam" em j;*/
        int Hubs[] = new int[H]; /*vetor que indicará quais vértices são os hubs*/
        int Auth[] = new int[A]; /*vetor que indicará quais vértices são as
        authorities*/
        /*zerando a matriz contadora*/
        for (i=0; i<N; i++) {
            for (j=0; j<N; j++) {
                connections[i][j]=0;
            }
        }
        /*definindo os hubs ~apontam~*/
        aux1=(int) (Math.random()*N);
        Hubs[0]=aux1; i=1;
        while (i<H) {
            aux1=(int) (Math.random()*N);
            aux2=1;
            for (j=0; j<i; j++) { if (aux1==Hubs[j]) aux2=0; }
            if (aux2==1) i++;
        }
        /*definindo as authorities ~recebem~*/
        aux1=(int) (Math.random()*N);
        Auth[0]=aux1; i=1;
        while (i<A) {
            aux1=(int) (Math.random()*N);
            aux2=1;
            for (j=0; j<i; j++) { if (aux1==Auth[j]) aux2=0; }
            if (aux2==1) i++;
        }
        /*criando as conexões dos hubs*/
        for (i=0; i<H; i++) {
            for (aux1=0; aux1<(int) (M/10+1); aux1++) {
                j=Hubs[i];
                while (j==Hubs[i]) {
                    j=(int) (Math.random()*N); /*p/a página não apontar pra si mesma*/
                }
                connections[Hubs[i]][j]++;
                cont++;
            }
        }
        /*criando as conexões das authorithies*/
        for (j=0; j<H; j++) {
            for (aux1=0; aux1<(int) (M/10+1); aux1++) {
                i=Auth[j];
                while (i==Auth[j]) {
                    i=(int) (Math.random()*N); /*p/a página não apontar pra si mesma*/
                }
                connections[i][Auth[j]]++;
                cont++;
            }
        }
        
        /*outras conexões restantes*/
        while (cont<M) {
            i=(int) (Math.random()*N);
            j=i;
            while (j==i) { j=(int) (Math.random()*N); }
            connections[i][j]++;
            cont++;
        }

        System.out.printf ("%d\n", N);
        for (i=0; i<N; i++) {
            for (j=0; j<N; j++) {
                for (aux1=0; aux1<connections[i][j]; aux1++) {
                    System.out.printf ("%d %d  ", i, j);
                }
            }
            System.out.printf ("\n");
        }
        
    }
}
