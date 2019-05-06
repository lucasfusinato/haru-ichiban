package game.model;

import java.util.ArrayList;
import java.util.List;

import game.model.flower.Flower;

public class Round {
	
	private NenufarBoard board;
	private List<Turn> turns;
	private List<Flower> redFlowers;
	private List<Flower> yellowFlowers;
	private List<Flower> withdrawRedFlowers;
	private List<Flower> withdrawYellowFlowers;
	
	public Round() {
	}

	public Round(NenufarBoard board, List<Flower> redFlowers, List<Flower> yellowFlowers) {
		this.turns = new ArrayList<>();
		this.board = board;
		this.redFlowers = redFlowers;
		this.yellowFlowers = yellowFlowers;
		this.withdrawRedFlowers = redFlowers;
		this.withdrawYellowFlowers = yellowFlowers;
	}

	public void addTurn(Turn turn) {
		turns.add(turn);
	}

	public void removeTurn(Turn turn) {
		turns.remove(turn);
	}

	public void removeFlower(Flower flower) {
		// TODO Auto-generated method stub
		
	}

	public boolean recheadTurnLimit() {
		// TODO Auto-generated method stub
		return false;
	}

	public Flower getYellowFlower(int index) {
		return yellowFlowers.get(index);
	}

	public Flower getRedFlower(int index) {
		return redFlowers.get(index);
	}

	public List<Turn> getTurns() {
		return turns;
	}

	public List<Flower> getWithdrawRedFlowers() {
		return withdrawRedFlowers;
	}

	public List<Flower> getWithdrawYellowFlowers() {
		return withdrawYellowFlowers;
	}

	public Flower getWithdrawRedFlower(int index) {
		return withdrawRedFlowers.get(index);
	}

	public Flower getWithdrawYellowFlower(int index) {
		return withdrawYellowFlowers.get(index);
	}

	public Flower removeWithdrawRedFlower(int index) {
		return withdrawRedFlowers.remove(index);
	}

	public Flower removeWithdrawYellowFlower(int index) {
		return withdrawYellowFlowers.remove(index);
	}

}
