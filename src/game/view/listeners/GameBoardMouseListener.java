package game.view.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.controller.GameControllerInterface;
import game.view.GameBoard;

public class GameBoardMouseListener implements MouseListener {

	private GameBoard gameBoard;
	private GameControllerInterface gameController;
	
	public GameBoardMouseListener(GameBoard gameBoard, GameControllerInterface gameController) {
		this.gameBoard = gameBoard;
		this.gameController = gameController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.gameController.selectSquare(this.gameBoard.getSelectedRow(), this.gameBoard.getSelectedColumn());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
