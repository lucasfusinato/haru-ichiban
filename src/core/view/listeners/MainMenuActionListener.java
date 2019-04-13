package core.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.controller.MainControllerInterface;
import core.controller.MainMenuOption;

public class MainMenuActionListener implements ActionListener {

	private MainControllerInterface mainController;
	private MainMenuOption mainMenuOption;
	
	public MainMenuActionListener(MainControllerInterface mainController, MainMenuOption mainMenuOption) {
		this.mainController = mainController;
		this.mainMenuOption = mainMenuOption;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainController.selectMenuOption(this.mainMenuOption);
	}

}
