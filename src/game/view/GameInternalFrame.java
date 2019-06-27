package game.view;

import javax.swing.JInternalFrame;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameInternalFrame extends JInternalFrame {

	public static final int WIDTH = 1100;
	public static final int HEIGHT = 600;

	public GameInternalFrame(GameControllerInterface gameController, GameFrame gameFrame) {
		this.setContentPane(new GamePanel(gameController, gameFrame));
	}

}
