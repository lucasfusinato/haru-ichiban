package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidSeniorGardenerFrogTimeException extends InvalidMoveTimeException {
	
	public InvalidSeniorGardenerFrogTimeException(String etapa) {
		super("N�o � permitido mover o sapo na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidSeniorGardenerFrogTimeException() {
		this(null);
	}
	
}
