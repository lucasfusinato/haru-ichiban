package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidHaruIchibanTimeException extends InvallidMoveTimeException {

	public InvallidHaruIchibanTimeException() {
		super("N�o � permitido executar o Haru Ichiban nesse momento.");
	}
	
}
