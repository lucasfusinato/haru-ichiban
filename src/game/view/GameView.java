package game.view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameView extends JInternalFrame {
	
	private GameControllerInterface gameController;
	private JPanel gamePanel;

	public GameView(GameControllerInterface gameController) {
		this.init(gameController);
	}
	
	private void init(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.setTitle(this.gameController.getGameTitle());
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.gamePanel = new GamePanel(this.gameController);
	}

	private void addComponents() {
		this.setContentPane(this.gamePanel);
	}

}
