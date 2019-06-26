package game.model.flower;

import game.model.Element;

public abstract class Flower implements Element, Cloneable {

	private int number;
	
	public Flower(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
	
	@Override
	public Flower clone() throws CloneNotSupportedException {
		return (Flower) super.clone();
	}

}
