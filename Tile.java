public class Tile {
	
	private int[][] cubes;
	private int xUpperLeftMost;
	private int yUpperLeftMost;

	public Tile (int[][] cubes, int x, int y) {
		this.cubes = cubes;
		xUpperLeftMost = x;
		yUpperLeftMost = y;
	}

	public void putTileOnField (int[][] field, int x, int y) {
		for (int i = 0; i < cubes.length; i++) {
			for (int j = 0; j < cubes[i].length; j++) {
				if (cubes[i][j] == 1)  field[y+i][x+j-xUpperLeftMost] = 1;
			}
		}
	}

	public void printTile () {
		for (int i = 0; i < cubes.length; i++) {
			for (int j = 0; j < cubes[i].length; j++) {
				if (cubes[i][j] == 0) System.out.print(" ");
				else if (cubes[i][j] == 1) System.out.print("■");
			}		
			System.out.print("\n");
		}
		System.out.println("x: " + xUpperLeftMost);
		System.out.println("y: " + yUpperLeftMost);
	}

	public boolean doesTileFitField (int[][] field, int x, int y) {
		for (int i = 0; i < cubes.length; i++) {
			for (int j = 0; j < cubes[i].length; j++) {
				if (cubes[i][j] == 1) {
					if (y+i < 0 || y+i > 8 || x+j-xUpperLeftMost < 0 || x+j-xUpperLeftMost > 8 || (field[y+i][x+j-xUpperLeftMost] == 1 && cubes[i][j] == 1)) return false;
				}
			}	
		}

		return true;

	}
	
	public int numberOfCubes () {
		int result = 0;
		for (int i = 0; i < cubes.length; i++) {
			for (int j = 0; j < cubes[i].length; j++) {
				if (cubes[i][j] == 1) result++;
			}
		}
		return result;
	}

	public int[] tile1D () {
		int[] result = new int[25];
		int index = 0;
		for (int i = 0; i < cubes.length; i++) {
			index = 5*i;
			for (int j = 0; j < cubes[i].length; j++) {
				result[index] = cubes[i][j];
				index++;		
			}	
		}
		return result;	
	}

}
