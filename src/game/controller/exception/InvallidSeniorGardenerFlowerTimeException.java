package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFlowerTimeException extends InvallidMoveTimeException {

	public InvallidSeniorGardenerFlowerTimeException() {
		super("N�o � permitido executar a flora��o do jardineiro s�nior nessa etapa do jogo.");
	}
	
}
