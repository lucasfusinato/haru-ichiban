package game.view.flowerselection.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.controller.GameControllerInterface;
import game.model.gardener.GardenerColor;
import game.view.flowerselection.FlowerSelectionTable;

public class FlowerSelectionTableMouseListener implements MouseListener {

	private FlowerSelectionTable flowerSelectionTable;
	private GameControllerInterface gameController;
	private GardenerColor gardenerColor;

	public FlowerSelectionTableMouseListener(FlowerSelectionTable flowerSelectionTable) {
		this.flowerSelectionTable 	= flowerSelectionTable;
		this.gameController 		= flowerSelectionTable.getGameController();
		this.gardenerColor 			= flowerSelectionTable.getGardenerColor();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.gameController.mouseClickedInFlower(this.gardenerColor, this.flowerSelectionTable.getSelectedRow());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.gameController.mouseExitedFlowers(this.gardenerColor);
	}

}
