package game.controller.exception;

@SuppressWarnings("serial")
public class InvalidHaruIchibanSelectedSquareException extends InvalidMoveException {

	public InvalidHaruIchibanSelectedSquareException() {
		super("O jardineiro j�nior deve selecionar uma casa vazia para chamar o Haru Ichiban.");
	}

}
