public class MathOp {

	public static double[][] matrixMultiply (double[][] x, double[][] y) {
		double[][] result = new double[x.length][y[0].length];

		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y[0].length; j++) {
				result[i][j] = 0;
				for (int k = 0; k < y.length; k++) {
					result[i][j] += x[i][k] * y[k][j];
				}
			}
		}

		return result;
	}

	public static double sigmoidFunction (double x, boolean deriv) {
		if (deriv) return x * (1-x);
		return 1/(1+Math.exp(-x)); 
	}

	public static void sigmoidFunction (double[] input, boolean deriv) {
		for (int i = 0; i < input.length; i++) {
			if (deriv) input[i] = input[i] * (1 - input[i]);
			else input[i] = 1/(1+Math.exp(-input[i]));
		}
	}

	public static void sigmoidFunction (double[][] input, boolean deriv) {
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				if (deriv) input[i][j] = input[i][j] * (1 - input[i][j]);
				//else input[i][j] = (1/(1 + Math.exp(-input[i][j])));
				//else input[i][j] = Math.exp(-input[i][j]);
				else input[i][j] = 100 * (Math.exp(-1/(1+Math.exp(-input[i][j]))));
			}
		}
	}

}
