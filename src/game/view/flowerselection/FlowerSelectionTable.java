package game.view.flowerselection;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import game.controller.GameControllerInterface;
import game.model.gardener.GardenerColor;
import game.view.flowerselection.listener.FlowerSelectionTableMouseListener;
import game.view.flowerselection.listener.FlowerSelectionTableMouseMotionListener;
import utils.view.DefaultTableIconCellRenderer;

@SuppressWarnings("serial")
public class FlowerSelectionTable extends JTable {

	private final int SQUARE_SIZE = 100;
	private GameControllerInterface gameController;
	private GardenerColor gardenerColor;
	private TableCellRenderer cellRenderer;
	private TableModel model;
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;

	public FlowerSelectionTable(GameControllerInterface gameController, GardenerColor gardenerColor) {
		this.gameController = gameController;
		this.gardenerColor = gardenerColor;
		this.init();
	}

	private void init() {
		this.setRowHeight(this.SQUARE_SIZE);
		this.setOpaque(false);
		this.setShowGrid(false);
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.cellRenderer 		 = new DefaultTableIconCellRenderer(this.SQUARE_SIZE);
		this.model 				 = new FlowerSelectionTableModel(this.gameController, this.gardenerColor);
		this.mouseListener		 = new FlowerSelectionTableMouseListener(this);
		this.mouseMotionListener = new FlowerSelectionTableMouseMotionListener(this);
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

	public GardenerColor getGardenerColor() {
		return gardenerColor;
	}
	
}
