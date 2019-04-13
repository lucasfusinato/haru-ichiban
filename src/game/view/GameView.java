package view;

import javax.swing.JFrame;

import controller.AbstractGameController;
import controller.GameController;
import controller.GameControllerObserver;

@SuppressWarnings("serial")
public class GameView extends JFrame implements GameControllerObserver {
	
	private final int CELL_SIZE = 125;
	private AbstractGameController gameController;
	
	public GameView() {
		gameController = new GameController();
		GameBoardModel gameBoardModel 		  = new GameBoardModel(gameController, CELL_SIZE);
		GameBoard gameBoard 		  		  = new GameBoard(gameBoardModel, CELL_SIZE);
		init();
		gameController.attach(this);
		add(gameBoard);
	}
	
	private void init() {
		setTitle(getDefaultFrameTitle());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(calculateFrameWidth(), calculateFrameHeight());
		setLocationRelativeTo(null);
	}
	
	private String getDefaultFrameTitle() {
		return "Haru Ichiban";
	}
	
	private int calculateFrameWidth() {
		return CELL_SIZE * gameController.getRowCount();
	}
	
	private int calculateFrameHeight() {
		return CELL_SIZE * gameController.getColumnCount() + 40; //40px é o tamanho do cabeçalho do JFrame
	}

}
