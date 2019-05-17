package core.view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import core.view.MainUtils;
import core.view.frames.playerselection.PlayerSelectionPanel;
import utils.ClickEnterKeyListener;
import utils.NextFocusEnterKeyListener;
import utils.view.BackgroundPanel;

@SuppressWarnings("serial")
public class PlayerSelectionInternalFrame extends JInternalFrame {

	private JPanel backgroundPanel;
	private PlayerSelectionPanel player1Selection;
	private PlayerSelectionPanel player2Selection;
	private JButton cancelButton;
	private JButton confirmButton;
	
	public PlayerSelectionInternalFrame() {
		init();
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
		backgroundPanel = new BackgroundPanel(MainUtils.createImagePath("background-settings"));
		
		player1Selection = new PlayerSelectionPanel(1, "Jardineiro Vermelho");
		player1Selection.setOpaque(false);
		
		player2Selection = new PlayerSelectionPanel(2, "Jardineiro Amarelo");
		player2Selection.setOpaque(false);

		cancelButton = new JButton();
		cancelButton.setText("Cancelar");

		confirmButton = new JButton();
		confirmButton.setText("Confirmar");
	}

	private void assignEvents() {
		player1Selection.getTfdName().addKeyListener(new NextFocusEnterKeyListener(player2Selection.getTfdName()));
		player2Selection.getTfdName().addKeyListener(new NextFocusEnterKeyListener(confirmButton));
		cancelButton.addKeyListener(new ClickEnterKeyListener(cancelButton));
		confirmButton.addKeyListener(new ClickEnterKeyListener(confirmButton));
	}

	private void addComponents() {
		setContentPane(backgroundPanel);
		backgroundPanel.setLayout(new BorderLayout());
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setOpaque(false);
		selectionPanel.setLayout(new GridLayout(1, 2));
		selectionPanel.add(player1Selection);
		selectionPanel.add(player2Selection);
		backgroundPanel.add(selectionPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(cancelButton);
		buttonPanel.add(confirmButton);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addCancelButtonListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}
	
	public void addConfirmButtonListener(ActionListener listener) {
		confirmButton.addActionListener(listener);
	}

	public String getPlayer1Name() {
		return player1Selection.getTfdName().getText();
	}

	public String getPlayer2Name() {
		return player2Selection.getTfdName().getText();
	}
	
	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if(player1Selection != null) {
			player1Selection.getTfdName().requestFocus();
		}
	}

	public void setPlayer1Name(String player) {
		player1Selection.getTfdName().setText(player);
	}

	public void setPlayer2Name(String player) {
		player2Selection.getTfdName().setText(player);
	}
	
}
