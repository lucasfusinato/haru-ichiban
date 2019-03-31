package utils;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
	
	private List<Command> all;
	private List<Command> immediate;
	private List<Command> undone;

	public CommandInvoker() {
		init();
	}
	
	private void init() {
		all = new ArrayList<>();
		immediate = new ArrayList<>();
		undone = new ArrayList<>();
	}
	
	public void add(Command command) {
		immediate.add(command);
	}
	
	public void execute(Command command) {
		command.execute();
		all.add(command);
	}
	
	public void execute() {
		for(Command command : immediate) {
			command.execute();
			all.add(command);
		}
		immediate.clear();
	}
	
	public void undo() {
		int allSize = all.size();
		if(allSize > 0) {
			Command command = all.remove(allSize - 1);
			command.undo();
			undone.add(command);
		}
	}
	
	public void redo() {
		int undoneSize = undone.size();
		if(undoneSize > 0) {
			Command command = undone.remove(undoneSize - 1);
			command.redo();
			all.add(command);
		}
	}
	
}
