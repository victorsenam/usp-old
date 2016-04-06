import edu.princeton.cs.algs4.*;
import java.util.function.Function;

public class Tester {
    public static int test (int discount, String nome, Function<Integer, String> runner, Integer arg) {
        StdOut.println("Teste: " + nome);
        try {
            String passed = (String) runner.apply(arg);
            if (passed == "") {
                StdOut.println("ACCEPTED");
                discount = 0;
            } else {
                StdOut.print("[-" + discount + "] ");
                StdOut.println(passed);
            }
        } catch (Throwable e) {
            StdOut.println("ERRO DE EXECUÇÃO");
            e.printStackTrace();
        }
        StdOut.println("---------------");
        return discount;
    }

    public static String independentQueues(Integer siz) {
        RandomQueue<Integer> first = new RandomQueue<Integer>();
        RandomQueue<Integer> second = new RandomQueue<Integer>();
        
        for (int i = 0; i < siz; i++) {
            first.enqueue(i);
            second.enqueue(i);
        }

        while (!first.isEmpty()) {
            if (first.dequeue() != second.dequeue())
                return "";
        }
        return "Filas independentes ficam iguais";
    }

    private static String repetitions(int siz) {
        RandomQueue<Integer> queue = new RandomQueue<Integer>();
        boolean[] visited = new boolean[siz];

        for (int i = 0; i < siz; i++) {
            queue.enqueue(i);
            visited[i] = false;
        }

        while (!queue.isEmpty()) {
            int rm = queue.dequeue();
            if (rm < 0 || rm >= siz)
                return "Valor não inserido retornado";
            if (visited[rm])
                return "Repetição de valor retornado";
            visited[rm] = true;
        }

        return "";
    }

    public static void main(String[] args) {
        // Duas queues independentes deveriam gerar permutações diferentes quando alimentadas com a mesma entrada
        int nota = 1000;

        nota -= test(200, "Filas Independentes", Tester::independentQueues, 10);
        nota -= test(200, "Repetições", Tester::repetitions, 200);
    }
}
