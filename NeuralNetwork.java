import java.util.Random;

public class NeuralNetwork {
	
	private double[][] syn0;
	private double[][] syn1;

	public NeuralNetwork () {
		
		Random rand = new Random();
		
		syn0 = new double[106][10];
		syn1 = new double[10][81];

		for (int i = 0; i < syn0.length; i++) {
			for (int j = 0; j < syn0[i].length; j++) {
				syn0[i][j] = rand.nextGaussian();
			}
		}

		for (int i = 0; i < syn1.length; i++) {
			for (int j = 0; j < syn1[i].length; j++) {
				syn1[i][j] = rand.nextGaussian();
			}
		}
	}

	public NeuralNetwork (NeuralNetwork net) {
		
		syn0 = new double[106][10];
		syn1 = new double[10][81];
		
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
		syn0 = new double[106][10];
		syn1 = new double[10][81];
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
				if (rand.nextDouble() < 0.1) {
					double offset = rand.nextGaussian() * 0.5;
					syn0[i][j] += offset;	
				}
			}
		}
		for (int i = 0; i < syn1.length; i++) {
			for (int j = 0; j < syn1[i].length; j++) {
				if (rand.nextDouble() < 0.1) {
					double offset = rand.nextGaussian() * 0.5;
					syn1[i][j] += offset;	
				}
			}
		}
	}

}
