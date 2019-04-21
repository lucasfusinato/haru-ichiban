package game.model;

import game.model.flower.Flower;
import game.model.gardener.Gardener;

public class GameRound {
	
	private Flower selectedRedFlower;
	private Flower selectedYellowFlower;
	private Gardener seniorGardener;
	private Gardener juniorGardener;
	
	public Flower getSelectedRedFlower() {
		return selectedRedFlower;
	}
	public void setSelectedRedFlower(Flower selectedRedFlower) {
		this.selectedRedFlower = selectedRedFlower;
	}
	public Flower getSelectedYellowFlower() {
		return selectedYellowFlower;
	}
	public void setSelectedYellowFlower(Flower selectedYellowFlower) {
		this.selectedYellowFlower = selectedYellowFlower;
	}
	public Gardener getSeniorGardener() {
		return seniorGardener;
	}
	public void setSeniorGardener(Gardener seniorGardener) {
		this.seniorGardener = seniorGardener;
	}
	public Gardener getJuniorGardener() {
		return juniorGardener;
	}
	public void setJuniorGardener(Gardener juniorGardener) {
		this.juniorGardener = juniorGardener;
	}

}
