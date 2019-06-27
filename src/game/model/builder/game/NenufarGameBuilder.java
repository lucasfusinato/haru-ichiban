package game.model.builder.game;

import game.model.board.Board;
import game.model.factory.RedGardenerFactory;
import game.model.factory.YellowGardenerFactory;
import game.model.gardener.AbstractGardener;
import game.model.gardener.GardenerItem;
import game.model.nenufar.Nenufar;

public abstract class NenufarGameBuilder extends GameBuilder<Nenufar> {

	@Override
	public void defineRoundQuantity() {
		Board<Nenufar> board = getGame().getBoard();
		int rows = board.getRows();
		int cols = board.getCols();
		int count = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(board.getElementAtSquare(i, j) != null) {
					count++;
				}
			}
		}
		getGame().setTurnLimit((int) (count / 2));
	}

	@Override
	protected void definePlayer1(String playerName) {
		AbstractGardener gardener = RedGardenerFactory.getInstance().createGardener(playerName);
		getGame().setRedGardener(gardener);
		defineRedGardenerItens();
	}

	@Override
	protected void definePlayer2(String playerName) {
		AbstractGardener gardener = YellowGardenerFactory.getInstance().createGardener(playerName);
		getGame().setYellowGardener(gardener);
		defineYellowGardenerItens();
	}

	protected void defineRedGardenerItens() {
		getGame().addRedGardenerItem(GardenerItem.PULVERIZADOR);
		getGame().addRedGardenerItem(GardenerItem.TESOURA_PODA);
	}

	protected void defineYellowGardenerItens() {
		getGame().addYellowGardenerItem(GardenerItem.PULVERIZADOR);
		getGame().addYellowGardenerItem(GardenerItem.TESOURA_PODA);
	}

	protected abstract int getBoardRows();

	protected abstract int getBoardCols();

}
