package game.model.factory;

import game.model.NenufarBoard;

public class NenufarBoardFactory {

	public static NenufarBoard createEmptyBoard() {
		return new NenufarBoard();
	}
	
	public static NenufarBoard createStartBoard() {
		NenufarBoard startBoard = createEmptyBoard();
		NenufarFactory nenufarFactory = NenufarFactory.getInstance();
		
		//Linha 1
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 0, 0);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 0, 2);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 0, 4);
		
		//Linha 2
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 1, 1);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 1, 2);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 1, 3);
		
		//Linha 3
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 2, 0);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 2, 1);
		startBoard.setElementAtSquare(nenufarFactory.createYellowFrogNenufar(), 2, 3);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 2, 4);
		
		//Linha 4
		startBoard.setElementAtSquare(nenufarFactory.createRedFrogNenufar(), 3, 1);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 3, 2);
		startBoard.setElementAtSquare(nenufarFactory.createDarkenedNenufar(), 3, 3);
		
		//Linha 5
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 4, 0);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 4, 2);
		startBoard.setElementAtSquare(nenufarFactory.createNenufar(), 4, 4);
		
		return startBoard;
	}

}
