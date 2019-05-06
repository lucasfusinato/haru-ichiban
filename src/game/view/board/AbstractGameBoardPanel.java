package game.view.board;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JTable;

import game.controller.GameControllerInterface;
import utils.view.ImageIconFactory;

@SuppressWarnings("serial")
public abstract class AbstractGameBoardPanel extends JPanel {

	private GameControllerInterface gameController;
	private JTable gameBoard;

	public AbstractGameBoardPanel(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.init();
	}

	public void refreshBoard() {
		this.gameBoard.updateUI();
	}

	public void updateMouseImage(String statusImage) {
		Cursor cursor = null;
		if(statusImage != null) {
			Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
			Image cursorImage = ImageIconFactory.create(statusImage).getImage();
			cursor = defaultToolkit.createCustomCursor(cursorImage, new Point(1, 1), "custom cursor"); 
		} else {
			cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		}
		this.setCursor(cursor);
	}

	private void init() {
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.gameBoard = this.createGameBoard();
	}

	private void addComponents() {
		this.add(this.gameBoard);
	}

	protected abstract JTable createGameBoard();
	
	protected GameControllerInterface getGameController() {
		return gameController;
	}

	protected JTable getGameBoard() {
		return gameBoard;
	}

}
