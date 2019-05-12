package game.model.nenufar;

import game.model.Element;

public enum NenufarSide implements Element {

	LIGHTED("lighted-nenufar"),
	DARKENED("darkened-nenufar");
	
	private String description;
	
	private NenufarSide(String description) {
		this.description = description;
	}
		
	@Override
	public String getDescription() {
		return description;
	}
	
}
