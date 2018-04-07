import java.util.Arrays;

public class Gauss {

	/**
	 * Solution authors:
	 * @author Svilen Stefanov (svilen.stefanov@tum.de)
	 * @author Nikola Dinev (nikdd95@gmail.com)
	 * @author Lena Ouyanag (lena.s.ouyang@gmail.com)
	 *
	 * Diese Methode soll die Loesung x des LGS R*x=b durch
	 * Rueckwaertssubstitution ermitteln.
	 * PARAMETER: 
	 * R: Eine obere Dreiecksmatrix der Groesse n x n 
	 * b: Ein Vektor der Laenge n
	 */
	public static double[] backSubst(double[][] R, double[] b) {
		int n = b.length;
		double[] x = new double[n];
		for (int i = n-1; i >= 0; i--)
		{
			x[i] = b[i];
			for (int j = i+1; j < n; j++) x[i] -= R[i][j]*x[j];
			x[i] /= R[i][i];
		}
		return x;
	}

	/**
	 * Diese Methode soll die Loesung x des LGS A*x=b durch Gauss-Elimination mit
	 * Spaltenpivotisierung ermitteln. A und b sollen dabei nicht veraendert werden. 
	 * PARAMETER: A:
	 * Eine regulaere Matrix der Groesse n x n 
	 * b: Ein Vektor der Laenge n
	 */
	public static double[] solve(double[][] A, double[] b) {
		int n = b.length;
		double[][] B = new double[n][n];
		double[] a = Arrays.copyOf(b, n);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				B[i][j] = A[i][j];
		for (int i = 0; i < n; i++)
		{
			double max = Math.abs(B[i][i]);
			int maxIndex = i;
			for (int j = i+1; j < n; j++)
				if(max < Math.abs(B[j][i])){ 
					maxIndex = j; 
					max = Math.abs(B[j][i]);
				} 

			double tmp = a[i];
			a[i] = a[maxIndex];
			a[maxIndex] = tmp;

			double[] temp = Arrays.copyOf(B[i], n);
			B[i] = B[maxIndex];
			B[maxIndex] = temp;

			for (int j = i+1; j < n; j++)
			{
				double fac = B[j][i]/B[i][i];
				for (int k = i; k < n; k++)
					B[j][k] -= fac * B[i][k];
				a[j] -= fac*a[i];
			}
		}
		return backSubst(B, a);
	}

	/**
	 * Diese Methode soll eine Loesung p!=0 des LGS A*p=0 ermitteln. A ist dabei
	 * eine nicht invertierbare Matrix. A soll dabei nicht veraendert werden.
	 * 
	 * Gehen Sie dazu folgendermassen vor (vgl.Aufgabenblatt): 
	 * -Fuehren Sie zunaechst den Gauss-Algorithmus mit Spaltenpivotisierung 
	 *  solange durch, bis in einem Schritt alle moeglichen Pivotelemente
	 *  numerisch gleich 0 sind (d.h. <1E-10) 
	 * -Betrachten Sie die bis jetzt entstandene obere Dreiecksmatrix T und
	 *  loesen Sie Tx = -v durch Rueckwaertssubstitution 
	 * -Geben Sie den Vektor (x,1,0,...,0) zurueck
	 * 
	 * Sollte A doch intvertierbar sein, kann immer ein Pivot-Element gefunden werden(>=1E-10).
	 * In diesem Fall soll der 0-Vektor zurueckgegeben werden. 
	 * PARAMETER: 
	 * A: Eine singulaere Matrix der Groesse n x n 
	 */
	public static double[] solveSing(double[][] A) {
		int n = A.length;
		double[][] B = new double[n][n];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				B[i][j] = A[i][j];
		
		int i = 0;
		finished:
		for (i = 0; i < n; i++)
		{
			for (int j = i; j < n; j++) {
				if(B[j][i] != 0) break;
				if(j == n-1) break finished;
			}
			
			double max = Math.abs(B[i][i]);
			int maxIndex = i;
			for (int j = i+1; j < n; j++)
				if(max < Math.abs(B[j][i])){ 
					maxIndex = j; 
					max = Math.abs(B[j][i]);
				} 

			double[] temp = Arrays.copyOf(B[i], n);
			B[i] = B[maxIndex];
			B[maxIndex] = temp;

			for (int j = i+1; j < n; j++)
			{
				double fac = B[j][i]/B[i][i];
				for (int k = i; k < n; k++)
					B[j][k] -= fac * B[i][k];
			}
		}
		
		if(i == n) return new double[n];
		
		double[][] T = new double[i][i];
		double v [] = new double [i];
		double x [] = new double[i];
		double res [] = new double[n];
		
		for (int j = 0; j < v.length; j++) {
			v[j] = -B[j][i];
		}
		
		for (int j = 0; j < T.length; j++) {
			T[j] = Arrays.copyOf(B[j], i);
		}
		
		x = backSubst(T, v);
		System.arraycopy(x, 0, res, 0, i);
		res[i] = 1;
		
		return res;
	}

	/**
	 * Diese Methode berechnet das Matrix-Vektor-Produkt A*x mit A einer nxm
	 * Matrix und x einem Vektor der Laenge m. Sie eignet sich zum Testen der
	 * Gauss-Loesung
	 */
	public static double[] matrixVectorMult(double[][] A, double[] x) {
		int n = A.length;
		int m = x.length;

		double[] y = new double[n];

		for (int i = 0; i < n; i++) {
			y[i] = 0;
			for (int j = 0; j < m; j++) {
				y[i] += A[i][j] * x[j];
			}
		}

		return y;
	}
}
