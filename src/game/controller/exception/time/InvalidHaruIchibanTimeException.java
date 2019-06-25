package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidHaruIchibanTimeException extends InvalidMoveTimeException {
	
	public InvalidHaruIchibanTimeException(String etapa) {
		super("A execu��o do Haru Ichiban n�o � permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidHaruIchibanTimeException() {
		this(null);
	}
	
}
