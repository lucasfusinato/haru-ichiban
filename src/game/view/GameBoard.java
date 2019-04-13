package game.view;

import javax.swing.JTable;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameBoard extends JTable {
	
	private GameControllerInterface gameController;
	private GameBoardModel gameBoardModel;
	private GameBoardCellRenderer gameBoardCellRenderer;
	
	public GameBoard(GameControllerInterface gameController) {
		this.init(gameController);
	}
	
	private void init(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.setRowHeight(100);
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.gameBoardModel 		= new GameBoardModel(this.gameController);
		this.gameBoardCellRenderer  = new GameBoardCellRenderer();
	}

	private void addComponents() {
		this.setModel(this.gameBoardModel);
		this.setDefaultRenderer(Object.class, this.gameBoardCellRenderer);
	}
	
}
