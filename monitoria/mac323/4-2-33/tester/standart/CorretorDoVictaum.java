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
        public boolean tl;

        ArgsPasser () { }
    }

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static int test(String nome, Function<ArgsPasser, Throwable> runner, long timeout, int repetitions, ArgsPasser args, int i, boolean keeptl) {
        String casename = String.format("%03d", i) + "_" + args.file.getFileName().toString().replace(".", "_");
        args.output = new Out("judge_output/" + casename + ".out");
        
        Callable<Throwable> task = ()-> (Throwable)runner.apply(args);
        ExecutorService executor = Executors.newFixedThreadPool(1);

        try {
            Throwable ret = null;
            while (repetitions > 0 && ret == null) {
                Future<Throwable> future = executor.submit(task);
                ret = future.get(timeout, TimeUnit.MILLISECONDS);
                --repetitions;
            }

            if (ret != null) {
                throw ret;
            }
            System.out.print(".");
        } catch (UnsupportedOperationException e) {
            args.output.println("TEMPO DE MONTAGEM EXCEDIDO");
            System.out.print("M");
        } catch (TimeoutException e) {
            args.output.println("TEMPO DE EXECUCAO EXCEDIDO");
            System.out.print("T");
        } catch (Throwable e) {
            args.output.println("ERRO DE EXECUCAO");
            System.out.print("R");
            while (e.getCause() != null)
                e = e.getCause();
            args.output.println(getStackTrace(e));
        } finally {
            executor.shutdownNow();
            if (!executor.isShutdown())
                System.err.println("NOT SHUT DOWN");
        }

        return 0;
    }

    public static Throwable mountGraph(ArgsPasser args) {
        args.tl = true;
        In in = new In(args.file.toString());
        String[] strings = new String[args.siz];

        for (int i = 0; i < args.siz; i++)
            strings[i] = in.readLine();

        try {
            args.ex = new WordDAG(strings);
        } catch (Throwable e) {
            return e;
        }
        args.tl = false;

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
        if (args.tl == true) {
            return new UnsupportedOperationException();
        }
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
        env.tl = false;
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
                nota -= test("Monta o grafo num tempo razoável", CorretorDoVictaum::mountGraph, 120000, 1, env, i, true);
            } else if (command.equals("count")) {
                env.a = StdIn.readString();
                env.b = StdIn.readString();
                nota -= test("Conta caminho corretamente", CorretorDoVictaum::printPathCount, 3000, 1, env, i, false);
            }
        }
        System.out.println("");

        System.exit(0);
    }
}
