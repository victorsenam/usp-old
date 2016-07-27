import java.util.Arrays;

public class PageRanker {
    private SparseMatrix link_probability_matrix;
    static private double alpha = 0.95;
    private final double epsilon = 1e-8;
    private final int max_it = 10000000;

    public PageRanker (SparseMatrix link_probs, double alpha) {
        this.link_probability_matrix = link_probs;
        this.alpha = alpha;
    }

    private double euclidianDistance (double[] x, double[] y){
        double sum = 0;
        for(int i = 0; i < x.length; i++){
            sum = (x[i] - y[i]) * (x[i] - y[i]);
        }
        return Math.sqrt(sum);
    }

    public double[] findRank (int start) {
        int n = this.link_probability_matrix.dimension();
        double[] rank = new double[n];
        double[] previous_rank;
        double jumpProbability = (1.0 - this.alpha)/n;

        int numIts = 0;
        rank[start] = 1.0;

        do {
            numIts += 1;
            previous_rank = rank.clone();
            rank = this.link_probability_matrix.times_c(rank); //pi = pi*G + (1-a)/n * D = pi*G + (1-a)/n

            for(int i = 0; i < rank.length; i++)
                rank[i] += jumpProbability;

        } while( euclidianDistance(rank,previous_rank) > epsilon && numIts < max_it );

        return rank;
    }

    public static void main (String[] args) {
        SparseMatrix A = new SparseMatrix(5);

        A.put(0, 1, 0.90);
        A.put(1, 2, 0.36);
        A.put(1, 3, 0.36);
        A.put(1, 4, 0.18);
        A.put(2, 3, 0.90);
        A.put(3, 0, 0.90);
        A.put(4, 0, 0.45);
        A.put(4, 2, 0.45);

        PageRanker bla = new PageRanker(A, alpha);
        double[] mi = bla.findRank(0);
        for(double i : mi)   System.out.println(i);
        System.out.println("");

    }


}
