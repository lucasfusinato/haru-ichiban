package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidGardenerFrogSquareException extends InvalidMoveException {

	public InvalidGardenerFrogSquareException() {
		super("N�o � poss�vel mover o sapo na casa selecionada.");
	}
	
}
