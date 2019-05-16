package core.view.frames;

import javax.swing.JInternalFrame;

import game.controller.GameControllerInterface;
import game.view.AbstractGamePanel;
import game.view.GamePanel;

@SuppressWarnings("serial")
public class GameInternalFrame extends JInternalFrame {

	private AbstractGamePanel gamePanel;
	
	public void openGame(GameControllerInterface gameController) {
		gamePanel = new GamePanel(gameController);
		setContentPane(gamePanel);
		gamePanel.setVisible(true);
	}
	
}
