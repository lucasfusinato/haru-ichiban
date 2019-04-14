package core.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.controller.MainController;
import core.controller.MainControllerInterface;
import core.controller.MainControllerObserver;

@SuppressWarnings("serial")
public class MainView extends JFrame implements MainControllerObserver {

	private MainControllerInterface mainController;
	private MainMenuBar menuBar;
	private MainDesktopPane desktopPane;

	public MainView() {
		this.init();
	}

	private void init() {
		this.defineProperties();
		this.initComponents();
		this.addComponents();
		this.menuBar.setMenuOptionsVisible(false);
		this.desktopPane.openMainMenu();
	}

	private void defineProperties() {
		this.defineController();
		this.setTitle(this.mainController.getSystemTitle());
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
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
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.menuBar.setMenuOptionsVisible(true);
		this.desktopPane.openGame();
	}
	
}
