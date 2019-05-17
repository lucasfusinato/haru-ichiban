package game.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import core.view.MainFrame;
import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	public static final int WIDTH = 1100;
	public static final int HEIGHT = 600;
	private JInternalFrame gameInternalFrame;
	private GameControllerInterface gameController;
	
	public GameFrame(GameControllerInterface gameController) {
		this.gameController = gameController;
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Haru ichiban");
		JDesktopPane desktopPane = new JDesktopPane();
		gameInternalFrame = new GameInternalFrame(this.gameController);
		gameInternalFrame.setBorder(null);
		((BasicInternalFrameUI) gameInternalFrame.getUI()).setNorthPane(null);
		desktopPane.add(gameInternalFrame);
		setContentPane(desktopPane);
		try {
			gameInternalFrame.setSelected(true);
			gameInternalFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosing(WindowEvent e) {
	                setVisible(false);
	        		MainFrame.getInstance().setVisible(true);
	            }
		});
	}
	
	@Override
	public void setVisible(boolean b) {
		gameInternalFrame.setVisible(b);
		super.setVisible(b);
	}
	
}
