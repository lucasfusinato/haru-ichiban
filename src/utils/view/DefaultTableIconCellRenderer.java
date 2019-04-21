package utils.view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class DefaultTableIconCellRenderer extends DefaultTableCellRenderer {

	private final Integer SQUARE_SIZE;
	
	public DefaultTableIconCellRenderer() {
		this.SQUARE_SIZE = null;
	}
	
	public DefaultTableIconCellRenderer(Integer squareSize) {
		this.SQUARE_SIZE = squareSize;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		this.setOpaque(false);
		this.defineIcon(value);
		this.defineSize(table, column);
		return this;
	}

	private void defineIcon(Object value) {
		if(value != null) {
			ImageIcon icon = ResizedImageIconFactory.create((String) value, 90, 90);
			this.setIcon(icon);
		}
	}

	private void defineSize(JTable table, int column) {
		if(this.SQUARE_SIZE != null) {
			TableColumnModel tableColumnModel = table.getColumnModel();
			TableColumn columnModel = tableColumnModel.getColumn(column);
			columnModel.setWidth(this.SQUARE_SIZE);
			columnModel.setMinWidth(this.SQUARE_SIZE);
			columnModel.setMaxWidth(this.SQUARE_SIZE);
			columnModel.setPreferredWidth(this.SQUARE_SIZE);
		}
	}

}
