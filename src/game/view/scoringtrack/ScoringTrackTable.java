package game.view.scoringtrack;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import utils.view.DefaultTableIconCellRenderer;

@SuppressWarnings("serial")
public class ScoringTrackTable extends JTable {

	private final int SQUARE_SIZE = 50;
	private final int CENTRAL_COLUMN_SIZE = (int) (SQUARE_SIZE * 1.5);
	private final int CENTRAL_COLUMN = 4;
	private int[] points = {1, 2, 3, 4, 5, 4, 3, 2, 1};
	private TableCellRenderer cellRenderer;
	private TableModel model;
	private int redScore;
	private int yellowScore;
	
	private class ScoringTrackTableModel extends AbstractTableModel {
		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			if(columnIndex == CENTRAL_COLUMN && redScore == points[columnIndex] && yellowScore == points[columnIndex]) {
				return "images/rock-red-yellow.png";
			}
			else if(columnIndex <= CENTRAL_COLUMN && redScore == points[columnIndex]) {
				return "images/rock-red.png";
			}
			else if(columnIndex >= CENTRAL_COLUMN && yellowScore == points[columnIndex]) {
				return "images/rock-yellow.png";
			}
			else {
				return "images/rock-"+points[columnIndex]+".png";
			}
		}
		@Override
		public int getRowCount() {
			return 1;
		}
		@Override
		public int getColumnCount() {
			return points.length;
		}
	}
	
	private class ScoringTrackCellRenderer extends DefaultTableIconCellRenderer {
		public ScoringTrackCellRenderer() {
			super(SQUARE_SIZE);
		}
		@Override
		protected int getColumnWidth(int column) {
			return (column == CENTRAL_COLUMN) ? CENTRAL_COLUMN_SIZE : SQUARE_SIZE;
		}
	}
	
	public ScoringTrackTable() {
		init();
	}

	private void init() {
		this.setRowHeight(CENTRAL_COLUMN_SIZE);
		this.setOpaque(false);
		this.setShowGrid(false);
		this.initComponents();
		this.addComponents();
	}

	private void initComponents() {
		cellRenderer = new ScoringTrackCellRenderer();
		model = new ScoringTrackTableModel();
	}

	private void addComponents() {
		setDefaultRenderer(Object.class, cellRenderer);
		setModel(model);
	}

	public void refreshTrack(int redScore, int yellowScore) {
		this.redScore = redScore;
		this.yellowScore = yellowScore;
		this.updateUI();
	}
	
}
