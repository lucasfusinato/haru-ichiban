package game.view.itemselection;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.view.MainUtils;
import game.controller.GameControllerInterface;
import game.view.itemselection.strategy.ItemSelectionStrategy;

@SuppressWarnings("serial")
public class AbstractItemSelectionPanel extends JPanel {

	private GameControllerInterface gameController;
	private ItemSelectionStrategy strategy;
	
	public AbstractItemSelectionPanel(GameControllerInterface gameController, ItemSelectionStrategy strategy) {
		this.gameController = gameController;
		this.strategy = strategy;
		init();
	}

	private void init() {
		setOpaque(false);
		adicionarItems();
	}
	
	public void refreshItems() {
		removeAll();
		adicionarItems();
		updateUI();
		repaint();
	}

	private void adicionarItems() {
		setLayout(new GridBagLayout());
		int i = 0;
		for(Integer item : strategy.getItens(gameController)) {
			add(criarItem(item), criarConstraints(i++));
		}
	}

	private JButton criarItem(Integer item) {
		JButton button = new JButton(strategy.getDescricaoItem(gameController, item));
		button.setPreferredSize(new Dimension(100, 25));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					strategy.selecionarItem(gameController, item);
				} catch (Exception e1) {
					MainUtils.catchException(e1);
				}
			}
		});
		return button;
	}

	private GridBagConstraints criarConstraints(int i) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = i;
		cons.insets = new Insets(0, 10, 10, 10);
		return cons;
	}

	public void blockItems(boolean b) {
		setVisible(!b);
	}
	
}
