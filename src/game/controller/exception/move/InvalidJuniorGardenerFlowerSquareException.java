package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidJuniorGardenerFlowerSquareException extends InvalidMoveException {

	public InvalidJuniorGardenerFlowerSquareException() {
		super("A flora��o do jardineiro j�nior n�o � permitida para a casa selecionada.");
	}
	
}
