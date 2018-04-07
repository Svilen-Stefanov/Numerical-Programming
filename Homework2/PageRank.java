import java.util.Arrays;
import java.util.Comparator;

public class PageRank {

	/**
	 * Solution authors:
	 * @author Svilen Stefanov (svilen.stefanov@tum.de)
	 * @author Nikola Dinev (nikdd95@gmail.com)
	 * @author Lena Ouyanag (lena.s.ouyang@gmail.com)
	 *
	 * Diese Methode erstellt die Matrix A~ fuer das PageRank-Verfahren
	 * PARAMETER: 
	 * L: die Linkmatrix (s. Aufgabenblatt) 
	 * rho: Wahrscheinlichkeit, anstatt einem Link zu folgen,
	 *      zufaellig irgendeine Seite zu besuchen
	 */
	public static double[][] buildProbabilityMatrix(int[][] L, double rho) {
		//how many pages each page is linked to
		int n_arr [] = new int [L[0].length];
		
		//check if there is a link between a page and 
		for (int j = 0; j < L[0].length; j++) {
			for (int i = 0; i < L.length; i++) {
				if(L[i][j] != 0)
					n_arr[j]++; 
			}
		}
		
		double res [][] = new double [L.length][L[0].length];
		
		for (int i = 0; i < L.length; i++) {
			for (int j = 0; j < L[0].length; j++) {
				res[i][j] = (1-rho) * (L[i][j]*1.0/(n_arr[j])) + rho/L.length;
			}
		}
		
		return res;
	}

	/**
	 * Diese Methode berechnet die PageRanks der einzelnen Seiten,
	 * also das Gleichgewicht der Aufenthaltswahrscheinlichkeiten.
	 * (Entspricht dem p-Strich aus der Angabe)
	 * Die Ausgabe muss dazu noch normiert sein.
	 * PARAMETER:
	 * L: die Linkmatrix (s. Aufgabenblatt) 
	 * rho: Wahrscheinlichkeit, zufaellig irgendeine Seite zu besuchen
	 * ,anstatt einem Link zu folgen.
	 *      
	 */
	public static double[] rank(int[][] L, double rho) {
		double [][] PM = buildProbabilityMatrix(L, rho);
		double res [] = new double [L.length];
		
		for (int i = 0;i<PM.length;i++){
			PM[i][i]-=1;
		}
		
		res = Gauss.solveSing(PM);
		
		//normieren
		double tmp = 0;
		for (int i = 0;i<res.length;i++){
			tmp+=Math.pow(res[i], 2);
		}
		tmp = Math.sqrt(tmp);
		
		for (int i=0;i<res.length;i++){
			res[i] /= tmp;
		}
		
		return res;
	}

	/**
	 * Diese Methode erstellt eine Rangliste der uebergebenen URLs nach
	 * absteigendem PageRank. 
 	 * PARAMETER:
 	 * urls: Die URLs der betrachteten Seiten
 	 * L: die Linkmatrix (s. Aufgabenblatt) 
 	 * rho: Wahrscheinlichkeit, anstatt einem Link zu folgen,
 	 *      zufaellig irgendeine Seite zu besuchen
	 */ 
	public static String[] getSortedURLs(String[] urls, int[][] L, double rho) {
		int n = L.length;

		double[] p = rank(L, rho);

		RankPair[] sortedPairs = new RankPair[n];
		for (int i = 0; i < n; i++) {
			sortedPairs[i] = new RankPair(urls[i], p[i]);
		}

		Arrays.sort(sortedPairs, new Comparator<RankPair>() {

			@Override
			public int compare(RankPair o1, RankPair o2) {
				return -Double.compare(o1.pr, o2.pr);
			}
		});

		String[] sortedUrls = new String[n];
		for (int i = 0; i < n; i++) {
			sortedUrls[i] = sortedPairs[i].url;
		}

		return sortedUrls;
	}

	/**
	 * Ein RankPair besteht aus einer URL und dem zugehoerigen Rang, und dient
	 * als Hilfsklasse zum Sortieren der Urls
	 */
	private static class RankPair {
		public String url;
		public double pr;

		public RankPair(String u, double p) {
			url = u;
			pr = p;
		}
	}
}
