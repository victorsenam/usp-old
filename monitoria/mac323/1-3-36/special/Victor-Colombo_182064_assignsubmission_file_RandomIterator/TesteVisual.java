import java.util.*;
import edu.princeton.cs.algs4.*;
// Victor de Oliveira Colombo - 8988657

import java.util.*;

public class TesteVisual {

    public static int factorial(int n) {
        return (n == 0) ? 1 : n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]), t = Integer.parseInt(args[1]);
        Histogram histogram = new Histogram(factorial(n));
        RandomQueue<Integer> queue = new RandomQueue<Integer>();
        for(int i = 0; i < n; i++)
            queue.enqueue(i);
        ArrayList< ArrayList<Integer> > data = new ArrayList< ArrayList<Integer> >();
        for(int i = 0; i < t; i++) {
            ArrayList<Integer> permutation = new ArrayList<Integer>();
            for(int v : queue)
                permutation.add(v);
            data.add(permutation);
        }
        Comparator<ArrayList<Integer>> comparator = new Comparator<ArrayList<Integer>>() {
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                for(int i = 0; i < a.size(); i++) {
                    if(a.get(i) < b.get(i))
                        return -1;
                    if(a.get(i) > b.get(i))
                        return 1;
                }
                return 0;
            }
        };
        Collections.sort(data, comparator);
        int index = 0;
        histogram.addDataPoint(index);
        ArrayList<Integer> lastPerm = data.get(0);
        for(int i = 1; i < t; i++) {
            if(comparator.compare(lastPerm, data.get(i)) != 0) {
                lastPerm = data.get(i);
                index++;
            }
            else
                histogram.addDataPoint(index);
        }
        histogram.draw();
    }
}
