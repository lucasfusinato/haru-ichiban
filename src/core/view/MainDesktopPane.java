package core.view;

import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import core.controller.MainControllerInterface;
import game.view.GameView;

@SuppressWarnings("serial")
public class MainDesktopPane extends JDesktopPane {

	private MainControllerInterface mainController;
	private JInternalFrame mainMenuFrame;
	private JInternalFrame gameFrame;
	
	public MainDesktopPane(MainControllerInterface mainController) {
		this.init(mainController);
	}

	private void init(MainControllerInterface mainController) {
		this.mainController = mainController;
		this.initComponents();
		this.addComponents();
		this.openMainMenu();
	}

	private void initComponents() {
		this.mainMenuFrame = new MainMenuFrame(this.mainController);
		this.gameFrame	   = new GameView(this.mainController.getGameController());
	}
	
	private void addComponents() {
		this.add(this.mainMenuFrame);
		this.add(this.gameFrame);
	}

	public void openMainMenu() {
		this.openFrame(this.mainMenuFrame);
	}
	
	public void openGame() {
		this.openFrame(this.gameFrame);
	}
	
	protected void openFrame(JInternalFrame frame) {
		try {
			frame.setSelected(true);
			frame.setMaximum(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		frame.setVisible(true);
	}

}
