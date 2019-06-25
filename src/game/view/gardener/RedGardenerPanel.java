package game.view.gardener;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import game.controller.GameControllerInterface;
import game.view.flowerselection.RedFlowerSelectionPanel;
import game.view.flowerwithdraw.RedFlowerWithdrawDialog;
import game.view.gardener.listener.RedCroakActionListener;

@SuppressWarnings("serial")
public class RedGardenerPanel extends AbstractGardenerPanel {

	public RedGardenerPanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected Component getLeftComponent() {
		return getGardenerImageLabel();
	}

	@Override
	protected Component getRightComponent() {
		return createFlowerSelectionAndCroakButtonPanel();
	}

	@Override
	protected RedFlowerSelectionPanel createFlowerSelectionPanel() {
		return new RedFlowerSelectionPanel(getGameController());
	}
	
	@Override
	protected String getGardenerImagePath() {
		return "images/red-gardener.png";
	}
	
	@Override
	protected Border createPanelBorder() {
		return BorderFactory.createEmptyBorder(0, 0, 0, 20);
	}

	@Override
	protected RedFlowerWithdrawDialog createFlowerWithdrawDialog() {
		return new RedFlowerWithdrawDialog(getGameController());
	}

	@Override
	protected ActionListener getCroakActionListener() {
		return new RedCroakActionListener(getGameController());
	}

}
