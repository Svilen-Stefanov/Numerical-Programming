package ode;

import java.util.Arrays;

/**
 * Das Einschrittverfahren "Expliziter Euler"
 * 
 * @author braeckle
 *
 * Solution authors:
 * @author Svilen Stefanov (svilen.stefanov@tum.de)
 * @author Nikola Dinev (nikdd95@gmail.com)
 * @author Lena Ouyanag (lena.s.ouyang@gmail.com)
 * 
 */

public class ExpliziterEuler implements Einschrittverfahren {

	public double[] nextStep(double[] y_k, double t, double delta_t, ODE ode) {
		double [] dif = ode.auswerten(t, y_k);
		dif = Arrays.stream(dif).map(i -> i * delta_t).toArray();
		double [] res = new double [dif.length]; 
		
		for (int i = 0; i < dif.length; i++) {
			res[i] = y_k[i] + dif[i];
		}
		
		return res;
	}

}
