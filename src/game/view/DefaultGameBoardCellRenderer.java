package game.view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import view.factory.ResizedImageIconFactory;

@SuppressWarnings("serial")
public class DefaultGameBoardCellRenderer extends DefaultTableCellRenderer {
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		this.setOpaque(false);
		if(value != null) {
			ImageIcon icon = ResizedImageIconFactory.getInstance().create((String) value, 90, 90);
			this.setIcon(icon);
		}
		TableColumnModel tableColumnModel = table.getColumnModel();
		TableColumn columnModel = tableColumnModel.getColumn(column);
		columnModel.setWidth(100);
		columnModel.setMinWidth(100);
		columnModel.setMaxWidth(100);
		columnModel.setPreferredWidth(100);
		return this;
	}
	
}
