package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidJuniorGardenerFlowerTimeException extends InvalidMoveTimeException {
	
	public InvalidJuniorGardenerFlowerTimeException(String etapa) {
		super("A floração do jardineiro júnior não é permitida na etepa"+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidJuniorGardenerFlowerTimeException() {
		this(null);
	}
	
}
