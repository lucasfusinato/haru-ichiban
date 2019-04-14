package game.view;

import java.awt.Color;

import javax.swing.JTable;

import game.controller.GameControllerInterface;
import game.view.listeners.GameBoardMouseListener;

@SuppressWarnings("serial")
public class GameBoard extends JTable {
	
	private GameControllerInterface gameController;
	private GameBoardModel gameBoardModel;
	private DefaultGameBoardCellRenderer gameBoardCellRenderer;
	
	public GameBoard(GameControllerInterface gameController) {
		this.init(gameController);
	}
	
	private void init(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.defineProperties();
		this.initComponents();
		this.addComponents();
	}

	private void defineProperties() {
		this.setRowHeight(100);
		this.setOpaque(false);
		this.setGridColor(new Color(170, 227, 250));
		this.addMouseListener(new GameBoardMouseListener(this, this.gameController));
	}

	private void initComponents() {
		this.gameBoardModel 		= new GameBoardModel(this.gameController);
		this.gameBoardCellRenderer  = new DefaultGameBoardCellRenderer();
	}

	private void addComponents() {
		this.setModel(this.gameBoardModel);
		this.setDefaultRenderer(Object.class, this.gameBoardCellRenderer);
	}
	
}
