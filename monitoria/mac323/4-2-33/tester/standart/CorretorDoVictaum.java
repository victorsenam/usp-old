import edu.princeton.cs.algs4.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.io.*;
import java.nio.file.*;

public class CorretorDoVictaum {

    private static class ArgsPasser {
        public int siz;
        public String a, b;
        public WordDAG ex;
        public BufferedInputStream exOut;
        public Out output;
        public In answer;
        public Path file;

        ArgsPasser () { }
    }

    public static int test(String nome, Function<ArgsPasser, Throwable> runner, long timeout, int repetitions, ArgsPasser args, int i) {
        String casename = args.file.getFileName().toString().replace(".", "_") + "_" + String.format("%03d", i);
        args.output = new Out("judge_output/" + casename + ".out");
        
        Callable<Throwable> task = ()-> (Throwable)runner.apply(args);
        ExecutorService executor = Executors.newFixedThreadPool(1);

        try {
            Throwable ret = null;
            while (repetitions > 0 && ret == null) {
                Future<Throwable> future = executor.submit(task);
                future.get(timeout, TimeUnit.MILLISECONDS);
                --repetitions;
            }

            if (ret != null) 
                throw ret;
        } catch (TimeoutException e) {
            args.output.println("TEMPO DE EXECUÇÃO EXCEDIDO");
        } catch (Throwable e) {
            args.output.println("ERRO DE EXECUÇÃO");
            while (e.getCause() != null)
                e = e.getCause();
            args.output.println(e.getStackTrace());
        } finally {
            executor.shutdownNow();
            if (!executor.isShutdown())
                System.err.println("NOT SHUT DOWN");
        }
    }

    public static Throwable mountGraph(ArgsPasser args) {
        In in = new In(args.file.toString());
        String[] strings = new String[args.siz];

        for (int i = 0; i < args.siz; i++)
            strings[i] = in.readLine();

        try {
            args.ex = new WordDAG(strings);
        } catch (Throwable e) {
            return e;
        }
        return null;
    }

    public static Throwable printGraph(ArgsPasser args) {
        try {
            BufferedInputStream auxStream = getBuffer();
            args.ex.PrintDag();

            if (args.exOut.available() <= 0)
                args.exOut = auxStream;
            while (args.exOut.available() > 0)
                args.output.print((char) args.exOut.read() );
        } catch (Throwable e) {
            return e;
        }
        return null;
    }

    public static Throwable printPathCount(ArgsPasser args)  {
        try {
            BufferedInputStream auxStream = getBuffer();
            args.ex.PrintPathCount(args.a, args.b);

            if (args.exOut.available() <= 0)
                args.exOut = auxStream;
            while (args.exOut.available() > 0)
                args.output.print((char) args.exOut.read() );
        } catch (Throwable e) {
            return e;
        }
        return null;
    }

    public static BufferedInputStream getBuffer() throws Exception {
        return new BufferedInputStream(new FileInputStream( "saida.txt" ) );
    }

    public static void main(String args[]) {
        int nota = 10000;
        long stdTime = 1000;

        ArgsPasser env = new ArgsPasser();
        try {
            new Out("saida.txt");
            env.exOut = getBuffer();
        } catch (Exception e) {
            System.err.println("This failed!");
            return;
        }

        int n = StdIn.readInt();
        int stdDiscount = nota/n;
        
        for (int i = 0; i < n; i++) {
            String command = StdIn.readString();
            if (command.equals("mount")) {
                env.file = Paths.get(StdIn.readString());
                env.siz = StdIn.readInt();
                nota -= test("Monta o grafo num tempo razoável", CorretorDoVictaum::mountGraph, 10000, 1, env, i);
            } else if (command.equals("print")) {
                nota -= test("Imprime o grafo corretamente", CorretorDoVictaum::printGraph, 10000, 1, env, i);
            } else if (command.equals("count")) {
                env.a = StdIn.readString();
                env.b = StdIn.readString();
                nota -= test("Conta caminho corretamente", CorretorDoVictaum::printPathCount, 10000, 1, env, i);
            }
        }

        System.exit(0);
    }
}
