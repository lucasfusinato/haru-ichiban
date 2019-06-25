package game.controller.exception.time;

import game.controller.exception.move.InvalidMoveException;

@SuppressWarnings("serial")
public class InvalidRedFlowerTimeException extends InvalidMoveException {

	public InvalidRedFlowerTimeException(String etapa) {
		super("A seleção de flor vermelha não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvalidRedFlowerTimeException() {
		this(null);
	}
	
}
