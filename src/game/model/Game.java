package game.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private Player redPlayer;
	private Player yellowPlayer;
	private List<Round> rounds;

	public Game(Player red, Player yellow) {
		redPlayer = red;
		yellowPlayer = yellow;
		rounds = new ArrayList<>();
	}

	public void addRound(Round round) {
		rounds.add(round);
	}

	public Player getRedGardener() {
		return redPlayer;
	}

	public Player getYellowGardener() {
		return yellowPlayer;
	}

}
