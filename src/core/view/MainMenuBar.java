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
	private JMenu titleMenu;
	private JMenu optionsMenu;
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
		this.menuOptions 	= new HashMap<>();
	}

	private void initComponents() {
		this.titleMenu = new JMenu(mainController.getSystemTitle());
		this.titleMenu.setEnabled(false);
		this.optionsMenu  = new JMenu("Opções");
		for(MainMenuOption mainMenuOption : MainMenuOption.values()) {
			this.menuOptions.put(mainMenuOption, this.createMenuItem(mainMenuOption));
		}
	}

	private void addComponents() {
		this.add(this.titleMenu);
		this.add(this.optionsMenu);
		for(MainMenuOption mainMenuOption : MainMenuOption.values()) {
			this.optionsMenu.add(this.menuOptions.get(mainMenuOption));
		}
	}

	private JMenuItem createMenuItem(MainMenuOption mainMenuOption) {
		JMenuItem menuItem = new JMenuItem(mainMenuOption.getOption());
		menuItem.addActionListener(new MainMenuActionListener(this.mainController, mainMenuOption));
		return menuItem;
	}

	public void setMenuOptionsVisible(boolean visible) {
		this.optionsMenu.setVisible(visible);
	}
	
}
