package game.view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private GameControllerInterface gameController;
	private JTable gameBoard;
	
	public GamePanel(GameControllerInterface gameController) {
		this.init(gameController);
	}

	private void init(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.setLayout(new GridLayout(1, 1));
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.gameBoard = new GameBoard(gameController);
	}

	private void addComponents() {
		this.add(this.gameBoard);
	}

}
