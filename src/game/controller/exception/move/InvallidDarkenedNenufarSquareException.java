package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvallidDarkenedNenufarSquareException extends InvallidMoveException {

	public InvallidDarkenedNenufarSquareException() {
		super("N�o � permitido definir um nen�far escuro na casa selecionada.");
	}
	
}
