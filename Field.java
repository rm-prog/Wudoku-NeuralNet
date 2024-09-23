import java.util.ArrayList;

public class Field {
	
	private int[][] field = new int[9][9];

	public void putTileOnField (Tile tile, int x, int y) {
		tile.putTileOnField(field, x, y);
	}

	public void printField () {
		updateField();

		System.out.println("  123 456 789");
		System.out.println("  ___ ___ ___");

		int y = 0;
		int x = 0;

		for (int i = 1; i <= 11; i++) {
			x = 0;		
			if (i % 4 == 0) {
				System.out.print("  === === ===" + "\n");
				continue;
			}
			System.out.print((y+1) + "|");
			for (int j = 1; j <= 12; j++) {
				if (j % 4 == 0) System.out.print("|");
				else if (field[y][x] == 0) {
					System.out.print(" ");
					x++;
				}
				else if (field[y][x] == 1) {
					System.out.print("■");
					x++;
				}
			}
			System.out.print("\n");
			y++;
		}

		System.out.println("  ___ ___ ___");

	}

	public int scoredPoints () {

		int points = 0;

		int[] columns = new int[9];
		int[] rows = new int[9];
		int[] cubes = new int[9];

		for (int i = 0; i < 9; i++) {
			rows[i] = 1;
			for (int j = 0; j < 9; j++) {
				if (field[i][j] == 0) {
					rows[i] = 0;
					break;
				}
			}
			if (rows[i] == 1) points += 9 * (points/9 + 1);
		}
		for (int i = 0; i < 9; i++) {
			columns[i] = 1;
			for (int j = 0; j < 9; j++) {
				if (field[j][i] == 0) {
					columns[i] = 0;
					break;
				}
			}
			if (columns[i] == 1) points += 9 * (points/9 +1);
		}

		int cubeIndex = 0;
		for (int k = 0; k <= 6; k += 3) {
			for (int l = 0; l <= 6; l += 3) {
				cubes[cubeIndex] = 1;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (field[i+k][j+l] == 0) {
							cubes[cubeIndex] = 0;
						}
					}
				}
				if (cubes[cubeIndex] == 1) points += 9 * (points/9 + 1);
				cubeIndex++;
			}
		}	

		return points;
	}

	public void updateField () {
		int[] columns = new int[9];
		int[] rows = new int[9];
		int[] cubes = new int[9];

		for (int i = 0; i < 9; i++) {
			rows[i] = 1;
			for (int j = 0; j < 9; j++) {
				if (field[i][j] == 0) {
					rows[i] = 0;
					break;
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			columns[i] = 1;
			for (int j = 0; j < 9; j++) {
				if (field[j][i] == 0) {
					columns[i] = 0;
					break;
				}
			}
		}

		int cubeIndex = 0;
		for (int k = 0; k <= 6; k += 3) {
			for (int l = 0; l <= 6; l += 3) {
				cubes[cubeIndex] = 1;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (field[i+k][j+l] == 0) {
							cubes[cubeIndex] = 0;
						}
					}
				}
				cubeIndex++;
			}
		}
		for (int i = 0; i < 9; i++) {
			if (rows[i] == 1) {
				for (int j = 0; j < 9; j++) {
					field[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			if (columns[i] == 1) {
				for (int j = 0; j < 9; j++) {
					field[j][i] = 0;
				}
			}
		}

		cubeIndex = 0;
		for (int k = 0; k <= 6; k += 3) {
			for (int l = 0; l <= 6; l += 3) {
				if (cubes[cubeIndex] == 1) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							field[i+k][j+l] = 0;
						}
					}
				}
				cubeIndex++;
			}
		}

	}

	public boolean doesTileFitField (Tile tile, int x, int y) {
		return tile.doesTileFitField(field, x, y);
	}

	public boolean doesTileFitField (Tile tile) {
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tile.doesTileFitField(field, j, i)) return true;
			}
		}
		return false;
	}
	
	public int numOfavailablePositions (Tile tile) {
		int result = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tile.doesTileFitField(field, j, i)) result++;
			}
		}
		return result;
	}

	public int[][] availablePositions (Tile tile) {
		ArrayList<int[]> positions = new ArrayList<int[]>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tile.doesTileFitField(field, j, i)) positions.add(new int[] { j, i});
			}
		}
		int[][] result = positions.toArray(new int[0][0]);
		return result;
	}

	public int[] firstAvailablePosition (Tile tile) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tile.doesTileFitField(field, j, i)) return new int[] { j, i };
			}
		}
		return new int[] { -1, -1 };
	}	

	public int[] sortedAscCubesPopulation () {
		int[] cubesIndex =  { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] populations = new int[9];
		int cubeIndex = 0;
		for (int k = 0; k <= 6; k += 3) {
			int population = 0;
			for (int l = 0; l <= 6; l += 3) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (field[i+k][j+l] == 1) {
							populations[cubeIndex]++;
						}
					}
				}
				cubeIndex++;
			}
		}

		int index;
		for (index = 0; index < 8; index++) {
			int highestPopulationIndex = index;
			int highestPopulation = populations[index];
			for (int i = index; i < 9; i++) {
				if (populations[index] > highestPopulation) {
					highestPopulation = populations[index];
					highestPopulationIndex = i;
				}
			}
			int tempCube = cubesIndex[index];
			cubesIndex[index] = cubesIndex[highestPopulationIndex];
			cubesIndex[highestPopulationIndex] = tempCube;
			int tempPopulation = populations[index];
			populations[index] = populations[highestPopulationIndex];
			populations[highestPopulationIndex] = tempPopulation;
		}
		return cubesIndex;
	}

	public int[] firstAvailableMostPopulatedCube (Tile tile) {
		int[] sortedAscCubes = sortedAscCubesPopulation();
		for (int index = 8; index >= 0; index--) {
			int k;
			int l;
			if (sortedAscCubes[index] >= 0 && sortedAscCubes[index] < 3) {
				k = 0;
			} else if (sortedAscCubes[index] >= 3 && sortedAscCubes[index] < 6 ) {
				k = 3;
			} else {
				k = 6;
			}
			l = (sortedAscCubes[index] - k) * 3;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tile.doesTileFitField(field, j+l, i+k)) return new int[] { j+l, i+k}; 
				}
			}
		}
		return new int[] { -1, -1 };
	}

	public int[] field1D () {
		int[] result = new int[81];
		int index = 0;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				result[index] = field[i][j];
				index++;
			}
		}
		return result;
	}	

}
