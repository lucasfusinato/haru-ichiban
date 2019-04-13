package core.view;

import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MainDesktopPane extends JDesktopPane {

	private JInternalFrame mainMenuFrame;
	
	public MainDesktopPane() {
		this.init();
	}

	private void init() {
		this.initComponents();
		this.addComponents();
		this.openStartFrame();
	}

	private void initComponents() {
		this.mainMenuFrame = new MainMenuFrame();
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
