package game.model.game;

public enum GameType {
	
	NORMAL("Normal"),
	ADVANCED("Avançado"),
	MASTER("Master");
	
	private String description;
	
	private GameType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public static GameType fromInteger(int type) {
		GameType[] values = GameType.values();
		if(type >= 0 && type < values.length) {
			return values[type];
		} else {
			return null;
		}
	}

}
