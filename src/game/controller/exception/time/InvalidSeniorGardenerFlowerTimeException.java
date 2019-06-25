package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidSeniorGardenerFlowerTimeException extends InvalidMoveTimeException {
	
	public InvalidSeniorGardenerFlowerTimeException(String etapa) {
		super("N�o � permitido executar a flora��o do jardineiro s�nior na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidSeniorGardenerFlowerTimeException() {
		this(null);
	}
	
}
