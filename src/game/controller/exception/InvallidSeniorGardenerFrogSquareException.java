package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFrogSquareException extends InvallidMoveException {

	public InvallidSeniorGardenerFrogSquareException() {
		super("N�o � poss�vel mover o sapo na casa selecionada.");
	}
	
}