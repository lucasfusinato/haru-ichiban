package core.view;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import core.controller.MainController;
import core.controller.MainControllerInterface;
import core.controller.MainControllerObserver;

@SuppressWarnings("serial")
public class MainView extends JFrame implements MainControllerObserver {

	private MainControllerInterface mainController;
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
		this.setTitle("Main View");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.defineController();
	}

	private void defineController() {
		this.mainController = MainController.getInstance();
		this.mainController.attach(this);
	}

	private void initComponents() {
		this.desktopPane = new MainDesktopPane();
	}
	
	private void addComponents() {
		this.setContentPane(this.desktopPane);
	}
	
}
