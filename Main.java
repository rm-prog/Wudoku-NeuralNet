public class Main {
	public static void main (String[] args) {
		Simulation simulation = new Simulation();
		System.out.println("Hello");
		NeuralNetwork neuralNet = new NeuralNetwork();
		String tilePlacementMethod = "firstAvailable";
		//simulation.simulateMethod(tilePlacementMethod, neuralNet);
		System.out.println(tilePlacementMethod);	
		simulation.simulateNeuralNetworks();	
	}
}
