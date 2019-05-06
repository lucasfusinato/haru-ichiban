package game.controller.builder;

import game.model.NenufarBoard;
import game.model.factory.NenufarFactory;

public class StartNenufarBoardBuilder extends NenufarBoardBuilder {

	@Override
	public void definePieces() {
		NenufarBoard board = getObject();
		NenufarFactory nenufarFactory = NenufarFactory.getInstance();
		
		//Linha 1
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 0, 0);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 0, 2);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 0, 4);
		
		//Linha 2
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 1, 1);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 1, 2);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 1, 3);
		
		//Linha 3
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 2, 0);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 2, 1);
		board.setElementAtSquare(nenufarFactory.createYellowFrogNenufar(), 2, 3);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 2, 4);
		
		//Linha 4
		board.setElementAtSquare(nenufarFactory.createRedFrogNenufar(), 3, 1);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 3, 2);
		board.setElementAtSquare(nenufarFactory.createDarkenedNenufar(), 3, 3);
		
		//Linha 5
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 4, 0);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 4, 2);
		board.setElementAtSquare(nenufarFactory.createLightedNenufar(), 4, 4);
	}

}
