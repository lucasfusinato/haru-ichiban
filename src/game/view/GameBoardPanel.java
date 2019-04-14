package game.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;

import game.controller.GameControllerInterface;
import view.factory.ResizedImageIconFactory;

@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel {

	private GameControllerInterface gameController;
	private JTable gameBoard;
	
	public GameBoardPanel(GameControllerInterface gameController) {
		this.init(gameController);
	}

	private void init(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.gameBoard = new GameBoard(this.gameController);
	}

	private void addComponents() {
		this.add(this.gameBoard);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Image image = ResizedImageIconFactory.getInstance().create("images/background.jpg", this.getWidth(), this.getHeight()).getImage();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		super.paintComponents(g);
	}

}
