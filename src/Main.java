import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import game.controller.GameController;
import game.controller.GameControllerInterface;
import game.view.GameInternalFrame;
import game.view.ViewUtils;

public class Main implements Runnable {

	public static void main(String[] args) {
		new Thread(new Main()).start();
	}
	
	@Override
	public void run() {
		ViewUtils.loadSources();
		JFrame framePrincipal = new JFrame();
		framePrincipal.setResizable(false);
		framePrincipal.setSize(1100, 600);
		framePrincipal.setLocationRelativeTo(null);
		JDesktopPane desktopPane = new JDesktopPane();
		GameControllerInterface gameController = GameController.getInstance();
		gameController.startGame("Lucas", "João", 2);
		JInternalFrame gameInternalFrame = new GameInternalFrame(gameController);
		gameInternalFrame.setBorder(null);
		((BasicInternalFrameUI) gameInternalFrame.getUI()).setNorthPane(null);
		desktopPane.add(gameInternalFrame);
		try {
			gameInternalFrame.setSelected(true);
			gameInternalFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		gameInternalFrame.setVisible(true);
		framePrincipal.setContentPane(desktopPane);
		framePrincipal.setVisible(true);
		framePrincipal.setTitle("Haru ichiban");
	}

}
