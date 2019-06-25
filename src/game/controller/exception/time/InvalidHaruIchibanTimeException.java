package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidHaruIchibanTimeException extends InvalidMoveTimeException {
	
	public InvalidHaruIchibanTimeException(String etapa) {
		super("A execução do Haru Ichiban não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidHaruIchibanTimeException() {
		this(null);
	}
	
}
