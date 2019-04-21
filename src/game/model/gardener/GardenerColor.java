package game.model.gardener;

public enum GardenerColor {

	RED("Vermelho"),
	YELLOW("Amarelo");
	
	private String color;
	
	private GardenerColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
	
}
