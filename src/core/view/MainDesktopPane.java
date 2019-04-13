package core.view;

import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import core.controller.MainControllerInterface;

@SuppressWarnings("serial")
public class MainDesktopPane extends JDesktopPane {

	private MainControllerInterface mainController;
	private JInternalFrame mainMenuFrame;
	
	public MainDesktopPane(MainControllerInterface mainController) {
		this.init(mainController);
	}

	private void init(MainControllerInterface mainController) {
		this.mainController = mainController;
		this.initComponents();
		this.addComponents();
		this.openStartFrame();
	}

	private void initComponents() {
		this.mainMenuFrame = new MainMenuFrame(this.mainController);
	}
	
	private void addComponents() {
		this.add(this.mainMenuFrame);
	}

	private void openStartFrame() {
		JInternalFrame startFrame = this.mainMenuFrame;
		try {
			startFrame.setSelected(true);
			startFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		startFrame.setVisible(true);
	}

}
