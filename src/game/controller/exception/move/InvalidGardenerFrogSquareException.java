package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidGardenerFrogSquareException extends InvalidMoveException {

	public InvalidGardenerFrogSquareException() {
		super("Não é possível mover o sapo na casa selecionada.");
	}
	
}
