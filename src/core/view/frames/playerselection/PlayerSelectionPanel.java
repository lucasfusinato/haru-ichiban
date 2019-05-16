package core.view.frames.playerselection;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.view.ImageIconFactory;

@SuppressWarnings("serial")
public class PlayerSelectionPanel extends JPanel {

	private static final int INPUT_WIDTH = 125;
	private static final int INPUT_HEIGHT = 23;
	private Integer playerNumber;
	private JLabel lblPicture;
	private JLabel lblName;
	private JTextField tfdName;
	private String legenda;
	
	public PlayerSelectionPanel(Integer playerNumber) {
		this(playerNumber, "Jogador " + playerNumber);
	}
	
	public PlayerSelectionPanel(Integer playerNumber, String legenda) {
		this.playerNumber = playerNumber;
		this.legenda = legenda;
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
		lblPicture = new JLabel();
		lblPicture.setIcon(ImageIconFactory.create("images/player-"+playerNumber+".png"));
		
		lblName = new JLabel();
		lblName.setText(legenda + ":");
		
		tfdName = new JTextField();
		tfdName.setPreferredSize(new Dimension(INPUT_WIDTH, INPUT_HEIGHT));
	}

	private void addComponents() {
		GridBagConstraints cons;
		
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(-80, 0, 20, 0);
		add(lblPicture, cons);
		
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		cons.insets = new Insets(0, 0, 5, 0);
		add(lblName, cons);

		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 2;
		add(tfdName, cons);
	}

	public JLabel getLblPicture() {
		return lblPicture;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public JTextField getTfdName() {
		return tfdName;
	}
	
}
