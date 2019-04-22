package game.model.gardener;

import java.util.ArrayList;
import java.util.List;

import game.model.flower.Flower;

public abstract class Gardener {
	
	private List<Flower> flowers;
	private String name;
	private int points;
	
	public Gardener(String name) {
		this.flowers = new ArrayList<>();
		this.name = name;
	}
	
	public abstract GardenerColor getColor();
	
	public void addFlower(Flower flower) {
		this.flowers.add(flower);
	}
	
	public Flower getFlower(int index) {
		return this.flowers.get(index);
	}
	
	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}
	
	public List<Flower> getFlowers() {
		return this.flowers;
	}

	public String getName() {
		return this.name;
	}

	public void addPoint() {
		this.points++;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}

	public int getPoints() {
		return this.points;
	}

}
