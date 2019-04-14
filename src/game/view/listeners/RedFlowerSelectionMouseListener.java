package game.view.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import game.controller.GameControllerInterface;

public class RedFlowerSelectionMouseListener implements MouseListener {

	private GameControllerInterface gameController;
	private JTable gameBoard;
	
	public RedFlowerSelectionMouseListener(GameControllerInterface gameController, JTable gameBoard) {
		this.gameController = gameController;
		this.gameBoard 		= gameBoard;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.gameController.selectRedFlower(this.gameBoard.getSelectedRow(), this.gameBoard.getSelectedColumn());
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
