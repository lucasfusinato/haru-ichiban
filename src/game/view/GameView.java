package game.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.controller.GameController;
import game.controller.GameControllerInterface;
import game.controller.GameControllerObserver;

@SuppressWarnings("serial")
public class GameView extends JFrame implements GameControllerObserver {
	
	private GameControllerInterface gameController;
	private JPanel gamePanel;

	public GameView() {
		this.init();
	}
	
	private void init() {
		this.defineProperties();
		this.initComponents();
		this.addComponents();
	}

	private void defineProperties() {
		this.defineController();
		setTitle(this.gameController.getGameTitle());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private void initComponents() {
		this.gamePanel = new GamePanel(this.gameController);
	}

	private void addComponents() {
		this.setContentPane(this.gamePanel);
	}

	private void defineController() {
		this.gameController = GameController.getInstance();
		this.gameController.attach(this);
	}

	@Override
	public void gameWasBeStarted() {
		this.gamePanel.updateUI();
	}
	
}
