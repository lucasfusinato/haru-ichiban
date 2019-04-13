package core.view;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import core.controller.MainControllerInterface;
import core.controller.MainMenuOption;
import core.view.listeners.MainMenuActionListener;

@SuppressWarnings("serial")
public class MainMenuFrame extends JInternalFrame {

	private MainControllerInterface mainController;
	private JPanel panelMain;
	private HashMap<MainMenuOption, JButton> menuOptions;
	
	public MainMenuFrame(MainControllerInterface mainController) {
		this.init(mainController);
	}
	
	private void init(MainControllerInterface mainController) {
		this.defineProperties(mainController);
		this.initComponents();
		this.addComponents();
	}

	private void defineProperties(MainControllerInterface mainController) {
		this.mainController = mainController;
		this.menuOptions = new HashMap<>();
	}

	private void initComponents() {
		this.panelMain = new JPanel();
		for(MainMenuOption mainMenuOption : MainMenuOption.values()) {
			this.menuOptions.put(mainMenuOption, this.createButton(mainMenuOption));
		}
	}

	private void addComponents() {
		this.setContentPane();
		for(MainMenuOption mainMenuOption : MainMenuOption.values()) {
			this.panelMain.add(this.menuOptions.get(mainMenuOption));
		}
	}

	private JButton createButton(MainMenuOption mainMenuOption) {
		JButton button = new JButton(mainMenuOption.getOption());
		button.addActionListener(new MainMenuActionListener(this.mainController, mainMenuOption));
		return button;
	}
	
	public void setContentPane() {
		this.panelMain.setLayout(new GridLayout(this.menuOptions.size(), 1));
		this.setContentPane(this.panelMain);
	}
	
}
