package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFrogTimeException extends InvallidMoveTimeException {
	
	public InvallidSeniorGardenerFrogTimeException(String etapa) {
		super("Não é permitido mover o sapo na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidSeniorGardenerFrogTimeException() {
		this(null);
	}
	
}
