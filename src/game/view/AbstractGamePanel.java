package game.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.controller.GameControllerInterface;
import game.controller.GameControllerObserver;
import game.view.board.AbstractGameBoardPanel;
import game.view.flowerselection.AbstractFlowerSelectionPanel;
import game.view.scoringtrack.AbstractScoringTrackPanel;
import game.view.status.AbstractGameStatusPanel;

/**
 * Classe abstrata que define propriedades padrão do painel principal do jogo.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 */
@SuppressWarnings("serial")
public abstract class AbstractGamePanel extends JPanel implements GameControllerObserver {

	private GameControllerInterface gameController;
	private AbstractGameBoardPanel gameBoardPanel;
	private AbstractGameStatusPanel gameStatusPanel;
	private AbstractScoringTrackPanel scoringTrackPanel;
	private AbstractFlowerSelectionPanel redFlowerSelectionPanel;
	private AbstractFlowerSelectionPanel yellowFlowerSelectionPanel;

	public AbstractGamePanel(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.init();
		this.gameController.attach(this);
	}

	private void init() {
		this.initComponents();
		this.addComponents();
		this.gameStatusHasBeenUpdated(this.gameController.getGameStatus());
		this.scoringHasBeenUpdated(2, 3);
	}

	private void initComponents() {
		this.gameBoardPanel 			= this.createGameBoardPanel();
		this.gameStatusPanel 			= this.createGameStatusPanel();
		this.scoringTrackPanel 			= this.createScoringTrackPanel();
		this.redFlowerSelectionPanel 	= this.createRedFlowerSelectionPanel();
		this.yellowFlowerSelectionPanel	= this.createYellowFlowerSelectionPanel();
	}

	protected abstract void 						addComponents();
	protected abstract AbstractGameBoardPanel 		createGameBoardPanel();
	protected abstract AbstractGameStatusPanel 		createGameStatusPanel();
	protected abstract AbstractScoringTrackPanel 	createScoringTrackPanel();
	protected abstract AbstractFlowerSelectionPanel	createRedFlowerSelectionPanel();
	protected abstract AbstractFlowerSelectionPanel	createYellowFlowerSelectionPanel();

	protected GameControllerInterface getGameController() {
		return gameController;
	}

	protected AbstractGameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}

	protected AbstractGameStatusPanel getGameStatusPanel() {
		return gameStatusPanel;
	}

	protected AbstractScoringTrackPanel getScoringTrackPanel() {
		return scoringTrackPanel;
	}

	protected AbstractFlowerSelectionPanel getRedFlowerSelectionPanel() {
		return redFlowerSelectionPanel;
	}

	protected AbstractFlowerSelectionPanel getYellowFlowerSelectionPanel() {
		return yellowFlowerSelectionPanel;
	}

	@Override
	public void gameHasStarted() {
		this.gameBoardPanel.refreshBoard();
		this.redFlowerSelectionPanel.refreshFlowers();
		this.yellowFlowerSelectionPanel.refreshFlowers();
	}

	@Override
	public void gameBoardHasBeenUpdated() {
		this.gameBoardPanel.refreshBoard();
	}

	@Override
	public void gameStatusHasBeenUpdated(String status) {
		this.gameBoardPanel.updateMouseImage(this.gameController.getStatusImage());
		this.gameStatusPanel.setGameStatus(status);
	}
	
	@Override
	public void scoringHasBeenUpdated(int score1, int score2) {
		this.scoringTrackPanel.updateScore(score1, score2);
	}

	@Override
	public void redFlowerHasBeenSelected(int number) {
		this.redFlowerSelectionPanel.refreshFlowers();
	}

	@Override
	public void redFlowersHasBeenUpdated() {
		this.redFlowerSelectionPanel.refreshFlowers();
	}

	@Override
	public void yellowFlowerHasBeenSelected(int number) {
		this.yellowFlowerSelectionPanel.refreshFlowers();
	}

	@Override
	public void yellowFlowersHasBeenUpdated() {
		this.yellowFlowerSelectionPanel.refreshFlowers();
	}

	@Override
	public void gameHasEnded(String name) {
		JOptionPane.showMessageDialog(this, "O jogo terminou! O vencedor é: " + name);
	}

	@Override
	public void gameHasEnded() {
		JOptionPane.showMessageDialog(this, "O jogo terminou em empate.");
	}

	@Override
	public void showInvalidMoveError(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
}
