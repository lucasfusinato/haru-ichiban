package game.view.flowerwithdraw;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public abstract class AbstractFlowerWithdrawDialog extends JDialog {
	
	private static final int MAXIMO_COLUNAS = 8;
	private final int WIDTH;
	private final int HEIGHT;
	protected GameControllerInterface gameController;
	private JPanel flowersPanel;
	private JTable flowersTable;

	public AbstractFlowerWithdrawDialog(GameControllerInterface gameController) {
		this.gameController = gameController;
		WIDTH = calculateWidth();
		HEIGHT = calculateHeight();
		init();
	}

	private void init() {
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Saque de flor");
		setAlwaysOnTop(true);
		setResizable(false);
		addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
			}
			@Override
			public void windowLostFocus(WindowEvent e) {
				try {
					if(SwingUtilities.isDescendingFrom(e.getOppositeWindow(), AbstractFlowerWithdrawDialog.this)) {
						return;
					}
				} catch(Exception ex) {					
				}
				AbstractFlowerWithdrawDialog.this.setVisible(false);
			}
        });
		initComponents();
		addComponents();
	}

	private void initComponents() {
		flowersPanel = createFlowersPanel();
		flowersTable = createFlowersTable();
	}

	private void addComponents() {
		flowersPanel.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.anchor = GridBagConstraints.CENTER;
		flowersPanel.add(flowersTable, cons);
		setContentPane(flowersPanel);
	}

	private JPanel createFlowersPanel() {
		JPanel panel = new JPanel() { 
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
		};
		panel.setSize(getSize());
		return panel;
	}

	public void refreshFlowers() {
		flowersTable.updateUI();
	}

	protected abstract JTable createFlowersTable();

	private int calculateWidth() {
		return (MAXIMO_COLUNAS + 1) * 100;
	}

	private int calculateHeight() {
		int height = (calculateRows() + 1) * 100;
		return height > 100 ? height : 200;
	}
	
	private int calculateRows() {
		if(gameController.getRoundQuantity() == MAXIMO_COLUNAS) {
			return 1;
		}
		return 1 + ((int) Math.ceil(gameController.getRoundQuantity() / MAXIMO_COLUNAS));
	}

	protected int getRowIndex(int rowIndex, int columnIndex) {
		return getColumnCount() * rowIndex + columnIndex;
	}

	protected int getRowCount() {
		return calculateRows();
	}

	protected int getColumnCount() {
		if(gameController.getRoundQuantity() == MAXIMO_COLUNAS) {
			return MAXIMO_COLUNAS;
		} else if(gameController.getRoundQuantity() == 14) {
			return 7;
		} else {
			return 8;
		}
	}
	
}
