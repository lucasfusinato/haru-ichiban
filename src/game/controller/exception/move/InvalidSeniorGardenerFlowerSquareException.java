package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidSeniorGardenerFlowerSquareException extends InvalidMoveException {

	public InvalidSeniorGardenerFlowerSquareException() {
		super("Não é possível executar a floração na casa selecionada.");
	}
	
}
