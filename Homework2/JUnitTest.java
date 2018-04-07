import static org.junit.Assert.*;

import org.junit.Test;

/*
* Solution authors:
* @author Svilen Stefanov (svilen.stefanov@tum.de)
* @author Nikola Dinev (nikdd95@gmail.com)
* @author Lena Ouyanag (lena.s.ouyang@gmail.com)
*/
public class JUnitTest {

	@Test
	public void backSubTest() {
		double[][] A = {{4,9,3}, {0,7,5}, {0,0,2}};
		double[] b = {2,3,7};
		double x [];
		double res [] = {2.5357, -2.0714, 3.5};
		x = Gauss.backSubst(A, b);
		assertArrayEquals(x, res, 0.001);	
	}
	
	@Test
	public void solveTest() {
		double[][] A = {{3,1,2}, {1,2,3}, {2,2,1}};
		double[] b = {11,14,9};
		double x [];
		double res [] = {1.0, 2.0, 3.0};
		x = Gauss.solve(A, b);
		assertArrayEquals(x, res, 0.001);	
		
		double[][] B = {{4,9,3}, {0,7,5}, {0,0,2}};
		double[] c = {2,3,7};
		res[0] = 2.5357;
		res[1] = -2.0714;
		res[2] = 3.5;
		x = Gauss.solve(B, c);
		assertArrayEquals(x, res, 0.001);	
		
		double[][] C = {{1,5,8}, {8,5,0}, {8,6,4}};
		double[] d = {4,4,8};
		res[0] = 1.6842;
		res[1] = -1.8947;
		res[2] = 1.4736;
		x = Gauss.solve(C, d);
		assertArrayEquals(x, res, 0.001);	
	}
	
	@Test
	public void solveSingTest() {
		double[][] A = {{1,1}, {1,1}};
		double x [];
		double res [] = {-1, 1};
		x = Gauss.solveSing(A);
		assertArrayEquals(x, res, 0.001);	
		
		double[][] B = {{1,0}, {1,0}};
		res [0] = 0;
		x = Gauss.solveSing(B);
		assertArrayEquals(x, res, 0.001);	
		
		double[][] C = {{1,1}, {1,0}};
		res [0] = 0;
		res [1] = 0;
		x = Gauss.solveSing(C);
		assertArrayEquals(x, res, 0.001);	
	}
	
	@Test
	public void buildProbabilityMatrixTest() {
		int[][] A = {{1,0,1}, {0,1,1}, {0,1,0}};
		double[][] B = {{0.8666,0.0666,0.4666}, {0.0666,0.4666,0.4666}, {0.0666,0.4666,0.0666}};
		double[][] C = PageRank.buildProbabilityMatrix(A, 0.2);
		for (int i = 0; i < C.length; i++) {
			assertArrayEquals(B[i], C[i], 0.001);	
		}	
	}
}
