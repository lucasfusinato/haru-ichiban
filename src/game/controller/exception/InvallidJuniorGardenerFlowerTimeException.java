package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidJuniorGardenerFlowerTimeException extends InvallidMoveTimeException {

	public InvallidJuniorGardenerFlowerTimeException() {
		super("A flora��o do jardineiro j�nior n�o � permitida nessa etapa do jogo.");
	}
	
}
