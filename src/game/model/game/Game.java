package game.model.game;

import java.util.ArrayList;
import java.util.List;

import game.model.board.Board;
import game.model.gardener.AbstractGardener;
import game.model.gardener.GardenerItem;

public class Game<E> extends GameStep {

	private AbstractGardener redGardener;
	private AbstractGardener yellowGardener;
	private List<GardenerItem> redGardenerItens;
	private List<GardenerItem> yellowGardenerItens;
	private List<GameStep> steps;
	private int roundTurnLimit;
	private Board<E> board;

	public Game() {
		steps = new ArrayList<>();
		redGardenerItens = new ArrayList<>();
		yellowGardenerItens = new ArrayList<>();
	}

	public void addRound(GameStep round) {
		steps.add(round);
	}

	public AbstractGardener getRedGardener() {
		return redGardener;
	}

	public void setRedGardener(AbstractGardener redGardener) {
		this.redGardener = redGardener;
	}

	public AbstractGardener getYellowGardener() {
		return yellowGardener;
	}

	public void setYellowGardener(AbstractGardener yellowGardener) {
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

	public void addRedGardenerItem(GardenerItem item) {
		redGardenerItens.add(item);
	}

	public void addYellowGardenerItem(GardenerItem item) {
		yellowGardenerItens.add(item);
	}
	
	public GardenerItem removeRedGardenerItem(GardenerItem item) {
		GardenerItem removed = null;
		int index = redGardenerItens.indexOf(item);
		if(index >= 0) {
			removed = redGardenerItens.remove(index);
		}
		return removed;
	}
	
	public GardenerItem removeYellowGardenerItem(GardenerItem item) {
		GardenerItem removed = null;
		int index = yellowGardenerItens.indexOf(item);
		if(index >= 0) {
			removed = yellowGardenerItens.remove(index);
		}
		return removed;
	}
	
	public List<GardenerItem> getRedGardenerItens() {
		return redGardenerItens;
	}
	
	public List<GardenerItem> getYellowGardenerItens() {
		return yellowGardenerItens;
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
