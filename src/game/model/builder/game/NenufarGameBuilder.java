package game.model.builder.game;

import game.model.factory.RedGardenerFactory;
import game.model.factory.YellowGardenerFactory;
import game.model.nenufar.Nenufar;

public abstract class NenufarGameBuilder extends GameBuilder<Nenufar> {

	@Override
	public void defineRoundQuantity() {
		int rounds = calculateGameRounds(getBoardRows(), getBoardCols());
		getGame().setRoundQuantity(rounds);
	}

	@Override
	protected void definePlayer1(String playerName) {
		getGame().setRedGardener(RedGardenerFactory.getInstance().createGardener(playerName));
	}

	@Override
	protected void definePlayer2(String playerName) {
		getGame().setYellowGardener(YellowGardenerFactory.getInstance().createGardener(playerName));
	}

	protected abstract int getBoardRows();

	protected abstract int getBoardCols();

	private int calculateGameRounds(int boardRows, int boardCols) {
		return (int) (((boardRows - 1) * (boardCols - 1)) / 2);
	}

}
