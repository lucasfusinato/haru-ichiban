package utils;

public interface Command {

	void execute();
	void undo();
	void redo();
	
}
