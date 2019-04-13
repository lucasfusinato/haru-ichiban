package controller.factory;

import model.NenufarBoardType;
import model.NenufarInterface;
import model.NenufarType;
import model.utils.Board;

public class NenufarBoardFactory {
	
	private static NenufarBoardFactory instance;
	private final int COLS = 5;
	private final int ROWS = 5;
	
	public synchronized static NenufarBoardFactory getInstance() {
		if(instance == null) {
			instance = new NenufarBoardFactory();
		}
		return instance;
	}

	private NenufarBoardFactory() {
		
	}
	
	private Board<NenufarInterface> createBoard() {
		return new Board<>(ROWS, COLS);
	}
	
	public Board<NenufarInterface> create(NenufarBoardType boardType) {
		Board<NenufarInterface> board = null;
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
	
	private Board<NenufarInterface> createStartBoard() {
		NenufarFactory nenufarFactory = NenufarFactory.getInstance();
		Board<NenufarInterface> startBoard = createBoard();
		
		//Linha 1
		startBoard.getCell(0, 0).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(0, 2).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(0, 4).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		
		//Linha 2
		startBoard.getCell(1, 1).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(1, 2).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(1, 3).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		
		//Linha 3
		startBoard.getCell(2, 0).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(2, 1).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(2, 3).setElement(nenufarFactory.create(NenufarType.YELLOW_FROG_NENUFAR));
		startBoard.getCell(2, 4).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		
		//Linha 4
		startBoard.getCell(3, 1).setElement(nenufarFactory.create(NenufarType.RED_FROG_NENUFAR));
		startBoard.getCell(3, 2).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(3, 3).setElement(nenufarFactory.create(NenufarType.DARKENED_NENUFAR));
		
		//Linha 5
		startBoard.getCell(4, 0).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(4, 2).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		startBoard.getCell(4, 4).setElement(nenufarFactory.create(NenufarType.NENUFAR));
		
		return startBoard;
	}
}
