package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidHaruIchibanTimeException extends InvallidMoveTimeException {

	public InvallidHaruIchibanTimeException() {
		super("Não é permitido executar o Haru Ichiban nesse momento.");
	}
	
}
