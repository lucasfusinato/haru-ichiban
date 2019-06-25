package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidStartGameTimeException extends InvalidMoveTimeException {

	public InvalidStartGameTimeException(String etapa) {
		super("N�o � permitido iniciar uma partida na etapa"+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidStartGameTimeException() {
		this(null);
	}
	
}
