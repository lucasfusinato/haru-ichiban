package game.controller.exception.move;

@SuppressWarnings("serial")
public class AlreadySelectedRedFlowerException extends InvalidMoveException {

	public AlreadySelectedRedFlowerException() {
		super("A flor vermelha já foi selecionada.");
	}
	
}
