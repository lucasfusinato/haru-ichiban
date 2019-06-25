package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidSeniorGardenerFlowerTimeException extends InvalidMoveTimeException {
	
	public InvalidSeniorGardenerFlowerTimeException(String etapa) {
		super("Não é permitido executar a floração do jardineiro sênior na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidSeniorGardenerFlowerTimeException() {
		this(null);
	}
	
}
