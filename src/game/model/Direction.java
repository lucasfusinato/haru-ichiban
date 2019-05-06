package game.model;

public enum Direction {
	
	UP("arrow-up"),
	DOWN("arrow-down"),
	LEFT("arrow-left"),
	RIGHT("arrow-right");

	private String description;
	
	private Direction(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
