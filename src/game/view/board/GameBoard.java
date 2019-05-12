package game.view.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import game.controller.GameControllerInterface;
import game.view.board.listener.GameBoardMouseListener;
import game.view.board.listener.GameBoardMouseMotionListener;
import utils.view.BlackTransparentColor;
import utils.view.DefaultTableIconCellRenderer;

@SuppressWarnings("serial")
public class GameBoard extends JTable {
	
	private final int SQUARE_SIZE;
	private GameControllerInterface gameController;
	private TableModel model;
	private TableCellRenderer renderer;
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	
	public GameBoard(GameControllerInterface gameController) {
		this.gameController = gameController;
		SQUARE_SIZE = calculateSquareSize();
		init();
	}

	private void init() {
		defineProperties();
		initComponents();
		addComponents();
	}

	private void defineProperties() {
		setOpaque(false);
		setRowHeight(this.SQUARE_SIZE);
		setBackground(new BlackTransparentColor());
		Color gridColor = new Color(70, 186, 224);
		setBorder(BorderFactory.createLineBorder(gridColor));
		setGridColor(gridColor);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	private void initComponents() {
		model 				= new GameBoardModel(gameController);
		renderer 			= new DefaultTableIconCellRenderer(SQUARE_SIZE);
		mouseListener 		= new GameBoardMouseListener(gameController);
		mouseMotionListener = new GameBoardMouseMotionListener(this, gameController);
	}

	private void addComponents() {
		setModel(model);
		setDefaultRenderer(Object.class, renderer);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseMotionListener);
	}
	
	private int calculateSquareSize() {
		final int maximo = 500;
		return (int) (maximo / gameController.getBoardRowCount());
	}

}
