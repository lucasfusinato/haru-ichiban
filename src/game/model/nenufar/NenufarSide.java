package game.model.nenufar;

public enum NenufarSide {

	LIGHTED("lighted-nenufar"),
	DARKENED("darkened-nenufar");
	
	private String description;
	
	private NenufarSide(String description) {
		this.description = description;
	}
		
	public String getDescription() {
		return description;
	}
	
}
