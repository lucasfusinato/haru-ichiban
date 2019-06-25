package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidDarkenedNenufarSquareException extends InvalidMoveException {

	public InvalidDarkenedNenufarSquareException() {
		super("Não é permitido definir um nenúfar escuro na casa selecionada.");
	}
	
}
