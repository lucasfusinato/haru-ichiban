package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidStartGameTimeException extends InvallidMoveTimeException {

	public InvallidStartGameTimeException(String etapa) {
		super("Não é permitido iniciar uma partida na etapa"+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidStartGameTimeException() {
		this(null);
	}
	
}
