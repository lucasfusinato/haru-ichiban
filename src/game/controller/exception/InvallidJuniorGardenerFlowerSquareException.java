package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidJuniorGardenerFlowerSquareException extends InvallidMoveException {

	public InvallidJuniorGardenerFlowerSquareException() {
		super("A flora��o do jardineiro j�nior n�o � permitida para a casa selecionada.");
	}
	
}