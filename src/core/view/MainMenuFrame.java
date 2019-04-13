package core.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuFrame extends JInternalFrame {

	private JPanel panelMain;
	private JButton buttonNewGame;
	private JButton buttonAbout;
	private JButton buttonExit;
	
	public MainMenuFrame() {
		this.init();
	}
	
	private void init() {
		this.defineProperties();
		this.initComponents();
		this.addComponents();
	}

	private void defineProperties() {
		this.setTitle("Menu Principal");
	}

	private void initComponents() {
		this.initMainPanel();
		this.buttonNewGame 	= new JButton("Novo Jogo");
		this.buttonAbout 	= new JButton("Sobre");
		this.buttonExit 	= new JButton("Sair");
	}
	
	private void initMainPanel() {
		this.panelMain = new JPanel();
		this.panelMain.setLayout(new GridLayout(3, 1));
	}

	private void addComponents() {
		this.setContentPane(this.panelMain);
		this.panelMain.add(this.buttonNewGame);
		this.panelMain.add(this.buttonAbout);
		this.panelMain.add(this.buttonExit);
	}
	
}
