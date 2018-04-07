package ode;

import java.util.Arrays;

/**
 * Das Einschrittverfahren von Heun
 * 
 * @author braeckle
 * 
 *
 * Solution authors:
 * @author Svilen Stefanov (svilen.stefanov@tum.de)
 * @author Nikola Dinev (nikdd95@gmail.com)
 * @author Lena Ouyanag (lena.s.ouyang@gmail.com)
 */

public class Heun implements Einschrittverfahren {

	@Override
	/**
	 * {@inheritDoc} 
	 * Nutzen Sie dabei geschickt den Expliziten Euler.
	 */
	public double[] nextStep(double[] y_k, double t, double delta_t, ODE ode) {
		double [] dif_t = ode.auswerten(t, y_k);
		ExpliziterEuler euler = new ExpliziterEuler();
		double [] dif_delta_t = ode.auswerten(t+delta_t, euler.nextStep(y_k, t, delta_t, ode));
		
		double [] res = new double [dif_t.length]; 
		for (int i = 0; i < dif_t.length; i++) {
			res[i] = dif_t[i] + dif_delta_t[i];
		}
		
		//multiplication with delta_t/2
		res = Arrays.stream(res).map(i -> i * (delta_t/2)).toArray();
		
		for (int i = 0; i < dif_t.length; i++) {
			res[i] = y_k[i] + res[i];
		}
		
		return res;
	}

}
