package game.model.gardener;

public class RedGardener extends Gardener {

	public RedGardener(String name) {
		super(name);
	}

	@Override
	public GardenerColor getColor() {
		return GardenerColor.RED;
	}

}
