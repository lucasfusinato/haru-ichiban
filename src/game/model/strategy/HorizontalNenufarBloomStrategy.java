package game.model.strategy;

public class HorizontalNenufarBloomStrategy implements DirectionNenufarBloomStrategy {

	@Override
	public int calculateI(int i, int k) {
		return i;
	}

	@Override
	public int calculateJ(int j, int k) {
		return j + k;
	}

}
