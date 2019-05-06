package game.view.board;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
		setOpaque(false);
		setRowHeight(this.SQUARE_SIZE);
		setBackground(new BlackTransparentColor());
		Color gridColor = new Color(70, 186, 224);
		setBorder(BorderFactory.createLineBorder(gridColor));
		setGridColor(gridColor);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	private void initComponents() {
		this.model 				 = new GameBoardModel(this.gameController);
		this.renderer 			 = new DefaultTableIconCellRenderer(this.SQUARE_SIZE);
		this.mouseListener 		 = new GameBoardMouseListener(this.gameController);
		this.mouseMotionListener = new GameBoardMouseMotionListener(this, this.gameController);
	}

	private void addComponents() {
		this.setModel(this.model);
		this.setDefaultRenderer(Object.class, this.renderer);
		this.addMouseListener(this.mouseListener);
		this.addMouseMotionListener(this.mouseMotionListener);
	}

}
