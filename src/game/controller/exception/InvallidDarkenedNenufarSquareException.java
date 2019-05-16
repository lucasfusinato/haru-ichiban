package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidDarkenedNenufarSquareException extends InvallidMoveException {

	public InvallidDarkenedNenufarSquareException() {
		super("Não é permitido definir um nenúfar escuro na casa selecionada.");
	}
	
}
