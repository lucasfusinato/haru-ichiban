package game.view.scoringtrack;

import java.awt.Graphics;
import java.awt.GridLayout;
//import java.awt.Image;
//
//import javax.swing.ImageIcon;
//
//import utils.view.ResizedImageIconFactory;

@SuppressWarnings("serial")
public class ScoringTrackPanel extends AbstractScoreboardPanel {
	
	private ScoringTrackTable scoringTrack;
	
	public ScoringTrackPanel() {
		super();
		this.init();
	}

	private void init() {
		setOpaque(false);
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.scoringTrack = new ScoringTrackTable();
	}

	private void addComponents() {
		this.setLayout(new GridLayout());
		this.add(this.scoringTrack);
	}

	@Override
	public void updateScore(int score1, int score2) {
		this.scoringTrack.refreshTrack(score1, score2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(createBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
	}
	
//	private Image createBackgroundImage() {
//		ImageIcon imageIcon = ResizedImageIconFactory.create(getBackgroundImagePath(), this.getWidth(), this.getHeight());
//		if(imageIcon != null) {
//			return imageIcon.getImage();
//		} else {
//			return null;
//		}
//	}
	
//	private String getBackgroundImagePath() {
//		return "images/scoring-track-background.png";
//	}

}
