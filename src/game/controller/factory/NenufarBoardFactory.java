package game.controller.factory;

import game.model.NenufarBoard;
import game.model.NenufarBoardType;
import game.model.NenufarType;

public class NenufarBoardFactory {
	
	private static NenufarBoardFactory instance;
	
	public synchronized static NenufarBoardFactory getInstance() {
		if(instance == null) {
			instance = new NenufarBoardFactory();
		}
		return instance;
	}

	private NenufarBoardFactory() {
		
	}
	
	private NenufarBoard createBoard() {
		return new NenufarBoard();
	}
	
	public NenufarBoard create(NenufarBoardType boardType) {
		NenufarBoard board = null;
		switch (boardType) {
			case START:
				board = createStartBoard();
				break;
	
			default:
				board = createBoard();
				break;
		}
		return board;
	}
	
	private NenufarBoard createStartBoard() {
		NenufarFactory nenufarFactory = NenufarFactory.getInstance();
		NenufarBoard startBoard = createBoard();
		
		//Linha 1
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 0, 0);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 0, 2);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 0, 4);
		
		//Linha 2
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 1, 1);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 1, 2);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 1, 3);
		
		//Linha 3
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 2, 0);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 2, 1);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.YELLOW_FROG_NENUFAR), 2, 3);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 2, 4);
		
		//Linha 4
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.RED_FROG_NENUFAR), 3, 1);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 3, 2);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.DARKENED_NENUFAR), 3, 3);
		
		//Linha 5
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 4, 0);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 4, 2);
		startBoard.setElementAtCell(nenufarFactory.create(NenufarType.NENUFAR), 4, 4);
		
		return startBoard;
	}
}
