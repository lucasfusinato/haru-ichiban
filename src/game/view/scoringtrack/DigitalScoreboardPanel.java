package game.view.scoringtrack;

import java.awt.GridLayout;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DigitalScoreboardPanel extends AbstractScoreboardPanel {
	
	private JLabel scoreLabel1;
	private JLabel scoreLabel2;
	
	public DigitalScoreboardPanel() {
		super();
		this.init();
	}

	private void init() {
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.scoreLabel1 = new JLabel();
		this.scoreLabel2 = new JLabel();
	}

	private void addComponents() {
		GridLayout layout = new GridLayout();
		layout.setHgap(50);
		this.setLayout(layout);
		
		this.add(this.scoreLabel1);
		this.add(this.scoreLabel2);
	}

	@Override
	public void updateScore(int score1, int score2) {
		this.scoreLabel1.setText("Jogador 1: " + score1 + " pontos");
		this.scoreLabel2.setText("Jogador 2: " + score2 + " pontos");
	}

}
