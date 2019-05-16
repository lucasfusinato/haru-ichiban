package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFlowerTimeException extends InvallidMoveTimeException {

	public InvallidSeniorGardenerFlowerTimeException() {
		super("Não é permitido executar a floração do jardineiro sênior nessa etapa do jogo.");
	}
	
}
