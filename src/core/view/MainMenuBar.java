package core.view;

import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import core.controller.MainControllerInterface;
import core.controller.MainMenuOption;
import core.view.listeners.MainMenuActionListener;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

	private MainControllerInterface mainController;
	private JMenu mainMenu;
	private HashMap<MainMenuOption, JMenuItem> menuOptions;
	
	public MainMenuBar(MainControllerInterface mainController) {
		this.init(mainController);
	}

	private void init(MainControllerInterface mainController) {
		this.defineProperties(mainController);
		this.initComponents();
		this.addComponents();
	}

	private void defineProperties(MainControllerInterface mainController) {
		this.mainController = mainController;
		this.mainMenu 		= new JMenu("Menu Principal");
		this.menuOptions 	= new HashMap<>();
	}

	private void initComponents() {
		for(MainMenuOption mainMenuOption : MainMenuOption.values()) {
			this.menuOptions.put(mainMenuOption, this.createMenuItem(mainMenuOption));
		}
	}

	private void addComponents() {
		this.add(this.mainMenu);
		for(MainMenuOption mainMenuOption : MainMenuOption.values()) {
			this.mainMenu.add(this.menuOptions.get(mainMenuOption));
		}
	}

	private JMenuItem createMenuItem(MainMenuOption mainMenuOption) {
		JMenuItem menuItem = new JMenuItem(mainMenuOption.getOption());
		menuItem.addActionListener(new MainMenuActionListener(this.mainController, mainMenuOption));
		return menuItem;
	}
	
}
