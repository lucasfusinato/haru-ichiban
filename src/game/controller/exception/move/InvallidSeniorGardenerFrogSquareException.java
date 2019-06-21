package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFrogSquareException extends InvallidMoveException {

	public InvallidSeniorGardenerFrogSquareException() {
		super("Não é possível mover o sapo na casa selecionada.");
	}
	
}
