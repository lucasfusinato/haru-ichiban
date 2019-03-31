package controller;

import controller.factory.NenufarBoardFactory;
import model.NenufarBoardType;
import model.NenufarInterface;
import model.utils.Board;

public class GameController extends AbstractGameController {

	private Board<NenufarInterface> board;
	
	public GameController() {
		super();
		init();
	}
	
	private void init() {
		board = NenufarBoardFactory.getInstance().create(NenufarBoardType.START);
	}
	
	public int getRowCount() {
		return board.getRows();
	}

	public int getColumnCount() {
		return board.getCols();
	}

	public String getValueAt(int rowIndex, int columnIndex) {
		NenufarInterface nenufar = board.getElementAtCell(rowIndex, columnIndex);
		if(nenufar == null) {
			return null;
		} else {
			return nenufar.getImagePath();
		}
	}
	
}
