package game.view.scoringtrack;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractScoringTrackPanel extends JPanel {

	public abstract void updateScore(int score1, int score2);

}