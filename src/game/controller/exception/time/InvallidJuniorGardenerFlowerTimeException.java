package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidJuniorGardenerFlowerTimeException extends InvallidMoveTimeException {
	
	public InvallidJuniorGardenerFlowerTimeException(String etapa) {
		super("A floração do jardineiro júnior não é permitida na etepa"+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidJuniorGardenerFlowerTimeException() {
		this(null);
	}
	
}
