package utils;

public interface Command {

	void execute() throws Exception;
	void undo() throws Exception;
	void redo() throws Exception;
	
}
