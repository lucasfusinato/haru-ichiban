package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidDarkenedNenufarSquareException extends InvalidMoveException {

	public InvalidDarkenedNenufarSquareException() {
		super("N�o � permitido definir um nen�far escuro na casa selecionada.");
	}
	
}
