import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	
	private Field field;
	private Tile[] tiles;
	private int score;

	public Game () {
		field = new Field();
		tiles = new Tile[] { 
			new Tile(new int[][] { {1} }, 0, 0),
			new Tile(new int[][] { {1, 1} }, 0, 0),
			new Tile(new int[][] { {1}, {1} }, 0, 0),
			new Tile(new int[][] { {1, 0}, {0, 1} }, 0, 0),
			new Tile(new int[][] { {0, 1}, {1, 0} }, 1, 0),
			new Tile(new int[][] { {1, 1, 1} }, 0, 0),
			new Tile(new int[][] { {1}, {1}, {1} }, 0, 0),
			new Tile(new int[][] { {1, 0}, {1, 1} }, 0, 0),
			new Tile(new int[][] { {0, 1}, {1, 1} }, 1, 0),
			new Tile(new int[][] { {1, 1}, {1, 0} }, 0, 0),
			new Tile(new int[][] { {1, 1}, {0, 1} }, 0, 0),
			new Tile(new int[][] { {1, 0, 0}, {0, 1, 0}, {0, 0, 1} }, 0, 0),
			new Tile(new int[][] { {0, 0, 1}, {0, 1, 0}, {1, 0, 0} }, 2, 0),
			new Tile(new int[][] { {1, 0}, {1, 1}, {1, 0} }, 0, 0),
			new Tile(new int[][] { {0, 1}, {1, 1}, {0, 1} }, 1, 0),
			new Tile(new int[][] { {0, 1, 0}, {1, 1, 1} }, 1, 0),
			new Tile(new int[][] { {1, 1, 1}, {0, 1, 0} }, 0, 0),
			new Tile(new int[][] { {1, 1}, {1, 1} }, 0, 0),
			new Tile(new int[][] { {1, 1, 1, 1} }, 0, 0),
			new Tile(new int[][] { {1}, {1}, {1}, {1} }, 0, 0),
			new Tile(new int[][] { {1, 1}, {1, 0}, {1, 0} }, 0, 0),
			new Tile(new int[][] { {1, 1}, {0, 1}, {0, 1} }, 0, 0),
			new Tile(new int[][] { {1, 0, 0}, {1, 1, 1} }, 0, 0),	
			new Tile(new int[][] { {1, 1, 1}, {1, 0, 0} }, 0, 0),	
			new Tile(new int[][] { {1, 0}, {1, 0}, {1, 1} }, 0, 0),
			new Tile(new int[][] { {0, 1}, {0, 1}, {1, 1} }, 1, 0),
			new Tile(new int[][] { {1,0,0,0}, {0,1,0,0}, {0,0,1,0}, {0,0,0,1} }, 0, 0),
			new Tile(new int[][] { {0,0,0,1}, {0,0,1,0}, {0,1,0,0}, {1,0,0,0} }, 3, 0),
			new Tile(new int[][] { {0, 0, 1}, {1, 1, 1} }, 2, 0),
			new Tile(new int[][] { {1, 1, 1}, {0, 0, 1} }, 0, 0),
			new Tile(new int[][] { {1, 1, 0}, {0, 1, 1} }, 0, 0),
			new Tile(new int[][] { {0, 1, 1}, {1, 1, 0} }, 1, 0),
			new Tile(new int[][] { {0, 1}, {1, 1}, {1, 0} }, 1, 0),
			new Tile(new int[][] { {1, 0}, {1, 1}, {0, 1} }, 0, 0),
			new Tile(new int[][] { {1}, {1}, {1}, {1}, {1} }, 0, 0),	
			new Tile(new int[][] { {1, 1, 1, 1, 1} }, 0, 0),
			new Tile(new int[][] { {0, 1, 0}, {1, 1, 1}, {0, 1, 0} }, 1, 0),
			new Tile(new int[][] { {1,0,0,0,0}, {0,1,0,0,0}, {0,0,1,0,0}, 
				{0,0,0,1,0}, {0,0,0,0,1 } }, 0, 0),
			new Tile(new int[][] { {0,0,0,0,1}, {0,0,0,1,0}, {0,0,1,0,0}, 
				{0,1,0,0,0}, {1,0,0,0,0} }, 4, 0),
			new Tile(new int[][] { {1, 0, 0}, {1, 1, 1}, {1, 0, 0} }, 0, 0),
			new Tile(new int[][] { {0, 0, 1}, {1, 1, 1}, {0, 0, 1} }, 2, 0),
			new Tile(new int[][] { {0, 1, 0}, {0, 1, 0}, {1, 1, 1} }, 1, 0),
			new Tile(new int[][] { {1, 1, 1}, {0, 1, 0}, {0, 1, 0} }, 0, 0),
			new Tile(new int[][] { {1, 0, 0}, {1, 0, 0}, {1, 1, 1} }, 0, 0),
			new Tile(new int[][] { {0, 0, 1}, {0, 0, 1}, {1, 1, 1} }, 2, 0),
			new Tile(new int[][] { {1, 1, 1}, {1, 0, 0}, {1, 0, 0} }, 0, 0),
			new Tile(new int[][] { {1, 1, 1}, {0, 0, 1}, {0, 0, 1} }, 0, 0),
			new Tile(new int[][] { {1, 0, 1}, {1, 1, 1} }, 0, 0),
			new Tile(new int[][] { {1, 1, 1}, {1, 0, 1} }, 0, 0),
			new Tile(new int[][] { {1, 1}, {1, 0}, {1, 1} }, 0, 0),
			new Tile(new int[][] { {1, 1}, {0, 1}, {1, 1} }, 0, 0)
		};
		score = 0;		
	}

	public void shuffleTiles() {
		Random rand = new Random();
		int length = tiles.length;
		for (int i = 0; i < length; i++) {
			int randomIndexToSwap = rand.nextInt(length);
			Tile temp = tiles[randomIndexToSwap];
			tiles[randomIndexToSwap] = tiles[i];
			tiles[i] = temp;			
		}
	}

	public int play(String tilePlacementMethod, NeuralNetwork neuralNet) {
		score = 0;
		field = new Field();
		shuffleTiles();		
		Scanner in = new Scanner(System.in);
		ArrayList<Tile> availableTiles = new ArrayList<> (Arrays.asList(Arrays.copyOfRange(tiles, 0, 3)));
		while (true) {
			if (availableTiles.isEmpty()) {
				shuffleTiles();
				availableTiles = new ArrayList<> (Arrays.asList(Arrays.copyOfRange(tiles, 0, 3)));
				continue;
			}
			
			field.updateField();
			//printAvailableTiles(availableTiles);		
			
			boolean oneTileFits = false;
			for (int i = 0; i < availableTiles.size(); i++) {
				if (field.doesTileFitField(availableTiles.get(i))) {
					oneTileFits = true;
					break;
				}
			}
			if (!oneTileFits) {
				//System.out.println("\nGame Over!");
				in.close();
				//System.out.println("Final Score: " + score);	
				break;
			}
			
			//System.out.println("\nScore: " + score);
			if (tilePlacementMethod == "manual") {
				System.out.println("Choose Tile 1, 2 or 3");
				int tileNumber = Integer.valueOf(in.nextLine());
				if (tileNumber > availableTiles.size()) {
					System.out.println("\nOnly " + availableTiles.size() + " tiles available!!!");
					continue;
				} else if (tileNumber < 1) {
			       		System.out.println("\nNumber cannot be smaller than 1!!!");
					continue;
				}
				if (!field.doesTileFitField(availableTiles.get(tileNumber-1))) {
					System.out.println("\nTile does not fit in field!!!");
					continue;
				}

				System.out.println("Insert x coordinate: ");
				int x = Integer.valueOf(in.nextLine());
				System.out.println("Insert y coordinate: ");
				int y = Integer.valueOf(in.nextLine());
				if (!field.doesTileFitField(availableTiles.get(tileNumber-1), x-1, y-1)) {
					System.out.println("\nTile does not fit in this coordinates!!!");
					continue;
				}

				field.putTileOnField(availableTiles.get(tileNumber-1), x-1, y-1);
				score += field.scoredPoints();
				score += availableTiles.get(tileNumber-1).numberOfCubes();	
				availableTiles.remove(tileNumber-1);

			} else if (tilePlacementMethod == "random") {
				Random rand = new Random();
				int tileNumber = 0;
				if (field.doesTileFitField(availableTiles.get(0))) tileNumber = 0;
				else if (availableTiles.size() >= 2 && field.doesTileFitField(availableTiles.get(1))) tileNumber = 1;		
				else if (availableTiles.size() == 3 && field.doesTileFitField(availableTiles.get(2))) tileNumber = 2;
				
				int[][] availablePositions = field.availablePositions(availableTiles.get(tileNumber));
				int[] coordinates = availablePositions[rand.nextInt(availablePositions.length)];
				int x = coordinates[0];
				int y = coordinates[1];
				if (!field.doesTileFitField(availableTiles.get(tileNumber), x, y)) {
					System.out.println("\nTile does not fit in this coordinates!!!");
					continue;
				}
				field.putTileOnField(availableTiles.get(tileNumber), x, y);
				score += field.scoredPoints();
				score += availableTiles.get(tileNumber).numberOfCubes();
				availableTiles.remove(tileNumber);

			} else if (tilePlacementMethod == "firstAvailable") {
				int tileNumber = 0;
				if (field.doesTileFitField(availableTiles.get(0))) tileNumber = 0;
				else if (availableTiles.size() >= 2 && field.doesTileFitField(availableTiles.get(1))) tileNumber = 1;		
				else if (availableTiles.size() == 3 && field.doesTileFitField(availableTiles.get(2))) tileNumber = 2;
				int[] coordinates = field.firstAvailablePosition(availableTiles.get(tileNumber));
				int x = coordinates[0];
				int y = coordinates[1];	
				field.putTileOnField(availableTiles.get(tileNumber), x, y);
				score += field.scoredPoints();
				score += availableTiles.get(tileNumber).numberOfCubes();
				availableTiles.remove(tileNumber);
			} else if (tilePlacementMethod == "mostPopulatedCube") {
				int tileNumber = 0;
				if (field.doesTileFitField(availableTiles.get(0))) tileNumber = 0;
				else if (availableTiles.size() >= 2 && field.doesTileFitField(availableTiles.get(1))) tileNumber = 1;		
				else if (availableTiles.size() == 3 && field.doesTileFitField(availableTiles.get(2))) tileNumber = 2;
				int[] coordinates = field.firstAvailableMostPopulatedCube(availableTiles.get(tileNumber));
				int x = coordinates[0];
				int y = coordinates[1];
				field.putTileOnField(availableTiles.get(tileNumber), x, y);
				score += field.scoredPoints();
				score += availableTiles.get(tileNumber).numberOfCubes();
				availableTiles.remove(tileNumber);
			} else if (tilePlacementMethod == "neuralNet") {
				int tileNumber = 0;
				if (field.doesTileFitField(availableTiles.get(0))) tileNumber = 0;
				else if (availableTiles.size() >= 2 && field.doesTileFitField(availableTiles.get(1))) tileNumber = 1;		
				else if (availableTiles.size() == 3 && field.doesTileFitField(availableTiles.get(2))) tileNumber = 2;
				int[] field1D = field.field1D();	
				int[] tile1D = availableTiles.get(tileNumber).tile1D();
				double[] input = new double[106];
				for (int i = 0; i < 81; i++) {
					input[i] = field1D[i];
				}
				int index = 0;
				for (int i = 81; i < 106; i++) {
					input[i] = tile1D[index];
					index++;
				}

				double[] predictions = neuralNet.predict(input, field, availableTiles.get(tileNumber));
				double highest = Integer.MIN_VALUE;
				int highestIndex = 0;
				for (int i = 0; i < predictions.length; i++) {
					if (predictions[i] > highest) {
						highest = predictions[i];
						highestIndex = i;
					}
				}
				int y = highestIndex / 9;
			       	int x = highestIndex - y*9;	
				field.putTileOnField(availableTiles.get(tileNumber), x, y);
				score += field.scoredPoints();
				score += availableTiles.get(tileNumber).numberOfCubes();
				availableTiles.remove(tileNumber);
			}
		}
		return score;
	}

	public void printAvailableTiles(ArrayList<Tile> availableTiles) {
		for (int i = 0; i < availableTiles.size(); i++) {
			System.out.println("\n" + (i+1) + ".");
			availableTiles.get(i).printTile();
			if (!field.doesTileFitField(availableTiles.get(i))) System.out.println("Doesn't Fit!");
		}
	}

}
