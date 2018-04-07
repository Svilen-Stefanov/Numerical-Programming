import java.util.Arrays;
import java.util.Random;

public class Test_Gleitpunktzahl {
	/**
	 * @author Svilen Stefanov (svilen.stefanov@tum.de)
	 * @author Nikola Dinev (nikdd95@gmail.com)
	 * @author Lena Ouyanag (lena.s.ouyang@gmail.com)
	 * Testklasse, ob alles in der Klasse Gleitpunktzahl funktioniert!
	 */

	public static void main(String[] argv) {
		test_Gleitpunktzahl();
	}

	public static void test_Gleitpunktzahl() {

		/**********************************/
		/* Test der Klasse Gleitpunktzahl */
		/**********************************/

		System.out.println("-----------------------------------------");
		System.out.println("Test der Klasse Gleitpunktzahl");

		/*
		 * Verglichen werden die BitFelder fuer Mantisse und Exponent und das
		 * Vorzeichen
		 */
		Gleitpunktzahl.setSizeMantisse(8);
		Gleitpunktzahl.setSizeExponent(4);

		Gleitpunktzahl x;
		Gleitpunktzahl y;
		Gleitpunktzahl gleitref = new Gleitpunktzahl();
		Gleitpunktzahl gleiterg;

		/* Test von setDouble */
		System.out.println("Test von setDouble");
		try {
			// Test: setDouble
			x = new Gleitpunktzahl(0.5);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(0.5);

			// Test, ob Ergebnis korrekt
			if (x.compareAbsTo(gleitref) != 0
					|| x.vorzeichen != gleitref.vorzeichen) {
				printErg("" + x.toDouble(), "" + gleitref.toDouble());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			// Positive Zahlen
			for (int i = 0; i < 100; i++) {
				double test = new Random().nextDouble() * 100;
				x = new Gleitpunktzahl(test);
				gleitref = new Gleitpunktzahl(test);

				if (x.compareAbsTo(gleitref) != 0
						|| x.vorzeichen != gleitref.vorzeichen) {
					printErg("" + x.toDouble(), "" + gleitref.toDouble());
					System.out.println(" Test case " + (i + 1) + ": Falsch\n");
					System.out.println("Mit test = " + test);
				}
			}
			System.out.println("Positive Zahlen -> done\n");

			// Negative Zahlen
			for (int i = 0; i < 100; i++) {
				double test = new Random().nextDouble() * (-100);
				x = new Gleitpunktzahl(test);
				gleitref = new Gleitpunktzahl(test);

				if (x.compareAbsTo(gleitref) != 0
						|| x.vorzeichen != gleitref.vorzeichen) {
					printErg("" + x.toDouble(), "" + gleitref.toDouble());
					System.out.println(" Test case " + (i + 1) + ": Falsch\n");
					System.out.println("Mit test = " + test);
				}
			}
			System.out.println("Negative Zahlen -> done\n");


			System.out.println("\n\nEIGENE TESTS einfuegen!!!!!!!\n\n");

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Addition */
		System.out.println("Test der Addition mit Gleitpunktzahl");
		try {
			// Test: Addition
			System.out.println("Test: Addition  x + y");
			Gleitpunktzahl.setSizeMantisse(16);
			Gleitpunktzahl.setSizeExponent(8);

			x = new Gleitpunktzahl(3.25);
			y = new Gleitpunktzahl(0.5);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(3.25 + 0.5);

			// Berechnung
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			// Add mit positiven Zahlen
			for (int i = 0; i < 100; i++) {
				double a1 = new Random().nextDouble() * 100;
				double a2 = new Random().nextDouble() * 100;
				x = new Gleitpunktzahl(a1);
				y = new Gleitpunktzahl(a2);
				gleitref = new Gleitpunktzahl(a1 + a2);
				gleiterg = x.add(y);

				System.out.println("---TEST CASE " + (i + 1) + "---\n");
				System.out.println("x = " + x.toDouble() + "\ny = "
						+ y.toDouble() + "\n" + "Result: "
						+ gleitref.toDouble() + "\nOur result: "
						+ gleiterg.toDouble());
				System.out.println();

				// if (gleiterg.compareAbsTo(gleitref) != 0
				// || gleiterg.vorzeichen != gleitref.vorzeichen) {
				// printErg("" + gleiterg.toDouble(), "" + gleitref.toDouble());
				// System.out.println(" Test case " + (i + 1) + ": Falsch\n");
				// System.out.println("Mit a1 = " + a1 + " und " + " a2 = " +
				// a2);
				// }
			}

			System.out.println("\n");

			// Add mit positiven und negativen Zahlen
			for (int i = 0; i < 100; i++) {
				double a1 = new Random().nextDouble() * 100;
				double a2 = new Random().nextDouble() * (-100);
				x = new Gleitpunktzahl(a1);
				y = new Gleitpunktzahl(a2);
				gleitref = new Gleitpunktzahl(a1 + a2);
				gleiterg = x.add(y);

				System.out.println("---TEST CASE " + (i + 1) + "---\n");
				System.out.println("x = " + x.toDouble() + "\ny = "
						+ y.toDouble() + "\n" + "Result: "
						+ gleitref.toDouble() + "\nOur result: "
						+ gleiterg.toDouble());
				System.out.println();

				// if (gleiterg.compareAbsTo(gleitref) != 0
				// || gleiterg.vorzeichen != gleitref.vorzeichen) {
				// printErg("" + gleiterg.toDouble(), "" + gleitref.toDouble());
				// System.out.println(" Test case " + (i + 1) + ": Falsch\n");
				// System.out.println("Mit a1 = " + a1 + " und " + " a2 = " +
				// a2);
				// }
			}

			// Add mit positiven und negativen Zahlen
			for (int i = 0; i < 100; i++) {
				double a1 = new Random().nextDouble() * (-100);
				double a2 = new Random().nextDouble() * (100);
				x = new Gleitpunktzahl(a1);
				y = new Gleitpunktzahl(a2);
				gleitref = new Gleitpunktzahl(a1 + a2);
				gleiterg = x.add(y);

				System.out.println("---TEST CASE " + (i + 1) + "---\n");
				System.out.println("x = " + x.toDouble() + "\ny = "
						+ y.toDouble() + "\n" + "Result: "
						+ gleitref.toDouble() + "\nOur result: "
						+ gleiterg.toDouble());
				System.out.println();
			}

			// Add mit negativen Zahlen
			for (int i = 0; i < 100; i++) {
				double a1 = new Random().nextDouble() * (-100);
				double a2 = new Random().nextDouble() * (-100);
				x = new Gleitpunktzahl(a1);
				y = new Gleitpunktzahl(a2);
				gleitref = new Gleitpunktzahl(a1 + a2);
				gleiterg = x.add(y);

				System.out.println("---TEST CASE " + (i + 1) + "---\n");
				System.out.println("x = " + x.toDouble() + "\ny = "
						+ y.toDouble() + "\n" + "Result: "
						+ gleitref.toDouble() + "\nOur result: "
						+ gleiterg.toDouble());
				System.out.println();
			}

			System.out.println("Done\n");


			System.out.println("\n\nEIGENE TESTS einfuegen!!!!!!!\n\n");

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Subtraktion */
		try {
			System.out.println("Test der Subtraktion mit Gleitpunktzahl");

			// Test: Addition
			System.out.println("Test: Subtraktion  x - y");
			x = new Gleitpunktzahl(3.25);
			y = new Gleitpunktzahl(2.75);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((3.25 - 2.75));

			// Berechnung
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			// Sub mit positiven Zahlen
			for (int i = 0; i < 100; i++) {
				double a1 = new Random().nextDouble() * 100;
				double a2 = new Random().nextDouble() * 100;
				x = new Gleitpunktzahl(a1);
				y = new Gleitpunktzahl(a2);
				gleitref = new Gleitpunktzahl(a1 - a2);
				gleiterg = x.sub(y);

				System.out.println("---TEST CASE " + (i + 1) + "---\n");
				System.out.println("x = " + x.toDouble() + "\ny = "
						+ y.toDouble() + "\n" + "Result: "
						+ gleitref.toDouble() + "\nOur result: "
						+ gleiterg.toDouble());
				System.out.println();

				// if (gleiterg.compareAbsTo(gleitref) != 0
				// || gleiterg.vorzeichen != gleitref.vorzeichen) {
				// printErg("" + gleiterg.toDouble(), "" + gleitref.toDouble());
				// System.out.println(" Test case " + (i + 1) + ": Falsch\n");
				// System.out.println("Mit a1 = " + a1 + " und " + " a2 = " +
				// a2);
				// }
			}

			System.out.println("\n");

			// Sub mit positiven und negativen Zahlen
			for (int i = 0; i < 100; i++) {
				double a1 = new Random().nextDouble() * 100;
				double a2 = new Random().nextDouble() * (-100);
				x = new Gleitpunktzahl(a1);
				y = new Gleitpunktzahl(a2);
				gleitref = new Gleitpunktzahl(a1 - a2);
				gleiterg = x.sub(y);

				System.out.println("---TEST CASE " + (i + 1) + "---\n");
				System.out.println("x = " + x.toDouble() + "\ny = "
						+ y.toDouble() + "\n" + "Result: "
						+ gleitref.toDouble() + "\nOur result: "
						+ gleiterg.toDouble());
				System.out.println();

				// if (gleiterg.compareAbsTo(gleitref) != 0
				// || gleiterg.vorzeichen != gleitref.vorzeichen) {
				// printErg("" + gleiterg.toDouble(), "" + gleitref.toDouble());
				// System.out.println(" Test case " + (i + 1) + ": Falsch\n");
				// System.out.println("Mit a1 = " + a1 + " und " + " a2 = " +
				// a2);
				// }
			}

			// Sub mit positiven und negativen Zahlen
			for (int i = 0; i < 100; i++) {
				double a1 = new Random().nextDouble() * (-100);
				double a2 = new Random().nextDouble() * (100);
				x = new Gleitpunktzahl(a1);
				y = new Gleitpunktzahl(a2);
				gleitref = new Gleitpunktzahl(a1 - a2);
				gleiterg = x.sub(y);

				System.out.println("---TEST CASE " + (i + 1) + "---\n");
				System.out.println("x = " + x.toDouble() + "\ny = "
						+ y.toDouble() + "\n" + "Result: "
						+ gleitref.toDouble() + "\nOur result: "
						+ gleiterg.toDouble());
				System.out.println();
			}

			// Sub mit negativen Zahlen
			for (int i = 0; i < 100; i++) {
				double a1 = new Random().nextDouble() * (-100);
				double a2 = new Random().nextDouble() * (-100);
				x = new Gleitpunktzahl(a1);
				y = new Gleitpunktzahl(a2);
				gleitref = new Gleitpunktzahl(a1 - a2);
				gleiterg = x.sub(y);

				System.out.println("---TEST CASE " + (i + 1) + "---\n");
				System.out.println("x = " + x.toDouble() + "\ny = "
						+ y.toDouble() + "\n" + "Result: "
						+ gleitref.toDouble() + "\nOur result: "
						+ gleiterg.toDouble());
				System.out.println();
			}

			System.out.println("Done\n");


			System.out.println("\n\nEIGENE TESTS einfuegen!!!!!!!\n\n");

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Sonderfaelle */
		System.out.println("Test der Sonderfaelle mit Gleitpunktzahl");

		try {
			// Test: Sonderfaelle
			// 0 - inf
			System.out.println("Test: Sonderfaelle");
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl(1.0 / 0.0);

			// Referenzwerte setzen
			gleitref.setInfinite(true);

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			// Add
			// 0 + (-00)
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl();
			y.setInfinite(true);
			gleitref.setInfinite(true);
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 0 + 00
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl();
			y.setInfinite(false);
			gleitref.setInfinite(false);
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 50 + 00
			x = new Gleitpunktzahl(50.0);
			y = new Gleitpunktzahl();
			y.setInfinite(false);
			gleitref.setInfinite(false);
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 00 + (-00)
			x = new Gleitpunktzahl();
			x.setInfinite(false);
			y = new Gleitpunktzahl();
			y.setInfinite(true);
			gleitref.setNaN();
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// -00 + 00
			x = new Gleitpunktzahl();
			x.setInfinite(true);
			y = new Gleitpunktzahl();
			y.setInfinite(false);
			gleitref.setNaN();
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 00 + 00
			x = new Gleitpunktzahl();
			x.setInfinite(false);
			y = new Gleitpunktzahl();
			y.setInfinite(false);
			gleitref.setInfinite(false);
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// -00 + -00
			x = new Gleitpunktzahl();
			x.setInfinite(true);
			y = new Gleitpunktzahl();
			y.setInfinite(true);
			gleitref.setInfinite(true);
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// -00 + NaN
			x = new Gleitpunktzahl();
			x.setInfinite(true);
			y = new Gleitpunktzahl();
			y.setNaN();
			gleitref.setNaN();
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 00 + NaN
			x = new Gleitpunktzahl();
			x.setInfinite(false);
			y = new Gleitpunktzahl();
			y.setNaN();
			gleitref.setNaN();
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 0 + NaN
			x = new Gleitpunktzahl(0);
			y = new Gleitpunktzahl();
			y.setNaN();
			gleitref.setNaN();
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 100 + NaN
			x = new Gleitpunktzahl(100);
			y = new Gleitpunktzahl();
			y.setNaN();
			gleitref.setNaN();
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 100 + Null
			x = new Gleitpunktzahl(100);
			y = new Gleitpunktzahl();
			y.setNull();
			gleitref.setDouble(100);
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// NaN + Null
			x = new Gleitpunktzahl();
			x.setNaN();
			y = new Gleitpunktzahl();
			y.setNull();
			gleitref.setNaN();
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// Null + Null
			x = new Gleitpunktzahl();
			x.setNull();
			y = new Gleitpunktzahl();
			y.setNull();
			gleitref.setNull();
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 00 + Null
			x = new Gleitpunktzahl();
			x.setInfinite(false);
			y = new Gleitpunktzahl();
			y.setNull();
			gleitref.setInfinite(false);
			gleiterg = x.add(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// Sub
			// 0 - (-00)
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl();
			y.setInfinite(true);
			gleitref.setInfinite(false);
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 0 - 00
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl();
			y.setInfinite(false);
			gleitref.setInfinite(true);
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 50 - 00
			x = new Gleitpunktzahl(50.0);
			y = new Gleitpunktzahl();
			y.setInfinite(false);
			gleitref.setInfinite(true);
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 00 - (-00)
			x = new Gleitpunktzahl();
			x.setInfinite(false);
			y = new Gleitpunktzahl();
			y.setInfinite(true);
			gleitref.setInfinite(false);;
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// -00 - 00
			x = new Gleitpunktzahl();
			x.setInfinite(true);
			y = new Gleitpunktzahl();
			y.setInfinite(false);
			gleitref.setInfinite(true);;
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 00 - 00
			x = new Gleitpunktzahl();
			x.setInfinite(false);
			y = new Gleitpunktzahl();
			y.setInfinite(false);
			gleitref.setNaN();
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// -00 - -00
			x = new Gleitpunktzahl();
			x.setInfinite(true);
			y = new Gleitpunktzahl();
			y.setInfinite(true);
			gleitref.setNaN();
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// -00 - NaN
			x = new Gleitpunktzahl();
			x.setInfinite(true);
			y = new Gleitpunktzahl();
			y.setNaN();
			gleitref.setNaN();
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 00 - NaN
			x = new Gleitpunktzahl();
			x.setInfinite(false);
			y = new Gleitpunktzahl();
			y.setNaN();
			gleitref.setNaN();
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 0 - NaN
			x = new Gleitpunktzahl(0);
			y = new Gleitpunktzahl();
			y.setNaN();
			gleitref.setNaN();
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 100 - NaN
			x = new Gleitpunktzahl(100);
			y = new Gleitpunktzahl();
			y.setNaN();
			gleitref.setNaN();
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 100 - Null
			x = new Gleitpunktzahl(100);
			y = new Gleitpunktzahl();
			y.setNull();
			gleitref.setDouble(100);
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// NaN - Null
			x = new Gleitpunktzahl();
			x.setNaN();
			y = new Gleitpunktzahl();
			y.setNull();
			gleitref.setNaN();
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// Null - Null
			x = new Gleitpunktzahl();
			x.setNull();
			y = new Gleitpunktzahl();
			y.setNull();
			gleitref.setNull();
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();

			// 00 - Null
			x = new Gleitpunktzahl();
			x.setInfinite(false);
			y = new Gleitpunktzahl();
			y.setNull();
			gleitref.setInfinite(false);
			gleiterg = x.sub(y);

			System.out.println("x = " + x.toDouble() + "\ny = " + y.toDouble()
					+ "\n" + "Result: " + gleitref.toDouble()
					+ "\nOur result: " + gleiterg.toDouble());
			System.out.println();
			
			/*************
			 * Eigene Tests einfuegen
			 */

			System.out.println("\n\nEIGENE TESTS einfuegen!!!!!!!\n\n");

		} catch (Exception e) {
			System.out
					.print("Exception bei der Auswertung des Ergebnis in der Klasse Gleitpunktzahl!!\n");
		}

	}

	private static void printAdd(String x, String y) {
		System.out.println("    Fehler!\n      Es wurde gerechnet:            "
				+ x + " + " + y);
	}

	private static void printSub(String x, String y) {
		System.out.println("    Fehler!\n      Es wurde gerechnet:            "
				+ x + " - " + y + " = \n");
	}

	private static void printErg(String erg, String checkref) {
		System.out.println("      Ihr Ergebnis lautet:           " + erg
				+ "\n      Das Korrekte Ergebnis lautet:  " + checkref + "\n");
	}
}
