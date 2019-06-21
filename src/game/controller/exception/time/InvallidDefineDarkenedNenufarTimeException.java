package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidDefineDarkenedNenufarTimeException extends InvallidMoveTimeException {

	public InvallidDefineDarkenedNenufarTimeException() {
		super("Não é permitido definir um nenúfar escuro nesse momento.");
	}
	
}
