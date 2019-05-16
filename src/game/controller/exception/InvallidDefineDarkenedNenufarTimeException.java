package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidDefineDarkenedNenufarTimeException extends InvallidMoveTimeException {

	public InvallidDefineDarkenedNenufarTimeException() {
		super("Não é permitido definir um nenúfar escuro nesse momento.");
	}
	
}
