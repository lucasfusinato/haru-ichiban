package game.view.board.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import game.controller.GameControllerInterface;
import game.controller.command.BoardMouseCommandFactory;
import utils.CommandInvoker;

public class GameBoardMouseListener implements MouseListener {

	private CommandInvoker commandInvoker;
	private BoardMouseCommandFactory mouseCommandFactory;

	public GameBoardMouseListener(GameControllerInterface gameController) {
		this.commandInvoker = new CommandInvoker();
		this.mouseCommandFactory = new BoardMouseCommandFactory(gameController);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createClickBoardCommand(getRowIndex(e), getColumnIndex(e)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createPressBoardCommand(getRowIndex(e), getColumnIndex(e)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createReleaseBoardCommand(getRowIndex(e), getColumnIndex(e)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createEnterBoardCommand(getRowIndex(e), getColumnIndex(e)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createExitBoardCommand(getRowIndex(e), getColumnIndex(e)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	protected int getRowIndex(MouseEvent e) {
		return ((JTable) e.getSource()).rowAtPoint(e.getPoint());
	}

	protected int getColumnIndex(MouseEvent e) {
		return ((JTable) e.getSource()).columnAtPoint(e.getPoint());
	}

}
