package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFlowerSquareException extends InvallidMoveException {

	public InvallidSeniorGardenerFlowerSquareException() {
		super("N�o � poss�vel executar a flora��o na casa selecionada.");
	}
	
}
