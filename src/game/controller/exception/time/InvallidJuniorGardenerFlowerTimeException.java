package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidJuniorGardenerFlowerTimeException extends InvallidMoveTimeException {
	
	public InvallidJuniorGardenerFlowerTimeException(String etapa) {
		super("A flora��o do jardineiro j�nior n�o � permitida na etepa"+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidJuniorGardenerFlowerTimeException() {
		this(null);
	}
	
}
