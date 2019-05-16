package game.model.builder.board;

import game.model.factory.NenufarFactory;
import game.model.factory.RedGardenerFactory;
import game.model.factory.YellowGardenerFactory;
import game.model.nenufar.Nenufar;

public abstract class NenufarBoardBuilder extends BoardBuilder<Nenufar> {

	private NenufarFactory nenufarFactory = NenufarFactory.getInstance();
	
	@Override
	public void constructStartPosition() {
		int rows = board.getRows();
		int cols = board.getCols();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				board.setElementAtSquare(defineElementAtSquare(i, j), i, j);
			}
		}
	}

	private Nenufar defineElementAtSquare(int i, int j) {
		if(isStartDarkenedNenufarSquare(i, j)) {
			return nenufarFactory.createDarkenedNenufar();
		} else if(isStartLightedNenufarSquare(i, j)) {
			Nenufar nenufar = nenufarFactory.createLightedNenufar();
			if(isStartRedFrogNenufarSquare(i, j)) {
				nenufar.setElement(RedGardenerFactory.getInstance().createFrog());				
			} else if(isStartYellowFrogNenufarSquare(i, j)) {
				nenufar.setElement(YellowGardenerFactory.getInstance().createFrog());
			}
			return nenufar;  	
		}
		return null;
	}

	private boolean isStartDarkenedNenufarSquare(int i, int j) {
		int linha  = ((int) (board.getRows() / 2)) + 1;
		int coluna = ((int) (board.getCols() / 2)) + 1;
		return i == coluna && j == linha;
	}

	private boolean isStartLightedNenufarSquare(int i, int j) {
		boolean linhaPar  = (i % 2 == 0);
		boolean colunaPar = (j % 2 == 0);
		int linhaCentral  = (int) (board.getRows() / 2);
		int colunaCentral = (int) (board.getCols() / 2);
		boolean bordaLinhaCentral  = (i == linhaCentral  + 1 || i == linhaCentral  - 1);
		boolean bordaColunaCentral = (j == colunaCentral + 1 || j == colunaCentral - 1);
		
		return (bordaLinhaCentral && i != linhaCentral && j == colunaCentral)
			|| (bordaColunaCentral && j != colunaCentral  && i == linhaCentral)
			|| ((i != linhaCentral || j != colunaCentral) && linhaPar && colunaPar)
			|| ((i != linhaCentral || j != colunaCentral) && !linhaPar && !colunaPar);
	}

	private boolean isStartRedFrogNenufarSquare(int i, int j) {
		int linha  = ((int) (board.getRows() / 2)) + 1;
		int coluna = ((int) (board.getCols() / 2)) - 1;
		return i == coluna && j == linha;
	}

	private boolean isStartYellowFrogNenufarSquare(int i, int j) {
		int linha  = ((int) (board.getRows() / 2));
		int coluna = ((int) (board.getCols() / 2)) + 1;
		return i == coluna && j == linha;
	}

}
