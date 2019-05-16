package core.view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import game.view.ViewUtils;
import utils.ClickEnterKeyListener;
import utils.view.BackgroundPanel;

@SuppressWarnings("serial")
public class LevelSelectionInternalFrame extends JInternalFrame {

	private HashMap<Integer, JRadioButton> buttons;
	private JPanel backgroundPanel;
	private JPanel radioPanel;
	private JLabel lblLevel;
	private ButtonGroup radioGroup;
	private JButton cancelButton;
	private JButton confirmButton;
	
	public LevelSelectionInternalFrame(List<String> options) {
		buttons = new HashMap<>();
		init();
		defineOptions(options);
	}

	private void init() {
		defineProperties();
		initComponents();
		assignEvents();
		addComponents();
	}

	private void defineProperties() {
		//TODO
	}

	private void initComponents() {
		backgroundPanel = new BackgroundPanel(ViewUtils.createImagePath("background-settings"));
		
		radioPanel = new JPanel();
		radioPanel.setOpaque(false);
		radioPanel.setLayout(new GridBagLayout());
		
		lblLevel = new JLabel();
		lblLevel.setText("Selecione o nível de jogo:");
		
		radioGroup = new ButtonGroup();
		
		cancelButton = new JButton();
		cancelButton.setText("Cancelar");
		
		confirmButton = new JButton();
		confirmButton.setText("Confirmar");
	}

	private void assignEvents() {
		cancelButton.addKeyListener(new ClickEnterKeyListener(cancelButton));
		confirmButton.addKeyListener(new ClickEnterKeyListener(confirmButton));
	}

	private void addComponents() {
		setContentPane(backgroundPanel);
		backgroundPanel.setLayout(new BorderLayout());

		GridBagConstraints cons = new GridBagConstraints();
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.CENTER;
		radioPanel.add(lblLevel, cons);
		
		backgroundPanel.add(radioPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(cancelButton);
		buttonPanel.add(confirmButton);
		buttonPanel.setOpaque(false);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void defineOptions(List<String> options) {
		GridBagConstraints cons;
		JRadioButton radio;
		int i = 0;
		for(String option : options) {
			radio = new JRadioButton(option);
			buttons.put((Integer) i, radio);
			radio.setOpaque(false);
			radio.setActionCommand("" + i++);
			radioGroup.add(radio);
			
			cons = new GridBagConstraints();
			cons.gridy = i + 1;
			cons.anchor = GridBagConstraints.CENTER;
			radioPanel.add(radio, cons);
		}
	}

	public void addCancelButtonListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

	public void addConfirmButtonListener(ActionListener listener) {
		confirmButton.addActionListener(listener);
	}

	public Integer getLevel() {
		return Integer.parseInt(radioGroup.getSelection().getActionCommand());
	}

	public void setLevel(Integer level) {
		JRadioButton button = buttons.get(level);
		if(button != null) {
			button.setSelected(true);
		}
	}

}
