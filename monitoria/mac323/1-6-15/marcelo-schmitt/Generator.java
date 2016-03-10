

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author Marcelo Schmitt
 *
 */
public class Generator {
	/*
Random web. Write a generator for Transition.java that takes as input a page 
count N and a link count M and prints to standard output N followed by M random 
pairs of integers from 0 to N-1.

Hubs and authorities. Add to your generator from the previous exercise a 
fixed number of hubs, which have links pointing to them from 10% of the pages, 
chosen at random, and authorities, which have links pointing from them to 10% of
 the pages. Compute page ranks. Which rank higher, hubs or authorities? 
	 */

	private int pageCount;
	private int linkCount;
	private int[] linksFrom;
	private int[] linksTo;
	private int authorities;
	private int hubs;
	private int authoritieLinks; //Número total de links que serão gerados para as páginas authoritie.
	private int hubLinks; //Número total de links que serão gerados para as páginas hub.
	
	private Random random;
	private FileWriter fileWriter;
	
	public Generator(int pageCount, int linkCount) {
		this.pageCount = pageCount;
		this.linkCount = linkCount;
		this.linksFrom = new int[linkCount];
		this.linksTo = new int[linkCount];
		random = new Random();
		this.authorities = 0;
		this.hubs = 0;
	}
	
	/**
	 * 
	 * @param pageCount número de páginas normais que terão links entre elas 
	 * 						gerados de forma aleatória.
	 * @param linkCount número de links gerados entre as páginas normais.
	 * @param authorities número de páginas authoritie que terão links apontando 
	 * 						para 10% das páginas normais.
	 * @param hubs número de páginas hub que terão 10% das páginas normais 
	 * 					apontando para elas.
	 */
	public Generator(int pageCount, int linkCount, int authorities, int hubs) {
		this.pageCount = pageCount;// + hubs + authorities;
		this.linkCount = linkCount;
		this.authoritieLinks = (int) Math.round(authorities*0.1*pageCount);
		this.hubLinks = (int) Math.round(hubs*0.1*pageCount);
		this.linksFrom = new int[linkCount + authoritieLinks + hubLinks];
		this.linksTo = new int[linkCount+ authoritieLinks + hubLinks];
		this.hubs = hubs;
		this.authorities = authorities;
		random = new Random();
	}
	
	/**
	 * Método para gerar os links entre as páginas.
	 * Gera os links entre páginas sem considerar posíveis authorities e hubs.
	 */
	public void generateLinks() {
		for (int i = 0; i < linkCount; i++) {
			linksFrom[i] = random.nextInt(pageCount);
			linksTo[i] = random.nextInt(pageCount);
		}
	}
	
	/**
	 * Método para gerar os links das páginas autoridades.
	 * Deve ser chamado antes de generateHubs().
	 */
	public void generateAuthorities() {
		for (int i = 0; i < authorities; i++) {
			for (int j = 0; j < 0.1*pageCount; j++) {
				linksFrom[linkCount + i*authorities + j] = pageCount + i;
				linksTo[linkCount + i*authorities + j] = random.nextInt(pageCount);
			}
		}
	}
	
	/**
	 * Método para gerar os links das páginas hub.
	 * Deve ser chamando depois de generateAuthorities().
	 */
	public void generateHubs() {
		for (int i = 0; i < hubs; i++) {
			for (int j = 0; j < 0.1*pageCount; j++) {
				linksFrom[linkCount + authoritieLinks + i*hubs + j] = random.nextInt(pageCount);
				linksTo[linkCount + authoritieLinks + i*hubs + j] = pageCount + authorities + i;
			}
		}
	}
	
	/**
	 * 
	 * @param file - the file in witch to write the number of pages and it's 
	 * 					links.
	 * @return 1 if the data was written sucssesfuly, 0 otherwise.
	 */
	public int writeInFile(File file) {
		try {
			fileWriter = new FileWriter(file);
			fileWriter.append(String.valueOf(pageCount + authorities + hubs));
			fileWriter.append("\n");
			for (int i = 0; i < linksFrom.length; i++) {
				fileWriter.append(linksFrom[i] + " " + linksTo[i] + "\n");
			}
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public void printInConsole() {
		System.out.println(String.valueOf(pageCount + authorities + hubs));
		for (int i = 0; i < linksFrom.length; i++) {
			System.out.println(linksFrom[i] + " " + linksTo[i]);
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getLinkCount() {
		return linkCount;
	}

	public void setLinkCount(int linkCount) {
		this.linkCount = linkCount;
	}

	public int[] getLinksFrom() {
		return linksFrom;
	}

	public void setLinksFrom(int[] linksFrom) {
		this.linksFrom = linksFrom;
	}

	public int[] getLinksTo() {
		return linksTo;
	}

	public void setLinksTo(int[] linksTo) {
		this.linksTo = linksTo;
	}

	public int getHubs() {
		return hubs;
	}

	public void setHubs(int hubs) {
		this.hubs = hubs;
	}

	public int getAuthorities() {
		return authorities;
	}

	public void setAuthorities(int authorities) {
		this.authorities = authorities;
	}
	
}
