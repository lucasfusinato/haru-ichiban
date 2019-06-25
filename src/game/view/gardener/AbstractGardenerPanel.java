package game.view.gardener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import game.controller.GameControllerInterface;
import game.view.flowerselection.AbstractFlowerSelectionPanel;
import game.view.flowerwithdraw.AbstractFlowerWithdrawDialog;
import utils.view.ResizedImageIconFactory;

@SuppressWarnings("serial")
public abstract class AbstractGardenerPanel extends JPanel {

	private GameControllerInterface gameController;
	private AbstractFlowerSelectionPanel flowerSelectionPanel;
	private JLabel gardenerImageLabel;
	private AbstractFlowerWithdrawDialog flowerWithdrawDialog;
	private JButton croakButton;
	
	public AbstractGardenerPanel(GameControllerInterface gameController) {
		this.gameController = gameController;
		init();
	}
	
	private void init() {
		setBorder(createPanelBorder());
		setOpaque(false);
		initComponents();
		addComponents();
	}

	private void initComponents() {
		this.flowerSelectionPanel = createFlowerSelectionPanel();
		this.gardenerImageLabel = createGardenerImageLabel();
		this.croakButton = createCroakButton();
		this.flowerWithdrawDialog = createFlowerWithdrawDialog();
	}

	private void addComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints cons;
		
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.anchor = GridBagConstraints.CENTER;
		add(getLeftComponent(), cons);

		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.anchor = GridBagConstraints.CENTER;
		add(getRightComponent(), cons);	
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Image image1 = new ImageIcon("images/tabua.png").getImage();
		int wtotal = getWidth();
		int htotal = getHeight();
		
		int w = image1.getWidth(null);
		int h = image1.getHeight(null);
		for (int i = 0; i*w < wtotal; i++)
			for (int j = 0; j*h < htotal; j++)
				g.drawImage(image1, i*w, j*h, null);
	}

	
	protected abstract Component getLeftComponent();
	protected abstract Component getRightComponent();
	protected abstract AbstractFlowerSelectionPanel createFlowerSelectionPanel();
	protected abstract String getGardenerImagePath();
	protected abstract Border createPanelBorder();
	protected abstract AbstractFlowerWithdrawDialog createFlowerWithdrawDialog();

	private JLabel createGardenerImageLabel() {
		return new JLabel(ResizedImageIconFactory.create(getGardenerImagePath(), 120, 120));
	}
	
	private JButton createCroakButton() {
		JButton button = new JButton("Coachar");
		button.setVisible(false);
		button.setPreferredSize(new Dimension(100, 25));
		button.addActionListener(getCroakActionListener());
		return button;
	}
	
	protected abstract ActionListener getCroakActionListener();

	protected AbstractFlowerSelectionPanel getFlowerSelectionPanel() {
		return flowerSelectionPanel;
	}

	protected Component createFlowerSelectionAndCroakButtonPanel() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints cons;
		cons = new GridBagConstraints();
		cons.gridy = 0;
		panel.add(flowerSelectionPanel, cons);

		cons = new GridBagConstraints();
		cons.gridy = 1;
		panel.add(croakButton, cons);
		
		return panel;
	}
	
	protected JLabel getGardenerImageLabel() {
		return gardenerImageLabel;
	}
	
	protected GameControllerInterface getGameController() {
		return gameController;
	}

	public void refreshFlowers() {
		flowerSelectionPanel.refreshFlowers();
	}
	
	public void openFlowerWithdrawPanel() {
		flowerWithdrawDialog.refreshFlowers();
		flowerWithdrawDialog.setVisible(true);
	}

	public void refreshWithdrawFlowers() {
		flowerWithdrawDialog.refreshFlowers();
	}

	public void closeFlowerWithdrawPanel() {
		flowerWithdrawDialog.setVisible(false);
	}

	public void showCroakButton() {
		croakButton.setVisible(true);
	}
	
	public void hideCroakButton() {
		croakButton.setVisible(false);
	}
	
}
