package game.view;

import javax.swing.JInternalFrame;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameInternalFrame extends JInternalFrame {

	public GameInternalFrame(GameControllerInterface gameController) {
		this.setContentPane(new GamePanel(gameController));
	}

}
