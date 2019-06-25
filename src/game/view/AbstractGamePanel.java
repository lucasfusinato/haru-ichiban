package game.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.controller.GameControllerInterface;
import game.controller.GameControllerObserver;
import game.view.gardener.AbstractGardenerPanel;

@SuppressWarnings("serial")
public abstract class AbstractGamePanel extends JPanel implements GameControllerObserver {

	private GameControllerInterface gameController;
	private AbstractGameComponentsPanel gameComponentsPanel;
	private AbstractGardenerPanel redGardenerPanel;
	private AbstractGardenerPanel yellowGardenerPanel;

	public AbstractGamePanel(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.init();
		this.gameController.attach(this);
	}

	private void init() {
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.gameComponentsPanel = this.createGameComponentsPanel();
		this.redGardenerPanel 	 = this.createRedGardenerPanel();
		this.yellowGardenerPanel = this.createYellowGardenerPanel();
	}

	protected abstract void 					addComponents();
	protected abstract AbstractGameComponentsPanel 	createGameComponentsPanel();
	protected abstract AbstractGardenerPanel	createRedGardenerPanel();
	protected abstract AbstractGardenerPanel	createYellowGardenerPanel();

	protected GameControllerInterface getGameController() {
		return gameController;
	}

	protected AbstractGameComponentsPanel getGameComponentsPanel() {
		return gameComponentsPanel;
	}

	protected AbstractGardenerPanel getRedGardenerPanel() {
		return redGardenerPanel;
	}

	protected AbstractGardenerPanel getYellowGardenerPanel() {
		return yellowGardenerPanel;
	}

	@Override
	public void updateRedFlowers() {
		this.redGardenerPanel.refreshFlowers();
	}

	@Override
	public void updateYellowFlowers() {
		this.yellowGardenerPanel.refreshFlowers();
	}

	@Override
	public void updateGardeners(String seniorGardener, String juniorGardener) {
		JOptionPane.showMessageDialog(this, "Jardineiro sênior: " + seniorGardener	+ "\n"
				  + "Jardineiro júnior: " + juniorGardener);
	}

	@Override
	public void updateStatus(String status) {
		JOptionPane.showMessageDialog(this, "Status: "+status+".");
	}

	@Override
	public void updateBoard() {
		gameComponentsPanel.updateBoard();
	}

	@Override
	public void requestRedFlowerWithdraw() {
		redGardenerPanel.openFlowerWithdrawPanel();
	}

	@Override
	public void requestYellowFlowerWithdraw() {
		yellowGardenerPanel.openFlowerWithdrawPanel();
	}

	@Override
	public void updateWithdrawRedFlowers() {
		redGardenerPanel.refreshWithdrawFlowers();
	}

	@Override
	public void updateWithdrawYellowFlowers() {
		yellowGardenerPanel.refreshWithdrawFlowers();
	}

	@Override
	public void withdrawedRedFlowers() {
		redGardenerPanel.closeFlowerWithdrawPanel();
	}

	@Override
	public void withdrawedYellowFlowers() {
		yellowGardenerPanel.closeFlowerWithdrawPanel();
	}
	
	@Override
	public void updateScore(int addedScore1, int score1, int addedScore2, int score2) {
		JOptionPane.showMessageDialog(null, "O jogador vermelho fez "+addedScore1+" pontos.");
		JOptionPane.showMessageDialog(null, "O jogador amarelo fez "+addedScore2+" pontos.");
		gameComponentsPanel.updateScore(score1, score2);
	}
	
	@Override
	public void startedRound(int round) {
		JOptionPane.showMessageDialog(null, "A rodada "+round+" irá iniciar.");
	}
	
	@Override
	public void showCroakButton() {
		redGardenerPanel.showCroakButton();
		yellowGardenerPanel.showCroakButton();
	}
	
	@Override
	public void hideCroakButton() {
		redGardenerPanel.hideCroakButton();
		yellowGardenerPanel.hideCroakButton();
	}

	@Override
	public void requestMoveRedFrog() {
		JOptionPane.showMessageDialog(null, "Selecione uma casa para o sapo vermelho.");
	}

	@Override
	public void requestMoveYellowFrog() {
		JOptionPane.showMessageDialog(null, "Selecione uma casa para o sapo amarelo.");
	}
}
