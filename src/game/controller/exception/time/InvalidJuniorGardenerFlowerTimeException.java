package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidJuniorGardenerFlowerTimeException extends InvalidMoveTimeException {
	
	public InvalidJuniorGardenerFlowerTimeException(String etapa) {
		super("A flora��o do jardineiro j�nior n�o � permitida na etepa"+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidJuniorGardenerFlowerTimeException() {
		this(null);
	}
	
}
