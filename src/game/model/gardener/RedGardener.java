package game.model.gardener;

public class RedGardener extends AbstractGardener {

	public RedGardener(String name) {
		super(name);
	}

	@Override
	public GardenerColor getColor() {
		return GardenerColor.RED;
	}

}
