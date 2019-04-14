package game.view;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class FlowerSelectionBoard extends JTable {
	
	private FlowerSelectionBoardModel gameBoardModel;
	private DefaultGameBoardCellRenderer gameBoardCellRenderer;
	
	public FlowerSelectionBoard(FlowerSelectionBoardModel gameBoardModel) {
		this.init(gameBoardModel);
	}
	
	private void init(FlowerSelectionBoardModel gameBoardModel) {
		this.defineProperties();
		this.initComponents(gameBoardModel);
		this.addComponents();
	}

	private void defineProperties() {
		this.setRowHeight(100);
		this.setOpaque(false);
		this.setShowGrid(false);
	}

	private void initComponents(FlowerSelectionBoardModel gameBoardModel) {
		this.gameBoardModel 		= gameBoardModel;
		this.gameBoardCellRenderer  = new DefaultGameBoardCellRenderer();
	}

	private void addComponents() {
		this.setModel(this.gameBoardModel);
		this.setDefaultRenderer(Object.class, this.gameBoardCellRenderer);
	}
	
}
