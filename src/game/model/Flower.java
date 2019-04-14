package game.model;

public abstract class Flower implements ComponentInterface {

	private int number;
	
	public Flower(int number) {
		this.init(number);
	}
	
	private void init(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
	
}
