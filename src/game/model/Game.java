package game.model;

import java.util.ArrayList;
import java.util.List;

import game.model.board.Board;
import game.model.gardener.Gardener;

public class Game<E> {

	private Gardener redGardener;
	private Gardener yellowGardener;
	private int redPoints;
	private int yellowPoints;
	private List<Round> rounds;
	private int roundQuantity;
	private Board<E> board;

	public Game() {
		rounds = new ArrayList<>();
	}

	public void addRound(Round round) {
		rounds.add(round);
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

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public int getRoundQuantity() {
		return roundQuantity;
	}

	public void setRoundQuantity(int roundQuantity) {
		this.roundQuantity = roundQuantity;
	}

	public Board<E> getBoard() {
		return board;
	}

	public void setBoard(Board<E> board) {
		this.board = board;
	}

	public int getRedPoints() {
		return redPoints;
	}

	public void setRedPoints(int redPoints) {
		this.redPoints = redPoints;
	}

	public int getYellowPoints() {
		return yellowPoints;
	}

	public void setYellowPoints(int yellowPoints) {
		this.yellowPoints = yellowPoints;
	}

	public void addRedPoints(int redPoints) {
		this.redPoints += redPoints;
	}

	public void addYellowPoints(int yellowPoints) {
		this.yellowPoints += yellowPoints;
	}
	
}
