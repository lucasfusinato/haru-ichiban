package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvallidJuniorGardenerFlowerSquareException extends InvallidMoveException {

	public InvallidJuniorGardenerFlowerSquareException() {
		super("A floração do jardineiro júnior não é permitida para a casa selecionada.");
	}
	
}
