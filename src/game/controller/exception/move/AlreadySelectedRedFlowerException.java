package game.controller.exception.move;

@SuppressWarnings("serial")
public class AlreadySelectedRedFlowerException extends InvallidMoveException {

	public AlreadySelectedRedFlowerException() {
		super("A flor vermelha j� foi selecionada.");
	}
	
}
