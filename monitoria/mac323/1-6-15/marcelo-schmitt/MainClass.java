
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Marcelo Schmitt
 *
 */
public class MainClass {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int pageCount = Integer.parseInt(args[0]);
		int linkCount = Integer.parseInt(args[1]);
		int authorities = Integer.parseInt(args[2]);
		int hubs = Integer.parseInt(args[3]);
		
		Generator generator = new Generator(pageCount, linkCount, authorities, hubs);
		generator.generateLinks();
		generator.generateAuthorities();
		generator.generateHubs();
		
		generator.printInConsole();
		
		File file = new File("saida.txt");
		generator.writeInFile(file);
		
		System.out.println();
		
	}

}
