package game.view.flowerselection;

import javax.swing.JPanel;

import game.controller.GameControllerInterface;
import game.model.gardener.GardenerColor;

@SuppressWarnings("serial")
public abstract class AbstractFlowerSelectionPanel extends JPanel {

	private GameControllerInterface gameController;
	private FlowerSelectionTable flowerSelectionTable;

	public AbstractFlowerSelectionPanel(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.init();
	}

	private void init() {
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.flowerSelectionTable = new FlowerSelectionTable(this.gameController, this.getFlowerColor());
	}

	private void addComponents() {
		this.add(this.flowerSelectionTable);
	}
	
	protected abstract GardenerColor getFlowerColor();

	public void refreshFlowers() {
		this.flowerSelectionTable.updateUI();
	}

	protected GameControllerInterface getGameController() {
		return gameController;
	}

	protected FlowerSelectionTable getFlowerSelectionTable() {
		return flowerSelectionTable;
	}
	
}
