package game.view;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;

import game.controller.GameControllerInterface;
import game.view.board.AbstractGameBoardPanel;
import game.view.board.GameBoardPanel;
import game.view.scoringtrack.AbstractScoreboardPanel;
import game.view.scoringtrack.ScoringTrackPanel;
import game.view.status.AbstractGameStatusPanel;
import game.view.status.GameStatusPanel;
import utils.view.ResizedImageIconFactory;

@SuppressWarnings("serial")
public class GameComponentsPanel extends AbstractGameComponentsPanel {

	public GameComponentsPanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected void addComponents() {
		//Define o gerenciador de layout
		setLayout(new GridBagLayout());
		
		//Define o espaçamento padrão entre os components
		Insets defaultInsets = new Insets(5, 5, 5, 5);
		
		//Irá definir o posicionamento de cada componente
		GridBagConstraints cons;

		//Adiciona o painel de pontuação (no topo)
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 5;
		cons.gridheight = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(-10, 0, 0, 0);
		add(getScoreboardPanel(), cons);

		//Adiciona o painel do tabuleiro (no centro)
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		add(getGameBoardPanel(), cons);

		//Adiciona o painel de status (em baixo)
//		cons = new GridBagConstraints();
//		cons.gridx = 0;
//		cons.gridy = 6;
//		cons.gridwidth = 5;
//		cons.gridheight = 1;
//		cons.anchor = GridBagConstraints.CENTER;
//		cons.insets = defaultInsets;
//		add(getGameStatusPanel(), cons);
	}
	
	@Override
	protected AbstractGameBoardPanel createGameBoardPanel() {
		return new GameBoardPanel(getGameController());
	}

	@Override
	protected AbstractGameStatusPanel createGameStatusPanel() {
		return new GameStatusPanel();
	}

	@Override
	protected AbstractScoreboardPanel createScoringTrackPanel() {
		return new ScoringTrackPanel();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(createBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
	}
	
	private Image createBackgroundImage() {
		ImageIcon imageIcon = ResizedImageIconFactory.create(getBackgroundImagePath(), this.getWidth(), this.getHeight());
		if(imageIcon != null) {
			return imageIcon.getImage();
		} else {
			return null;
		}
	}

	private String getBackgroundImagePath() {
		return "images/board-background.png";
	}

}
