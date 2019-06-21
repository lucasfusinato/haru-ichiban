package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFlowerTimeException extends InvallidMoveTimeException {
	
	public InvallidSeniorGardenerFlowerTimeException(String etapa) {
		super("N�o � permitido executar a flora��o do jardineiro s�nior na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidSeniorGardenerFlowerTimeException() {
		this(null);
	}
	
}
