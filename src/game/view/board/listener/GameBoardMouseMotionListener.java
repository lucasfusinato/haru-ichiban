package game.view.board.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import game.controller.GameControllerInterface;
import game.view.board.GameBoard;

public class GameBoardMouseMotionListener implements MouseMotionListener {

	private GameBoard gameBoard;
	private GameControllerInterface gameController;

	public GameBoardMouseMotionListener(GameBoard gameBoard, GameControllerInterface gameController) {
		this.gameBoard = gameBoard;
		this.gameController = gameController;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.gameController.mouseMovedToBoardSquare(this.getRowIndex(e), this.getColumnIndex(e));
	}

	protected int getRowIndex(MouseEvent e) {
		return this.gameBoard.rowAtPoint(e.getPoint());
	}

	protected int getColumnIndex(MouseEvent e) {
		return this.gameBoard.columnAtPoint(e.getPoint());
	}

}
