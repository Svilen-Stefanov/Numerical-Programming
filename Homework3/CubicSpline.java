import java.util.Arrays;

/**
 * Die Klasse CubicSpline bietet eine Implementierung der kubischen Splines. Sie
 * dient uns zur effizienten Interpolation von aequidistanten Stuetzpunkten.
 *
 * @author braeckle
 *
 * Solution authors:
 * @author Svilen Stefanov (svilen.stefanov@tum.de)
 * @author Nikola Dinev (nikdd95@gmail.com)
 * @author Lena Ouyanag (lena.s.ouyang@gmail.com)
 *
 */
public class CubicSpline implements InterpolationMethod {

	/**
	 * linke und rechte Intervallgrenze x[0] bzw. x[n]
	 */
	double a, b;

	/**
	 * Anzahl an Intervallen
	 */
	int n;

	/**
	 * Intervallbreite
	 */
	double h;

	/**
	 * Stuetzwerte an den aequidistanten Stuetzstellen
	 */
	double[] y;

	/**
	 * zu berechnende Ableitunge an den Stuetzstellen
	 */
	double yprime[];

	/**
	 * {@inheritDoc} Zusaetzlich werden die Ableitungen der stueckweisen
	 * Polynome an den Stuetzstellen berechnet. Als Randbedingungen setzten wir
	 * die Ableitungen an den Stellen x[0] und x[n] = 0.
	 */
	@Override
	public void init(double a, double b, int n, double[] y) {
		this.a = a;
		this.b = b;
		this.n = n;
		h = ((double) b - a) / (n);

		this.y = Arrays.copyOf(y, n + 1);

		/* Randbedingungen setzten */
		yprime = new double[n + 1];
		yprime[0] = 0;
		yprime[n] = 0;

		/* Ableitungen berechnen. Nur noetig, wenn n > 1 */
		if (n > 1) {
			computeDerivatives();
		}
	}

	/**
	 * getDerivatives gibt die Ableitungen yprime zurueck
	 */
	public double[] getDerivatives() {
		return yprime;
	}

	/**
	 * Setzt die Ableitungen an den Raendern x[0] und x[n] neu auf yprime0 bzw.
	 * yprimen. Anschliessend werden alle Ableitungen aktualisiert.
	 */
	public void setBoundaryConditions(double yprime0, double yprimen) {
		yprime[0] = yprime0;
		yprime[n] = yprimen;
		if (n > 1) {
			computeDerivatives();
		}
	}

	/**
	 * Berechnet die Ableitungen der stueckweisen kubischen Polynome an den
	 * einzelnen Stuetzstellen. Dazu wird ein lineares System Ax=c mit einer
	 * Tridiagonalen Matrix A und der rechten Seite c aufgebaut und geloest.
	 * Anschliessend sind die berechneten Ableitungen y1' bis yn-1' in der
	 * Membervariable yprime gespeichert.
	 *
	 * Zum Zeitpunkt des Aufrufs stehen die Randbedingungen in yprime[0] und
	 * yprime[n]. Speziell bei den "kleinen" Faellen mit Intervallzahlen n = 2
	 * oder 3 muss auf die Struktur des Gleichungssystems geachtet werden. Der
	 * Fall n = 1 wird hier nicht beachtet, da dann keine weiteren Ableitungen
	 * berechnet werden muessen.
	 */
	public void computeDerivatives() {
		double[] a = new double[n - 2];
		for (int i = 0; i < n - 2; i++) {
			a[i] = 1;
		}
		double[] diag = new double[n - 1];
		for (int i = 0; i < n - 1; i++) {
			diag[i] = 4;
		}
		TridiagonalMatrix M = new TridiagonalMatrix(a, diag, a);
		double[] b = new double[n - 1];

		/* extra cases */
		b[0] = (y[2] - y[0] - h / 3 * yprime[0]) * 3 / h;
		b[n - 2] = (y[n] - y[n - 2] - h / 3 * yprime[n]) * 3 / h;

		for (int i = 1; i < n - 2; i++) {
			b[i] = (y[2 + i] - y[i]) * 3 / h;
		}
		double[] c = M.solveLinearSystem(b);

		// verschiebe ergebnisse nach links da wir y'1 ... zurueckkriegen
		for (int i = 0; i < c.length; i++) {
			yprime[i + 1] = c[i];
		}
	}

	/**
	 * {@inheritDoc} Liegt z ausserhalb der Stuetzgrenzen, werden die
	 * aeussersten Werte y[0] bzw. y[n] zurueckgegeben. Liegt z zwischen den
	 * Stuetzstellen x_i und x_i+1, wird z in das Intervall [0,1] transformiert
	 * und das entsprechende kubische Hermite-Polynom ausgewertet.
	 */
	@Override
	public double evaluate(double z) {
		double result = 0;
		if (a >= z) {
			result = y[0];
		} else if (b <= z) {
			result = y[y.length - 1];
		} else {
			int i = 0;
			double x_i = a, x_j;
			for (i = 0; i < n; i++) {
				if (x_i < z) {
					x_i += h;
				} else
					break;
			}

			x_j = x_i - h;
			double h = x_i - x_j;
			double t = (z - x_j) / h;
			double H0, H1, H2, H3;
			H0 = 1 - 3 * Math.pow(t, 2) + 2 * Math.pow(t, 3);
			H1 = 3 * Math.pow(t, 2) - 2 * Math.pow(t, 3);
			H2 = t - 2 * Math.pow(t, 2) + Math.pow(t, 3);
			H3 = -Math.pow(t, 2) + Math.pow(t, 3);

			computeDerivatives();

			result = y[i - 1] * H0 + y[i] * H1 + h * yprime[i - 1] * H2 + h
					* yprime[i] * H3;
		}

		return result;
	}
}
