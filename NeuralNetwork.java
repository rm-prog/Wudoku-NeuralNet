import java.util.Random;

public class NeuralNetwork {
	
	private double[][] syn0;
	private double[][] syn1;

	private int[][] coordinatesOfCubes = new int[][] { { 0,1,2,9,10,11,18,19,20 },
      						           { 3,4,5,12,13,14,21,22,23},
							   { 6,7,8,15,16,17,24,25,26},
						           { 27,28,29,36,37,38,45,46,47 },
							   { 30,31,32,39,40,41,48,49,50 },
							   { 33,34,35,42,43,44,51,52,53 },
							   { 54,55,56,63,64,65,72,73,74 },
							   { 57,58,59,66,67,68,75,76,77 },
							   { 60,61,62,69,70,71,78,79,80 }};

	public NeuralNetwork () {
		
		Random rand = new Random();
		
		syn0 = new double[106][27];
		syn1 = new double[27][81];

		for (int i = 0; i < syn0.length; i++) {
			for (int j = 0; j < syn0[i].length; j++) {
				if ((i / 9 == j || i % 9 == j-9) && i < 81 && j < 18) syn0[i][j] = rand.nextGaussian() + 20;
				else if (j >= 18 && arrayContains(coordinatesOfCubes[j-18], i)) syn0[i][j] = rand.nextGaussian() + 20;
				if (i >= 81) syn0[i][j] = rand.nextGaussian();
			}
		}

		for (int i = 0; i < syn1.length; i++) {
			for (int j = 0; j < syn1[i].length; j++) {
				if (((j / 9 == i && i < 9) || (j % 9 == i-9 && i >= 9)) && i < 18) syn1[i][j] = rand.nextGaussian() + 5;
				else if (i >= 18 && arrayContains(coordinatesOfCubes[i-18], j)) syn1[i][j] = rand.nextGaussian() + 5;
			}
		}
	}
	
	public boolean arrayContains(int[] arr, int x) { 
		for (int i : arr) {
			if (x == i) return true;
		}
		return false;
	}

	public NeuralNetwork (NeuralNetwork net) {
		
		syn0 = new double[106][27];
		syn1 = new double[27][81];
		
		for (int i = 0; i < syn0.length; i++) {
			for (int j = 0; j < syn0[i].length; j++) {
				this.syn0[i][j] = net.syn0[i][j];	
			}
		}

		for (int i = 0; i < syn1.length; i++) {
			for (int j = 0; j < syn1[i].length; j++) {
				this.syn1[i][j] = net.syn1[i][j];			
			}
		}
	}

	public NeuralNetwork (NeuralNetwork netA, NeuralNetwork netB) {
		syn0 = new double[106][27];
		syn1 = new double[27][81];
		Random rand = new Random();
		for (int i = 0; i < syn0.length; i++) {
			for (int j = 0; j < syn0[i].length; j++) {
				if (rand.nextDouble() < 0.5) this.syn0[i][j] = netA.syn0[i][j];
				else this.syn0[i][j] = netB.syn0[i][j];	
			}
		}

		for (int i = 0; i < syn1.length; i++) {
			for (int j = 0; j < syn1[i].length; j++) {
				if (rand.nextDouble() < 0.5) this.syn1[i][j] = netA.syn1[i][j];
				else this.syn1[i][j] = netB.syn1[i][j];			
			}
		}
	}

	public double[] predict (double[] input, Field field, Tile tile) {
		double[][] layer0 = { input };
		double[][] layer1 = MathOp.matrixMultiply(layer0, syn0);
		MathOp.sigmoidFunction(layer1, false);
		double[][] layer2 = MathOp.matrixMultiply(layer1, syn1);
		MathOp.sigmoidFunction(layer2, false);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!field.doesTileFitField(tile, j, i)) {
					layer2[0][i*9 + j] = Integer.MIN_VALUE;	
				}
			}
		}
		return layer2[0];	
	}

	public void mutate () {
		Random rand = new Random();
		for (int i = 0; i < syn0.length; i++) {
			for (int j = 0; j < syn0[i].length; j++) {
				if ((((i / 9 == j || i % 9 == j-9) && i < 81 && j < 18) || (j >= 18 && arrayContains(coordinatesOfCubes[j-18], i)) || i >= 81) && rand.nextDouble() < 0.1) {
					double offset = rand.nextGaussian() * 0.5;
					syn0[i][j] += offset;	
				}
			}
		}
		for (int i = 0; i < syn1.length; i++) {
			for (int j = 0; j < syn1[i].length; j++) {
				if (((j / 9 == i && i < 9) || (j % 9 == i-9 && i >= 9 && i < 18) || (i >= 18 && arrayContains(coordinatesOfCubes[i-18], j))) && rand.nextDouble() < 0.1) {
					double offset = rand.nextGaussian() * 0.5;
					syn1[i][j] += offset;	
				}
			}
		}
	}

}
