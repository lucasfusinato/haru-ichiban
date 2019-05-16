package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidJuniorGardenerFlowerTimeException extends InvallidMoveTimeException {

	public InvallidJuniorGardenerFlowerTimeException() {
		super("A floração do jardineiro júnior não é permitida nessa etapa do jogo.");
	}
	
}
