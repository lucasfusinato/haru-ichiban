package game.view;

import javax.swing.JPanel;

import game.controller.GameControllerInterface;
import game.view.board.AbstractGameBoardPanel;
import game.view.scoringtrack.AbstractScoreboardPanel;
import game.view.status.AbstractGameStatusPanel;

@SuppressWarnings("serial")
public abstract class AbstractGameComponentsPanel extends JPanel {
	
	private GameControllerInterface gameController;
	private AbstractGameBoardPanel gameBoardPanel;
	private AbstractGameStatusPanel gameStatusPanel;
	private AbstractScoreboardPanel scoreboardPanel;
	
	public AbstractGameComponentsPanel(GameControllerInterface gameController) {
		this.gameController = gameController;
		init();
	}
	
	private void init() {
		initComponents();
		addComponents();
	}

	public void initComponents() {
		this.gameBoardPanel 	 = this.createGameBoardPanel();
		this.gameStatusPanel 	 = this.createGameStatusPanel();
		this.scoreboardPanel 	 = this.createScoringTrackPanel();
	}
	
	protected abstract void addComponents();
	protected abstract AbstractGameBoardPanel 	createGameBoardPanel();
	protected abstract AbstractGameStatusPanel 	createGameStatusPanel();
	protected abstract AbstractScoreboardPanel 	createScoringTrackPanel();
	
	protected GameControllerInterface getGameController() {
		return gameController;
	}
	
	protected AbstractGameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}
	
	protected AbstractGameStatusPanel getGameStatusPanel() {
		return gameStatusPanel;
	}
	
	protected AbstractScoreboardPanel getScoreboardPanel() {
		return scoreboardPanel;
	}
	
	public void updateMouseImage(String mouseImage) {
		gameBoardPanel.updateMouseImage(mouseImage);
	}
	
	public void updateStatus(String status) {
		gameStatusPanel.setGameStatus(status);
	}
	
	public void updateBoard() {
		gameBoardPanel.refreshBoard();
	}
	
}
