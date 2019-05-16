package game.view.status;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractGameStatusPanel extends JPanel {

	public abstract void setGameStatus(String status);

}
