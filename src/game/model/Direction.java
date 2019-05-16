package game.model;

public enum Direction implements Element {
	
	UP("arrow-up"),
	DOWN("arrow-down"),
	LEFT("arrow-left"),
	RIGHT("arrow-right");

	private String description;
	
	private Direction(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
}
