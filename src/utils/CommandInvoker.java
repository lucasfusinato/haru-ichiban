package utils;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

	private List<Command> all;

	public CommandInvoker() {
		all = new ArrayList<>();
	}
	
	public void execute(Command command) {
		if(command != null) {
			command.execute();
			all.add(command);
		}
	}

}
