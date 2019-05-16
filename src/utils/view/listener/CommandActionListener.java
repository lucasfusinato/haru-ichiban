package utils.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.Command;
import utils.CommandInvoker;

public class CommandActionListener implements ActionListener {

	private CommandInvoker commandInvoker;
	private Command command;
	
	public CommandActionListener(Command command) {
		this(command, new CommandInvoker());
	}
	
	public CommandActionListener(Command command, CommandInvoker commandInvoker) {
		this.command = command;
		this.commandInvoker = commandInvoker;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		commandInvoker.execute(command);
	}

}
