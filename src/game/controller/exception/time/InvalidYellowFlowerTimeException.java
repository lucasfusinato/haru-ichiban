package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidYellowFlowerTimeException extends InvalidMoveTimeException {

	public InvalidYellowFlowerTimeException(String etapa) {
		super("A seleção de flor amarela não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvalidYellowFlowerTimeException() {
		this(null);
	}
	
}
