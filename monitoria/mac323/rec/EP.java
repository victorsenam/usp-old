import java.util.Arrays;

public class EP {
    static private double alpha = 0.95;
    static private String fileName = null;
    static private String xmlOSM = null;
    static private boolean osmFile = false;
    static private boolean giantCC = false;
    static private int outputType = 1; // 1 - normal | 2 - ordenado | 3 - grafico 
    static private int limit = -1; 
    static private EdgeWeightedDigraph G;
    static private double[] area;
    
    public static void printHelp(){
        System.out.println("Como usar: java EP <nome_do_arquivo> [args]");
        System.out.println("Possíveis argumentos:");
        System.out.println("\t-a <VAL>: Determina valor de alpha igual a VAL. Default: 0.95;");
        System.out.println("\t-o: Determina que o tipo do arquivo de entrada é OSM, usando <xml_file>;");
        System.out.println("\t\t como arquivo .xml de suporte para esse tipo de entrada.");
        System.out.println("\t-g <xml_file>: Roda o PageRank somente na maior SCC;");
        System.out.println("\t-v <VAL>: Determina o tipo de saida do programa:");
        System.out.println("\t\t <VAL> == 1: Impressão da distribuição limite toda (Default);");
        System.out.println("\t\t <VAL> == 2: Impressão da distribuição limite ordenada;");
        System.out.println("\t\t <VAL> == 3: Apresentação do resultado na interface gráfica");
        System.out.println("\t-l <VAL>: Número máximo <VAL> de ranks impressos na opção 2.");
        System.out.println("\t-h: Imprime essa mensagem.");
    }

    public static boolean parseArgs(String[] args) {

        int n = args.length;
        if(n <= 0) {printHelp(); return false;}
        for(int i = 0; i < n; i++){
            String arg = args[i];
            char c = arg.charAt(0);
            char c2 = arg.charAt(1);
            if(c != '-') fileName = arg;
            else if(c2 == 'a') alpha = Double.parseDouble(args[++i]);
            else if(c2 == 'o'){
		    xmlOSM = args[++i];
		    osmFile = true;
	    }
            else if(c2 == 'g'){
		    giantCC = true;
	    }
            else if(c2 == 'h') {printHelp(); return false;}
            else if(c2 == 'v') outputType = Integer.parseInt(args[++i]);
	    else if(c2 == 'l') limit = Integer.parseInt(args[++i]);
            else{
                System.out.println("Argumento inválido: "+ arg);
                printHelp();
                return false; // Erro
            }
        }

        if(fileName == null) {
            System.out.println("ERRO: Nome do arquivo não informado!\n");
            printHelp();
            return false;
        }

        return true;
    }

    
    public static void main(String[] args) {
        BuildSCCSparseMatrix sccBuilder = new BuildSCCSparseMatrix();
        BuildFullSparseMatrix matrixBuilder = new BuildFullSparseMatrix();
    	SparseMatrix A = null;
        PageRanker r1, r2;
    	SymbolEWDigraph sg;

        if(!parseArgs(args)) return;

        if(osmFile){
            //String filenameADJ  = args[0];
            //String filenameXML = args[1];
	    try{
		    sg = new SymbolEWDigraph(fileName, xmlOSM);
	    }catch(Exception e){
		    System.out.println("ERRO ao tentar criar grafo dos arquivos OSM.");
		    System.out.println(e.getMessage());
		    return;
	    }
	    if(!giantCC)
		    A = BuildFullSparseMatrix.sparseMatrixFromDigraph(sg.D(), alpha);
	    else
		    A = BuildSCCSparseMatrix.biggestSCCSparseMatrix(sg.D(), alpha);
	    

	    area = new double[4];

        }
        else{ 
            // Arquivo normal
            if(!giantCC)
		        A = BuildFullSparseMatrix.mountTransitionMatrix(fileName, alpha);
            else{
		        BuildSCCSparseMatrix.buildDigraph(fileName);
		        A = BuildSCCSparseMatrix.biggestSCCSparseMatrix(alpha);
            }
        }

        r1 = new PageRanker(A, alpha);

        if(outputType == 1){
            // Imprime normal
             double[] d1 = r1.findRank(0);
             for(double i : d1)   System.out.println(i);

        }
        else if(outputType == 2){ 
            // Imprime ordenado
            double[] d1 = r1.findRank(0);
            Arrays.sort(d1);
	    if(limit == -1) limit = d1.length;
	        int count = 0;
            for(double i : d1){
		        if(count++ >= limit) break;
		        System.out.println(i);
            }
        }
        else{ 
            // Interface gráfica
            String interacaoEP = "n";
            //SymbolGeoEWDigraph.Plot(area, sg, "vertices"); 
        }
    }
}
