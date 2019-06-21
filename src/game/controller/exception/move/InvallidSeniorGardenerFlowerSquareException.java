package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFlowerSquareException extends InvallidMoveException {

	public InvallidSeniorGardenerFlowerSquareException() {
		super("Não é possível executar a floração na casa selecionada.");
	}
	
}
