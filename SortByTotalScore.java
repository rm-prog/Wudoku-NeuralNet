import java.util.Comparator;

public class SortByTotalScore implements Comparator<Player> {
	public int compare (Player a, Player b) {
		if (a.getScore() < b.getScore()) return 1;
		else if (b.getScore() < a.getScore()) return -1;
		return 0;
	}
}
