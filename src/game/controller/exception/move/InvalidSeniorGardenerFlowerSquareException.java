package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidSeniorGardenerFlowerSquareException extends InvalidMoveException {

	public InvalidSeniorGardenerFlowerSquareException() {
		super("N�o � poss�vel executar a flora��o na casa selecionada.");
	}
	
}
