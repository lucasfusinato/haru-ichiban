package game.controller.exception.time;

import game.controller.exception.move.InvallidMoveException;

@SuppressWarnings("serial")
public class InvallidRedFlowerTimeException extends InvallidMoveException {

	public InvallidRedFlowerTimeException(String etapa) {
		super("A seleção de flor vermelha não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvallidRedFlowerTimeException() {
		this(null);
	}
	
}
