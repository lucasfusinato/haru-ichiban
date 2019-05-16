package core.view.frames.mainmenu;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import utils.view.BackgroundPanel;

@SuppressWarnings("serial")
public class MainMenuPanel extends BackgroundPanel {

	private static final String IMAGE_PATH = "images/background.png";
	private static final int LAYOUT_ROWS = 1;
	private static final int LAYOUT_COLS = 2;
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	public MainMenuPanel() {
		super(IMAGE_PATH);
		init();
	}

	public MainMenuPanel(int width, int height) {
		super(IMAGE_PATH, width, height);
		init();
	}

	private void init() {
		setBackground(Color.RED);
		defineProperties();
		initComponents();
		addComponents();
	}

	private void defineProperties() {
		setLayout(new GridLayout(LAYOUT_ROWS, LAYOUT_COLS));
	}

	private void initComponents() {
		leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		
		rightPanel = new MainMenuActionsPanel();
		rightPanel.setOpaque(false);
	}

	private void addComponents() {
		add(leftPanel);
		add(rightPanel);
	}
	
}
