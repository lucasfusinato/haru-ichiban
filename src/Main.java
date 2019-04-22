import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import game.controller.GameController;
import game.controller.GameControllerInterface;
import game.view.GameInternalFrame;

public class Main implements Runnable {

	public static void main(String[] args) {
		new Thread(new Main()).start();
	}
	
	@Override
	public void run() {
		JFrame framePrincipal = new JFrame();
		framePrincipal.setSize(1000, 700);
		framePrincipal.setLocationRelativeTo(null);
		JDesktopPane desktopPane = new JDesktopPane();
		GameControllerInterface gameController = GameController.getInstance();
		gameController.startGame();
		JInternalFrame gameInternalFrame = new GameInternalFrame(gameController);
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
	}

}
