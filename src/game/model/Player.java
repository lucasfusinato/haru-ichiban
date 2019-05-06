package game.model;

import game.model.gardener.GardenerColor;

public class Player {
	
	private String name;
	private GardenerColor color;
	
	public Player(String name, GardenerColor color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public GardenerColor getColor() {
		return color;
	}

}
