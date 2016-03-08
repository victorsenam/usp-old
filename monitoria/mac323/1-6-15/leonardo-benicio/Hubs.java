/* ##############################################################
   #####################   Header   #############################
   ##############################################################
   #                                                        	 # 
   #  MAC-0323                                              	 #
   #  Creative Exercise 1.6.15 (Hubs and authorities; IntroCS)  #
   #  Name: Leonardo Araujo Benicio dos Santos              	 #
   #  USP Number: 8536110                                   	 #
   #                                                        	 #
   #                                                        	 #
   ##############################################################
   ####################   Debugging   ###########################
   ##############################################################
   # Compiled: javac-algs4 BulgingSquares.java              	 #
   # Executed: java-algs BulgingSquares                     	 #
   # No known bugs                                          	 #
   #                                                        	 #
   # Hubs has highier PageRansk than authorities                #
   ##############################################################
*/
public class Hubs {
	public static void main (String[] args){
      int N = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      int H = Integer.parseInt(args[2]);
      int A = Integer.parseInt(args[3]);
      int links;
      int[][] nav = new int[N][2 + 2*M];

      if (M < N) throw new RuntimeException("M < N");

      // set -1 entire matrix to use as marker to print links
      for (int i = 0; i < N; i++){
         for (int j = 0; j < 2 + 2*M; j++){
            nav[i][j] = -1;
         }
      }

      // declaring links for hubs and authorities
      if (N < 10) {
         links = 1;
      }

      else {
         links = N / 10;
      }


      // print N
      StdOut.println(N);

      // Generate M pairs
      for (int i = 0; i < N; i++){
         //N-1 is the maximum and the 0 is our minimum
         nav[i][0] = i;
         nav[i][1] = StdRandom.uniform(N-1);
      }

      // Creating authorities
      for (int j = 0; j < A; j++){
         int a = StdRandom.uniform(N-1);

         for (int i = 0; i < links; i++){
            int r = StdRandom.uniform(N-1);

            int count = 0;
            while (nav[a][count] != -1){
               count++;
            }

            nav[a][count] = a;
            nav[a][count + 1] = r;
         }
      }

      // Creating hubs
      for (int j = 0; j < H; j++){
         int h = StdRandom.uniform(N-1);

         for (int i = 0; i < links; i++){
            int r = StdRandom.uniform(N-1);

            int count = 0;
            while (nav[r][count] != -1){
               count++;
            }

            nav[r][count] = r;
            nav[r][count + 1] = h;
         }
      }
         
      // generating the rest of links
      for (int i = 0; i < M - 1 - (A * links) - (H * links); i++){
         int page = StdRandom.uniform(N-1);
         int refer = StdRandom.uniform(N-1);

         int count = 0;
         while (nav[page][count] != -1){
            count++;
         }

         nav[page][count] = page;
         nav[page][count + 1] = refer;
      }


      // print result;
      for (int i = 0; i < N; i++){
         for (int j = 0; j < 2 + 2*M; j++){
            if (nav[i][j] != -1){
               StdOut.printf(nav[i][j] + " ");
            }
            
         }
         StdOut.printf("\n");
      }
	}
}