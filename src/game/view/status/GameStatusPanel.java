package game.view.status;

import java.awt.GridLayout;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameStatusPanel extends AbstractGameStatusPanel {

	private final int DEFAULT_CHARACTERS_PER_LINE = 200;
	private int charactersPerLine;
	private JLabel statusLabel;

	public GameStatusPanel() {
		super();
		this.charactersPerLine = DEFAULT_CHARACTERS_PER_LINE;
		this.init();
	}

	public GameStatusPanel(int charactersPerLine) {
		super();
		this.charactersPerLine = charactersPerLine;
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
		status += "\n";
		status = status.replaceAll("(.{"+charactersPerLine+"})\\s+", "$1\n");
		this.statusLabel.setText(status);
	}

}
