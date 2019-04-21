package game.view.flowerselection.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import game.controller.GameControllerInterface;
import game.model.gardener.GardenerColor;
import game.view.flowerselection.FlowerSelectionTable;

public class FlowerSelectionTableMouseMotionListener implements MouseMotionListener {

	private FlowerSelectionTable flowerSelectionTable;
	private GameControllerInterface gameController;
	private GardenerColor gardenerColor;

	public FlowerSelectionTableMouseMotionListener(FlowerSelectionTable flowerSelectionTable) {
		this.flowerSelectionTable 	= flowerSelectionTable;
		this.gameController 		= flowerSelectionTable.getGameController();
		this.gardenerColor 			= flowerSelectionTable.getGardenerColor();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.gameController.mouseMovedToFlower(this.gardenerColor, this.getRowIndex(e));
	}

	protected int getRowIndex(MouseEvent e) {
		return this.flowerSelectionTable.rowAtPoint(e.getPoint());
	}

}
