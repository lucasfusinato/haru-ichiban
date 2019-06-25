package game.view.gardener;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import game.controller.GameControllerInterface;
import game.view.flowerselection.YellowFlowerSelectionPanel;
import game.view.flowerwithdraw.YellowFlowerWithdrawDialog;
import game.view.gardener.listener.YellowCroakActionListener;

@SuppressWarnings("serial")
public class YellowGardenerPanel extends AbstractGardenerPanel {

	public YellowGardenerPanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected Component getLeftComponent() {
		return createFlowerSelectionAndCroakButtonPanel();
	}

	@Override
	protected Component getRightComponent() {
		return getGardenerImageLabel();
	}

	@Override
	protected YellowFlowerSelectionPanel createFlowerSelectionPanel() {
		return new YellowFlowerSelectionPanel(getGameController());
	}
	
	@Override
	protected String getGardenerImagePath() {
		return "images/yellow-gardener.png";
	}
	
	@Override
	protected Border createPanelBorder() {
		return BorderFactory.createEmptyBorder(0, 20, 0, 0);
	}

	@Override
	protected YellowFlowerWithdrawDialog createFlowerWithdrawDialog() {
		return new YellowFlowerWithdrawDialog(getGameController());
	}

	@Override
	protected ActionListener getCroakActionListener() {
		return new YellowCroakActionListener(getGameController());
	}

}
