package core.view.frames.mainmenu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.controller.MainController;
import core.controller.MainControllerInterface;
import utils.ClickEnterKeyListener;

@SuppressWarnings("serial")
public class MainMenuActionsPanel extends JPanel {
	
	private static final int BUTTON_WIDTH = 130;
	private static final int BUTTON_HEIGHT = 30;
	private List<JButton> buttons;
	private MainControllerInterface mainController;

	public MainMenuActionsPanel() {
		buttons = new ArrayList<>();
		mainController = MainController.getInstance();
		init();
	}

	private void init() {
		defineProperties();
		initComponents();
		addComponents();
	}

	private void defineProperties() {
		setLayout(new GridBagLayout());
	}

	private void initComponents() {
		buttons.add(createButton("Novo Jogo", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.startNewGame();
			}
		}));
		
		buttons.add(createButton("Sobre", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.showGameInformation();
			}
		}));
		
		buttons.add(createButton("Sair", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.exitSystem();
			}
		}));
	}

	private void addComponents() {
		int y = 0;
		for(JButton button : buttons) {
			add(button, createConstraints(y++));
		}
	}

	private JButton createButton(String name, ActionListener actionListener) {
		JButton button = new JButton();
		button.setText(name);
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		button.addActionListener(actionListener);
		button.addKeyListener(new ClickEnterKeyListener(button));
		return button;
	}

	private GridBagConstraints createConstraints(int y) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridy = y;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.VERTICAL;
		cons.insets = new Insets(5, 0, 5, 0);
		return cons;
	}
	
}
