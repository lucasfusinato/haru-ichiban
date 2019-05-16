package game.view.flowerselection;

import javax.swing.JPanel;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public abstract class AbstractFlowerSelectionPanel extends JPanel {

	private GameControllerInterface gameController;
	private AbstractFlowerSelectionTable flowerSelectionTable;

	public AbstractFlowerSelectionPanel(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.init();
	}

	private void init() {
		setOpaque(false);
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.flowerSelectionTable = createFlowerSelectionTable();
	}

	private void addComponents() {
		add(flowerSelectionTable);
	}
	
	protected abstract AbstractFlowerSelectionTable createFlowerSelectionTable();
	
	public void refreshFlowers() {
		this.flowerSelectionTable.updateUI();
	}

	protected GameControllerInterface getGameController() {
		return gameController;
	}

	protected AbstractFlowerSelectionTable getFlowerSelectionTable() {
		return flowerSelectionTable;
	}
	
}
