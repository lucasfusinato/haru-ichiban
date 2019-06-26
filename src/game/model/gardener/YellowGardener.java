package game.model.gardener;

public class YellowGardener extends AbstractGardener {

	public YellowGardener(String name) {
		super(name);
	}

	@Override
	public GardenerColor getColor() {
		return GardenerColor.YELLOW;
	}
	
}
