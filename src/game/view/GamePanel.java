package game.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import game.controller.GameControllerInterface;
import game.view.board.AbstractGameBoardPanel;
import game.view.board.GameBoardPanel;
import game.view.flowerselection.AbstractFlowerSelectionPanel;
import game.view.flowerselection.RedFlowerSelectionPanel;
import game.view.flowerselection.YellowFlowerSelectionPanel;
import game.view.scoringtrack.AbstractScoringTrackPanel;
import game.view.scoringtrack.ScoringTrackPanel;
import game.view.status.AbstractGameStatusPanel;
import game.view.status.GameStatusPanel;

@SuppressWarnings("serial")
public class GamePanel extends AbstractGamePanel {

	public GamePanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected void addComponents() {
		//Define o gerenciador de layout
		this.setLayout(new GridBagLayout());
		
		//Define o espaçamento padrão entre os components
		Insets defaultInsets = new Insets(5, 5, 5, 5);
		
		//Irá definir o posicionamento de cada componente
		GridBagConstraints cons;

		//Adiciona o painel com a trilha pontuação (no topo)
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridwidth = 5;
		cons.gridheight = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		this.add(this.getScoringTrackPanel(), cons);

		//Adiciona o painel com o tabuleiro do jogo (no centro)
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		this.add(this.getGameBoardPanel(), cons);

		//Adiciona o painel de status do jogo (em baixo)
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 6;
		cons.gridwidth = 5;
		cons.gridheight = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		this.add(this.getGameStatusPanel(), cons);

		//Adiciona o painel de seleção de flores vermelhas (no lado esquerdo)
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth = 1;
		cons.gridheight = 5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		this.add(this.getRedFlowerSelectionPanel(), cons);
		
		//Adiciona o painel de seleção de flores amarelas (no lado direito)
		cons = new GridBagConstraints();
		cons.gridx = 6;
		cons.gridy = 2;
		cons.gridwidth = 1;
		cons.gridheight = 5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		this.add(this.getYellowFlowerSelectionPanel(), cons);
	}

	@Override
	protected AbstractGameBoardPanel createGameBoardPanel() {
		return new GameBoardPanel(this.getGameController());
	}

	@Override
	protected AbstractGameStatusPanel createGameStatusPanel() {
		return new GameStatusPanel();
	}

	@Override
	protected AbstractScoringTrackPanel createScoringTrackPanel() {
		return new ScoringTrackPanel();
	}

	@Override
	protected AbstractFlowerSelectionPanel createRedFlowerSelectionPanel() {
		return new RedFlowerSelectionPanel(this.getGameController());
	}

	@Override
	protected AbstractFlowerSelectionPanel createYellowFlowerSelectionPanel() {
		return new YellowFlowerSelectionPanel(this.getGameController());
	}

}
