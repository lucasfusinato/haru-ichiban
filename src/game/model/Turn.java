package game.model;

import java.util.ArrayList;
import java.util.List;

import game.model.flower.Flower;
import game.model.gardener.GardenerColor;

public class Turn {
	
	private TurnStatus status;
	private List<Flower> redFlowers;
	private List<Flower> yellowFlowers;
	private Flower selectedRedFlower;
	private Flower selectedYellowFlower;
	private Player juniorGardener;
	private Player seniorGardener;
	
	public Turn() {
		redFlowers = new ArrayList<>();
		yellowFlowers = new ArrayList<>();
	}

	public boolean isStatus(TurnStatus status) {
		return this.status == status;
	}

	public boolean hasSelectedYellowFlower() {
		return selectedYellowFlower != null;
	}

	public boolean hasSelectedRedFlower() {
		return selectedRedFlower != null;
	}

	public void setSelectedYellowFlower(Flower selectedFlower) {
		selectedYellowFlower = selectedFlower;
	}

	public void setSelectedRedFlower(Flower selectedFlower) {
		selectedRedFlower = selectedFlower;
	}

	public int getSelectedRedNumber() {
		return selectedRedFlower.getNumber();
	}

	public int getSelectedYellowNumber() {
		return selectedYellowFlower.getNumber();
	}

	public void setJuniorGardener(Player gardener) {
		juniorGardener = gardener;
	}

	public void setSeniorGardener(Player gardener) {
		seniorGardener = gardener;
	}

	public void setStatus(TurnStatus status) {
		this.status = status;
	}

	public Player getJuniorGardener() {
		return juniorGardener;
	}

	public Player getSeniorGardener() {
		return seniorGardener;
	}

	public TurnStatus getStatus() {
		return status;
	}

	public GardenerColor getJuniorGardenerColor() {
		return juniorGardener.getColor();
	}

	public Flower getSelectedJuniorGardenerFlower() {
		return (getJuniorGardenerColor() == GardenerColor.RED) ? selectedRedFlower : selectedYellowFlower;
	}

	public Flower getSelectedSeniorGardenerFlower() {
		return (getSeniorGardenerColor() == GardenerColor.RED) ? selectedRedFlower : selectedYellowFlower;
	}

	public GardenerColor getSeniorGardenerColor() {
		return seniorGardener.getColor();
	}

	public Flower getRedFlowerAt(int index) {
		return redFlowers.get(index);
	}

	public List<Flower> getYellowFlowers() {
		return yellowFlowers;
	}

	public List<Flower> getRedFlowers() {
		return redFlowers;
	}

	public void addRedFlower(Flower flower) {
		redFlowers.add(flower);
	}
	
	public void addYellowFlower(Flower flower) {
		yellowFlowers.add(flower);
	}

	public Flower getRedFlower(int index) {
		return redFlowers.get(index);
	}

	public Flower getYellowFlower(int index) {
		return yellowFlowers.get(index);
	}

	public void removeFlower(Flower flower, GardenerColor color) {
		switch (color) {
			case RED:
				removeRedFlower(flower);
				break;
			case YELLOW:
				removeYellowFlower(flower);
				break;
			default:
				break;
		}
	}

	private void removeRedFlower(Flower flower) {
		redFlowers.remove(flower);
	}

	private void removeYellowFlower(Flower flower) {
		yellowFlowers.remove(flower);
	}

	public void setRedFlowers(List<Flower> redFlowers) {
		this.redFlowers = redFlowers;
	}

	public void setYelllowFlowers(List<Flower> yellowFlowers) {
		this.yellowFlowers = yellowFlowers;
	}

	public Flower getSelectedRedFlower() {
		return selectedRedFlower;
	}

	public Flower getSelectedYellowFlower() {
		return selectedYellowFlower;
	}

}
