package core.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import core.controller.MainController;
import core.controller.MainControllerInterface;
import core.controller.MainControllerObserver;
import core.view.frames.LevelSelectionInternalFrame;
import core.view.frames.MainMenuInternalFrame;
import core.view.frames.PlayerSelectionInternalFrame;
import core.view.listeners.CancelLevelSelectionActionListener;
import core.view.listeners.CancelPlayerSelectionActionListener;
import core.view.listeners.ConfirmLevelSelectionActionListener;
import core.view.listeners.ConfirmPlayerSelectionActionListener;
import game.controller.GameControllerInterface;
import game.view.GameFrame;
import utils.view.ConfirmPane;
import utils.view.MessagePane;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MainControllerObserver {

	private static MainFrame instance;
	public static final int WIDTH = 550;
	public static final int HEIGHT = 300;
	private MainControllerInterface mainController;
	private JDesktopPane desktop;
	private JInternalFrame mainMenuFrame;
	private PlayerSelectionInternalFrame playerSelectionFrame;
	private LevelSelectionInternalFrame levelSelectionFrame;
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	private MainFrame() {
		mainController = MainController.getInstance();
		mainController.attach(this);
		init();
	}

	private void init() {
		defineProperties();
		initComponents();
		assignEvents();
		addComponents();
	}

	private void defineProperties() {
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setUndecorated(true);
	}

	private void initComponents() {
		desktop 			 = new JDesktopPane();		
		mainMenuFrame 		 = new MainMenuInternalFrame();
		playerSelectionFrame = new PlayerSelectionInternalFrame();
		levelSelectionFrame  = new LevelSelectionInternalFrame(mainController.getLevelOptions());
	}

	private void assignEvents() {
		playerSelectionFrame.addCancelButtonListener(new CancelPlayerSelectionActionListener(this));
		playerSelectionFrame.addConfirmButtonListener(new ConfirmPlayerSelectionActionListener(this));
		
		levelSelectionFrame.addCancelButtonListener(new CancelLevelSelectionActionListener(this));
		levelSelectionFrame.addConfirmButtonListener(new ConfirmLevelSelectionActionListener(this));
	}

	private void addComponents() {
		setContentPane(desktop);
		desktop.add(mainMenuFrame);
		desktop.add(playerSelectionFrame);
		desktop.add(levelSelectionFrame);
	}

	@Override
	public void gameStarted(GameControllerInterface gameController) {
		GameFrame gameFrame = new GameFrame(gameController);
		gameFrame.setVisible(true);
		setVisible(false);
	}

	@Override
	public void requestGameInformation() {
		openInternalFrame(playerSelectionFrame);
	}

	@Override
	public void systemWillBeClosed() {
		MessagePane.show("Obrigado por jogar!");
	}

	@Override
	public void showGameInformation(String gameInformation) {
		MessagePane.show(gameInformation);
	}

	@Override
	public void updatePlayer1(String player) {
		playerSelectionFrame.setPlayer1Name(player);
	}

	@Override
	public void updatePlayer2(String player) {
		playerSelectionFrame.setPlayer2Name(player);
	}

	@Override
	public void updateLevel(Integer level) {
		levelSelectionFrame.setLevel(level);
	}

	public void confirmSelectedPlayer() {
		if(ConfirmPane.confirm("Confirma os jogadores selecionados?")) {
			mainController.definePlayer1(playerSelectionFrame.getPlayer1Name());
			mainController.definePlayer2(playerSelectionFrame.getPlayer2Name());
			openInternalFrame(levelSelectionFrame);
		}
	}

	public void confirmSelectedLevel() {
		if(ConfirmPane.confirm("Confirma o nível selecionado?")) {
			mainController.defineLevel(levelSelectionFrame.getLevel());
			try {
				mainController.startGame();
			} catch (Exception e) {
				MainUtils.catchException(e);
			}
		}
	}

	public void cancelSelectedPlayer() {
		if(ConfirmPane.confirm("Deseja cancelar a seleção de jogadores e voltar para o menu principal?")) {
			openInternalFrame(mainMenuFrame);
		}
	}

	public void cancelSelectedLevel() {
		if(ConfirmPane.confirm("Deseja cancelar a seleção de nível de jogo para o menu principal?")) {
			openInternalFrame(mainMenuFrame);
		}
	}
	
	private void openInternalFrame(JInternalFrame frame) {
		frame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		((BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
		try {
			frame.setSelected(true);
			frame.setMaximum(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setVisible(true);
	}
	
	@Override
	public void setVisible(boolean b) {
		if(mainMenuFrame != null) {
			openInternalFrame(mainMenuFrame);
		}
		super.setVisible(b);
	}
	
}
