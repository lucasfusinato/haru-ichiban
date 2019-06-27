package game.view;

import java.awt.BorderLayout;

import game.controller.GameControllerInterface;
import game.view.gardener.AbstractGardenerPanel;
import game.view.gardener.RedGardenerPanel;
import game.view.gardener.YellowGardenerPanel;

@SuppressWarnings("serial")
public class GamePanel extends AbstractGamePanel {

	public GamePanel(GameControllerInterface gameController, GameFrame gameFrame) {
		super(gameController, gameFrame);
	}

	@Override
	protected void addComponents() {
		setLayout(new BorderLayout());
		add(getRedGardenerPanel(), BorderLayout.LINE_START);
		add(getGameComponentsPanel(), BorderLayout.CENTER);
		add(getYellowGardenerPanel(), BorderLayout.LINE_END);
	}

	@Override
	protected AbstractGameComponentsPanel createGameComponentsPanel() {
		return new GameComponentsPanel(getGameController());
	}

	@Override
	protected AbstractGardenerPanel createRedGardenerPanel() {
		return new RedGardenerPanel(getGameController());
	}

	@Override
	protected AbstractGardenerPanel createYellowGardenerPanel() {
		return new YellowGardenerPanel(getGameController());
	}

}
