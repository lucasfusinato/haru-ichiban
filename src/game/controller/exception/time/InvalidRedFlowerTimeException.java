package game.controller.exception.time;

import game.controller.exception.move.InvalidMoveException;

@SuppressWarnings("serial")
public class InvalidRedFlowerTimeException extends InvalidMoveException {

	public InvalidRedFlowerTimeException(String etapa) {
		super("A sele��o de flor vermelha n�o � permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvalidRedFlowerTimeException() {
		this(null);
	}
	
}
