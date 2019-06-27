package game.model.gardener;

public enum GardenerItem {

	PULVERIZADOR("Pulverizador"),
	TESOURA_PODA("Tesoura de Poda");
	
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
