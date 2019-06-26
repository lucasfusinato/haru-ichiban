package game.model.gardener;

public enum GardenerItem {

	SPRAY("Spray"),
	VENENO("Veneno");
	
	private String icone;
	
	private GardenerItem(String icone) {
		this.icone = icone;
	}
	
	public String getDescription() {
		return icone;
	}
	
	public static GardenerItem getByNumber(int number) {
		int i = 0;
		for(GardenerItem item : values()) {
			if(i == number) {
				return item;
			}
			i++;
		}
		return null;
	}
	
}
