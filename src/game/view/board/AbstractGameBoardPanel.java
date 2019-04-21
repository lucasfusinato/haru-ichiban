package game.view.board;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.tools.Tool;

import game.controller.GameControllerInterface;
import utils.view.ImageIconFactory;
import utils.view.ResizedImageIconFactory;

@SuppressWarnings("serial")
public abstract class AbstractGameBoardPanel extends JPanel {

	private GameControllerInterface gameController;
	private JTable gameBoard;
	private Image backgroundImage;

	public AbstractGameBoardPanel(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.init();
	}

	public void refreshBoard() {
		this.gameBoard.updateUI();
	}

	public void updateMouseImage(String statusImage) {
		Cursor cursor = null;
		if(statusImage != null) {
			Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
			Image cursorImage = ImageIconFactory.create(statusImage).getImage();
			cursor = defaultToolkit.createCustomCursor(cursorImage, new Point(1, 1), "custom cursor"); 
		} else {
			cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		}
		this.setCursor(cursor);
	}

	private void init() {
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		this.gameBoard 		 = this.createGameBoard();
		this.backgroundImage = this.createBackgroundImage();
	}

	private void addComponents() {
		this.add(this.gameBoard);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(this.backgroundImage != null) {
			g.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		super.paintComponent(g);
	}
	
	private Image createBackgroundImage() {
		ImageIcon imageIcon = ResizedImageIconFactory.create(this.getBackgroundImagePath(), this.getWidth(), this.getHeight());
		if(imageIcon != null) {
			return imageIcon.getImage();
		} else {
			return null;
		}
	}

	protected abstract JTable createGameBoard();
	protected abstract String getBackgroundImagePath();
	
	protected GameControllerInterface getGameController() {
		return gameController;
	}

	protected JTable getGameBoard() {
		return gameBoard;
	}

	protected Image getBackgroundImage() {
		return backgroundImage;
	}

}
