package game.model.game;

import java.util.ArrayList;
import java.util.List;

import game.model.board.Board;
import game.model.gardener.Gardener;

public class Game<E> extends GameStep {

	private Gardener redGardener;
	private Gardener yellowGardener;
	private List<GameStep> steps;
	private int roundTurnLimit;
	private Board<E> board;

	public Game() {
		steps = new ArrayList<>();
	}

	public void addRound(GameStep round) {
		steps.add(round);
	}

	public Gardener getRedGardener() {
		return redGardener;
	}

	public void setRedGardener(Gardener redGardener) {
		this.redGardener = redGardener;
	}

	public Gardener getYellowGardener() {
		return yellowGardener;
	}

	public void setYellowGardener(Gardener yellowGardener) {
		this.yellowGardener = yellowGardener;
	}

	public List<GameStep> getSteps() {
		return steps;
	}

	public void setSteps(List<GameStep> steps) {
		this.steps = steps;
	}

	public int getTurnLimit() {
		return roundTurnLimit;
	}

	public void setTurnLimit(int limit) {
		this.roundTurnLimit = limit;
	}

	public Board<E> getBoard() {
		return board;
	}

	public void setBoard(Board<E> board) {
		this.board = board;
	}

	@Override
	public int getRedPoints() {
		int points = 0;
		for(GameStep step : steps) {
			points += step.getRedPoints();
		}
		return points;
	}

	@Override
	public int getYellowPoints() {
		int points = 0;
		for(GameStep step : steps) {
			points += step.getYellowPoints();
		}
		return points;
	}

	
}
