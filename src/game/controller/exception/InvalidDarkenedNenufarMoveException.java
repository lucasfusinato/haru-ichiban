package game.controller.exception;

@SuppressWarnings("serial")
public class InvalidDarkenedNenufarMoveException extends InvalidMoveException {

	public InvalidDarkenedNenufarMoveException() {
		super("O jardineiro j�nior deve mover sua flor ao nenufar escuro.");
	}
	
}
