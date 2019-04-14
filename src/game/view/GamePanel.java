package game.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import game.controller.GameControllerInterface;
import game.controller.GameControllerObserver;
import game.view.listeners.RedFlowerSelectionMouseListener;
import game.view.listeners.YellowFlowerSelectionMouseListener;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements GameControllerObserver {

	private GameControllerInterface gameController;
	private JPanel gameBoardPanel;
	private JTable redFlowerSelectionBoard;
	private JTable yellowFlowerSelectionBoard;
	
	public GamePanel(GameControllerInterface gameController) {
		this.init(gameController);
	}

	private void init(GameControllerInterface gameController) {
		this.gameController = gameController;
		this.setLayout(new GridBagLayout());
		this.initComponents();
		this.addComponents();
		this.gameController.attach(this);
	}

	private void initComponents() {
		this.gameBoardPanel			  	= new GameBoardPanel(this.gameController);
		this.redFlowerSelectionBoard 	= this.createRedFlowerSelectionBoard();
		this.yellowFlowerSelectionBoard = this.createYellowFlowerSelectionBoard();
	}

	private JTable createYellowFlowerSelectionBoard() {
		FlowerSelectionBoardModel tableModel = new YellowFlowerSelectionBoardModel(this.gameController);
		JTable nenufarSelectionBoard 		 = new FlowerSelectionBoard(tableModel);
		MouseListener mouseListener 		 = new YellowFlowerSelectionMouseListener(this.gameController, nenufarSelectionBoard);
		nenufarSelectionBoard.addMouseListener(mouseListener);
		return nenufarSelectionBoard;
	}

	private JTable createRedFlowerSelectionBoard() {
		FlowerSelectionBoardModel tableModel = new RedFlowerSelectionBoardModel(this.gameController);
		JTable nenufarSelectionBoard 		 = new FlowerSelectionBoard(tableModel);
		MouseListener mouseListener 		 = new RedFlowerSelectionMouseListener(this.gameController, nenufarSelectionBoard);
		nenufarSelectionBoard.addMouseListener(mouseListener);
		return nenufarSelectionBoard;
	}

	private void addComponents() {
		GridBagConstraints cons;
		Insets defaultInsets = new Insets(5, 5, 5, 5);
		
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 5;
		cons.gridheight = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		this.add(this.redFlowerSelectionBoard, cons);
		
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 5;
		cons.gridheight = 5;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		this.add(this.gameBoardPanel, cons);
		
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridwidth = 5;
		cons.gridheight = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = defaultInsets;
		this.add(this.yellowFlowerSelectionBoard, cons);
	}

	@Override
	public void gameWasBeStarted() {
		this.redFlowerSelectionBoard.updateUI();
		this.yellowFlowerSelectionBoard.updateUI();
		this.gameBoardPanel.updateUI();
	}

	@Override
	public void redFlowerWasBeSelected(int number) {
		JOptionPane.showMessageDialog(this, "Flor vermelha selecionada: " + number);
		this.redFlowerSelectionBoard.updateUI();
	}

	@Override
	public void yellowFlowerWasBeSelected(int number) {
		JOptionPane.showMessageDialog(this, "Flor amarela selecionada: " + number);
		this.yellowFlowerSelectionBoard.updateUI();
	}

	@Override
	public void updateGameBoard() {
		this.gameBoardPanel.updateUI();
	}

}
