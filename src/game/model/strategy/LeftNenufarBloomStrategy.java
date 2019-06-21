package game.model.strategy;

public class LeftNenufarBloomStrategy implements DirectionNenufarBloomStrategy {

	@Override
	public int calculateI(int i, int k) {
		return i - k;
	}

	@Override
	public int calculateJ(int j, int k) {
		return j - k;
	}

}
