package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidJuniorGardenerFlowerSquareException extends InvalidMoveException {

	public InvalidJuniorGardenerFlowerSquareException() {
		super("A floração do jardineiro júnior não é permitida para a casa selecionada.");
	}
	
}
