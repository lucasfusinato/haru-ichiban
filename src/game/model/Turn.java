package game.model;

import java.util.ArrayList;
import java.util.List;

import game.model.flower.Flower;
import game.model.gardener.Gardener;
import game.model.gardener.GardenerColor;

public class Turn {
	
	private TurnStatus status;
	private List<Flower> redFlowers;
	private List<Flower> yellowFlowers;
	private Flower selectedRedFlower;
	private Flower selectedYellowFlower;
	private Gardener juniorGardener;
	private Gardener seniorGardener;
	
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

	public void setJuniorGardener(Gardener gardener) {
		juniorGardener = gardener;
	}

	public void setSeniorGardener(Gardener gardener) {
		seniorGardener = gardener;
	}

	public void setStatus(TurnStatus status) {
		this.status = status;
	}

	public Gardener getJuniorGardener() {
		return juniorGardener;
	}

	public Gardener getSeniorGardener() {
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
