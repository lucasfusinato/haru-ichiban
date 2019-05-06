package game.view.flowerselection.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JTable;

import game.controller.command.FlowerMouseCommandFactory;
import utils.CommandInvoker;

public class FlowerSelectionTableMouseMotionListener implements MouseMotionListener {

	private FlowerMouseCommandFactory mouseCommandFactory;
	private CommandInvoker commandInvoker;
	
	public FlowerSelectionTableMouseMotionListener(FlowerMouseCommandFactory mouseCommandFactory) {
		this.mouseCommandFactory = mouseCommandFactory;
		this.commandInvoker = new CommandInvoker();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createDragFlowerCommand(getIndex(e)));
		} catch (Exception ex) {
			//TODO implementar classe padrão para tratamento dos erros
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createMoveFlowerCommand(getIndex(e)));
		} catch (Exception ex) {
			//TODO implementar classe padrão para tratamento dos erros
			ex.printStackTrace();
		}
	}

	private int getIndex(MouseEvent e) {
		JTable table = ((JTable) e.getSource());
		Point point = e.getPoint();
		return table.rowAtPoint(point);
	}

}
