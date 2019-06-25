package game.model;

public enum Bloom {
	
	BASIC(1),
	STRAIGHT(3),
	DIAGONAL(4),
	PREMIUM(5);

	private int points;
	
	private Bloom(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return points;
	}
	
}
