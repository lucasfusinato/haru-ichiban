package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFrogTimeException extends InvallidMoveTimeException {

	public InvallidSeniorGardenerFrogTimeException() {
		super("N�o � permitido mover o sapo nessa etapa do jogo.");
	}
	
}
