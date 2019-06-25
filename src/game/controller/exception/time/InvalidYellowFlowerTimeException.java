package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidYellowFlowerTimeException extends InvalidMoveTimeException {

	public InvalidYellowFlowerTimeException(String etapa) {
		super("A sele��o de flor amarela n�o � permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvalidYellowFlowerTimeException() {
		this(null);
	}
	
}
