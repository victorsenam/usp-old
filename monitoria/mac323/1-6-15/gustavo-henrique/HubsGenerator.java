//GUSTAVO H F SILVA - 9298260

import java.util.Random;

public class HubsGenerator {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
		int hubs = Integer.parseInt(args[2]);
		int auths = Integer.parseInt(args[3]);
		int linkhubs = m/10;
		int linkauths = m/10;
		int[][] links = new int[m][2];
		int[][] morelinks = new int[linkauths+linkhubs][2];
		Random r = new Random();
		for(int i = 0; i < m; i++) {
			links[i][0] = r.nextInt(n);
			links[i][1] = r.nextInt(n);
		}
		for(int i = 0; i < linkhubs; i++) {
			morelinks[i][0] = r.nextInt(n);
			morelinks[i][1] = r.nextInt(hubs)+n;
		}
		for(int i = linkhubs; i < linkauths+linkhubs; i++) {
			morelinks[i][0] = r.nextInt(auths)+n+hubs;
			morelinks[i][1] = r.nextInt(n);
		}
		System.out.println((n+hubs+auths)+"");
		for(int i = 0; i < m; i++) {
			System.out.println(links[i][0]+" "+links[i][1]);
		}
		for(int i = 0; i < linkhubs+linkauths; i++) {
			System.out.println(morelinks[i][0]+" "+morelinks[i][1]);
		}
	}
}