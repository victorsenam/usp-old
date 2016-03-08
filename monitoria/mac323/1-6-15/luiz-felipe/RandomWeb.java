// Autor:  Luiz Felipe Moumdjian Girotto                                                   
// Numero USP: 8941189                                    
// Data: 2016-02-28   

public class RandomWeb {
    public static void main(String[] args) { 
        int N = Integer.parseInt(args [0]);
        int M = Integer.parseInt(args [1]);
        int i, hub, auth;
        System.out.printf ("%d\n", N);
        // Fazendo a geracao aleatoria
        for (i = 0; i < M; i++)
            System.out.printf ("%d %d\n", StdRandom.uniform (N), StdRandom.uniform (N));
        
        // Definiremos apenas 1 hub e uma authority, uma vez que o numero e fixo e arbitrario.
        hub = StdRandom.uniform (N);
        while (true) {
            auth = StdRandom.uniform (N);
            if (auth != hub) break;
        }
        // Gerando o hub
        for (i = 0; i < M/10; i++)
            System.out.printf ("%d %d\n", StdRandom.uniform (N), hub);
        
        // Gerando a authority
        for (i = 0; i < M/10; i++)
            System.out.printf ("%d %d\n", auth, StdRandom.uniform (N));  
    }
}
