package utils.view;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class DefaultTableIconCellRenderer extends DefaultTableCellRenderer {

	private final Integer SQUARE_SIZE;
	
	public DefaultTableIconCellRenderer() {
		this.SQUARE_SIZE = 0;
	}
	
	public DefaultTableIconCellRenderer(Integer squareSize) {
		this.SQUARE_SIZE = squareSize;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		this.setOpaque(false);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.defineIcon(value, row, column);
		this.defineSize(table, column);
		return this;
	}

	private void defineIcon(Object value, int row, int column) {
		if(value != null) {
			int iconSize = (int) (getColumnWidth(column) * 0.9);
			ImageIcon icon = ResizedImageIconFactory.create((String) value, iconSize, iconSize);
			this.changeIcon(icon, row, column);
		} else {
			this.setIcon(null);
		}
	}

	protected void changeIcon(Icon icon, int row, int column) {
		this.setIcon(icon);
	}

	private void defineSize(JTable table, int column) {
		if(this.SQUARE_SIZE != null) {
			int columnSize = getColumnWidth(column);
			TableColumnModel tableColumnModel = table.getColumnModel();
			TableColumn columnModel = tableColumnModel.getColumn(column);
			columnModel.setWidth(columnSize);
			columnModel.setMinWidth(columnSize);
			columnModel.setMaxWidth(columnSize);
			columnModel.setPreferredWidth(columnSize);
		}
	}
	
	protected int getColumnWidth(int column) {
		return this.SQUARE_SIZE;
	}

}
