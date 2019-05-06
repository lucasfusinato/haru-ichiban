package game.view.flowerselection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import game.controller.GameControllerInterface;
import game.controller.command.FlowerMouseCommandFactory;
import game.view.flowerselection.listener.FlowerSelectionTableMouseListener;
import game.view.flowerselection.listener.FlowerSelectionTableMouseMotionListener;
import utils.view.BlackTransparentColor;
import utils.view.DefaultTableIconCellRenderer;

@SuppressWarnings("serial")
public abstract class AbstractFlowerSelectionTable extends JTable {

	private final int SQUARE_SIZE = 100;
	private GameControllerInterface gameController;
	private TableCellRenderer cellRenderer;
	private TableModel model;
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	private FlowerMouseCommandFactory mouseCommandFactory;
	private boolean vertical;

	public AbstractFlowerSelectionTable(GameControllerInterface gameController) {
		this(gameController, true);
	}

	public AbstractFlowerSelectionTable(GameControllerInterface gameController, boolean vertical) {
		this.gameController = gameController;
		this.vertical = vertical;
		this.init();
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
		Color gridColor = new Color(219, 159, 82);
		setBorder(BorderFactory.createLineBorder(gridColor));
		setGridColor(gridColor);
	}

	private void initComponents() {
		this.cellRenderer 		 = new DefaultTableIconCellRenderer(this.SQUARE_SIZE);
		this.model 				 = createTableModel();
		this.mouseCommandFactory = createMouseCommandFactory();
		this.mouseListener		 = new FlowerSelectionTableMouseListener(mouseCommandFactory, vertical);
		this.mouseMotionListener = new FlowerSelectionTableMouseMotionListener(mouseCommandFactory);
	}

	protected abstract TableModel createTableModel();

	protected abstract FlowerMouseCommandFactory createMouseCommandFactory();

	@Override
	protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
	
	private void addComponents() {
		this.setDefaultRenderer(Object.class, cellRenderer);
		this.setModel(this.model);
		this.addMouseListener(this.mouseListener);
		this.addMouseMotionListener(this.mouseMotionListener);
	}

	public GameControllerInterface getGameController() {
		return gameController;
	}
	
}
