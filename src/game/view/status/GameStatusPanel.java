package game.view.status;

import java.awt.GridLayout;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameStatusPanel extends AbstractGameStatusPanel {

	private JLabel statusLabel;

	public GameStatusPanel() {
		this.init();
	}
	
	private void init() {
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.statusLabel = new JLabel();
	}

	private void addComponents() {
		this.setLayout(new GridLayout());
		this.add(this.statusLabel);
	}

	@Override
	public void setGameStatus(String status) {
		this.statusLabel.setText(status);
	}

}
