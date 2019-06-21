package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFlowerTimeException extends InvallidMoveTimeException {
	
	public InvallidSeniorGardenerFlowerTimeException(String etapa) {
		super("Não é permitido executar a floração do jardineiro sênior na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidSeniorGardenerFlowerTimeException() {
		this(null);
	}
	
}
