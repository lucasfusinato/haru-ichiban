package game.view.board;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import game.controller.GameControllerInterface;
import game.view.board.listener.GameBoardMouseListener;
import game.view.board.listener.GameBoardMouseMotionListener;
import utils.view.DefaultTableIconCellRenderer;

@SuppressWarnings("serial")
public class GameBoard extends JTable {
	
	private final int SQUARE_SIZE = 100;
	private GameControllerInterface gameController;
	private TableModel model;
	private TableCellRenderer renderer;
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	
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
		this.setRowHeight(this.SQUARE_SIZE);
		this.setOpaque(false);
		this.setGridColor(new Color(170, 227, 250));
	}

	private void initComponents() {
		this.model 				 = new GameBoardModel(this.gameController);
		this.renderer 			 = new DefaultTableIconCellRenderer(this.SQUARE_SIZE);
		this.mouseListener 		 = new GameBoardMouseListener(this, this.gameController);
		this.mouseMotionListener = new GameBoardMouseMotionListener(this, this.gameController);
	}

	private void addComponents() {
		this.setModel(this.model);
		this.setDefaultRenderer(Object.class, this.renderer);
		this.addMouseListener(this.mouseListener);
		this.addMouseMotionListener(this.mouseMotionListener);
	}

}
