import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

public class Simulation {
	
	public int[] randomTilePlacement () {
		Random rand = new Random();
		int x = rand.nextInt(9);
		int y = rand.nextInt(9);

		return new int[] { x, y };
	}
	
	public void simulateMethod (String tilePlacementMethod, NeuralNetwork neuralNet) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		Game game = new Game();
		for (int i = 1; i <= 500; i++) {
			scores.add(game.play(tilePlacementMethod, neuralNet));
		}
		int total = 0;
		int highest = 0;
		int lowest = Integer.MAX_VALUE;
		for (int x : scores) {
			total += x;
			if (x > highest) highest = x;
			if (x < lowest) lowest = x;
		}
		float average = total / scores.size();
	       	
		System.out.println("Highest: " + highest);
		System.out.println("Lowest: " + lowest);
		System.out.println("Average: " + average);	
	}

	public void simulateNeuralNetworks () {
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 1; i <= 100; i++) {
			players.add(new Player());
		}

		for (Player player : players) {
			System.out.print(".");
			for (int i = 1; i <= 100; i++) {
				player.play();
			}
		}
		
		int highestScore = 0;
	       	int highestIndex = 0;	
		int recordIndex = 0;
		int record = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getScore() > highestScore) {
				highestIndex = i;
				highestScore = players.get(i).getScore();
			}
			if (players.get(i).getHighestScore() > record) {
				recordIndex = i;
				record = players.get(i).getHighestScore();
			}
		}
		System.out.println("\n1. Best Player: " + highestScore);
		System.out.println("Average of best player: " + highestScore / 100);
		System.out.println("Its highscore: " + players.get(highestIndex).getHighestScore());
		System.out.println("Record " + record);
		
		for (int k = 1; k <= 100; k++) {

			players.sort(new SortByTotalScore());
		
			for (int i = 1; i <= 95; i++) {
				players.remove(5);
			}

			ArrayList<Player> copies = new ArrayList<Player>();
			
			for (Player player : players) {
				player.resetScore();
			}

			for (int i = 0; i < 5; i++) copies.add(new Player(players.get(0)));
			for (int i = 0; i < players.size()-1; i++) {
				for (int j = i + 1; j < players.size(); j++) {
					for (int l = 1; l <= 9; l++) {
						copies.add(new Player(players.get(i), players.get(j)));
					}
				}
			}

			for (Player player : copies) {
				players.add(player);
			}		
		
			for (Player player : players) {
				player.mutate();
				System.out.print(".");
				for (int i = 1; i <= 100; i++) {
					player.play();
				}
			}
		
			highestScore = 0;
	       		highestIndex = 0;	
			recordIndex = 0;
			record = 0;
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getScore() > highestScore) {
					highestIndex = i;
					highestScore = players.get(i).getScore();
				}
				if (players.get(i).getHighestScore() > record) {
					recordIndex = i;
					record = players.get(i).getHighestScore();
				}
			}
			System.out.println("\n" + (k+1) + ". Best Player: " + highestScore);
			System.out.println("Average of best player: " + highestScore / 100);
			System.out.println("Its highscore: " + players.get(highestIndex).getHighestScore());
			System.out.println("Record " + record);
		}
	}
}
