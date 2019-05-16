package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFlowerSquareException extends InvallidMoveException {

	public InvallidSeniorGardenerFlowerSquareException() {
		super("Não é possível executar a floração na casa selecionada.");
	}
	
}
