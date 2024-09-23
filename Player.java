public class Player {
	
	private NeuralNetwork neuralNet;
	private int score;
	private int highestScore;

	public Player () {
		neuralNet = new NeuralNetwork();
		score = 0;
		highestScore = 0;
	}

	public Player (Player player) {
		neuralNet = new NeuralNetwork(player.neuralNet);
		score = 0;
		highestScore = 0;
	}

	public Player (Player playerA, Player playerB) {
		neuralNet = new NeuralNetwork(playerA.neuralNet, playerB.neuralNet);
		score = 0;
		highestScore = 0;
	}

	public void play() {
		Game game = new Game();
		int gameScore = game.play("neuralNet", neuralNet);
		if (gameScore > highestScore) highestScore = gameScore;
		score += gameScore;
	}

	public int getScore () {
		return score;
	}

	public int getHighestScore () {
		return highestScore;
	}

	public void resetScore() {
		score = 0;
	}
	
	public void mutate() {
		neuralNet.mutate();
	}
}
