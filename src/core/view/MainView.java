package core.view;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import core.controller.MainController;
import core.controller.MainControllerInterface;
import core.controller.MainControllerObserver;
import game.view.GameView;

@SuppressWarnings("serial")
public class MainView extends JFrame implements MainControllerObserver {

	private MainControllerInterface mainController;
	private JMenuBar menuBar;
	private JDesktopPane desktopPane;

	public MainView() {
		this.init();
	}

	private void init() {
		this.defineProperties();
		this.initComponents();
		this.addComponents();
	}

	private void defineProperties() {
		this.defineController();
		this.setTitle(this.mainController.getSystemTitle());
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void defineController() {
		this.mainController = MainController.getInstance();
		this.mainController.attach(this);
	}

	private void initComponents() {
		this.menuBar 	 = new MainMenuBar(this.mainController);
		this.desktopPane = new MainDesktopPane(this.mainController);
	}
	
	private void addComponents() {
		this.setJMenuBar(this.menuBar);
		this.setContentPane(this.desktopPane);
	}

	@Override
	public void systemWillBeClosed() {
		JOptionPane.showMessageDialog(this, "Obrigado por jogar!");
	}

	@Override
	public void showSystemInformation() {
		JOptionPane.showMessageDialog(this, this.mainController.getSystemInformation());
	}

	@Override
	public void gameWillBeStarted() {
		JFrame gameView = new GameView();
		gameView.setVisible(true);
	}
	
}
