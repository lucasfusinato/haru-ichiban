package game.view.flowerselection.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import game.view.command.FlowerMouseCommandFactory;
import utils.CommandInvoker;

public class FlowerSelectionTableMouseListener implements MouseListener {

	private FlowerMouseCommandFactory mouseCommandFactory;
	private CommandInvoker commandInvoker;
	private boolean vertical;

	public FlowerSelectionTableMouseListener(FlowerMouseCommandFactory mouseCommandFactory) {
		this(mouseCommandFactory, true);
	}

	public FlowerSelectionTableMouseListener(FlowerMouseCommandFactory mouseCommandFactory, boolean vertical) {
		this.mouseCommandFactory = mouseCommandFactory;
		this.commandInvoker = new CommandInvoker();
		this.vertical = vertical;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createClickFlowerCommand(getIndex(e)));
		} catch (Exception ex) {
			//TODO implementar classe padrão para tratamento dos erros
			ex.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createPressFlowerCommand(getIndex(e)));
		} catch (Exception ex) {
			//TODO implementar classe padrão para tratamento dos erros
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createReleaseFlowerCommand(getIndex(e)));
		} catch (Exception ex) {
			//TODO implementar classe padrão para tratamento dos erros
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createEnterFlowerCommand(getIndex(e)));
		} catch (Exception ex) {
			//TODO implementar classe padrão para tratamento dos erros
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		try {
			commandInvoker.execute(mouseCommandFactory.createExitFlowerCommand(getIndex(e)));
		} catch (Exception ex) {
			//TODO implementar classe padrão para tratamento dos erros
			ex.printStackTrace();
		}
	}

	private int getIndex(MouseEvent e) {
		JTable table = ((JTable) e.getSource());
		Point point = e.getPoint();
		return (this.vertical) ? table.rowAtPoint(point) : table.columnAtPoint(point);
	}

}
