package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidDefineDarkenedNenufarTimeException extends InvallidMoveTimeException {

	public InvallidDefineDarkenedNenufarTimeException() {
		super("N�o � permitido definir um nen�far escuro nesse momento.");
	}
	
}
