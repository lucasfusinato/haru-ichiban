package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerFrogTimeException extends InvallidMoveTimeException {

	public InvallidSeniorGardenerFrogTimeException() {
		super("Não é permitido mover o sapo nessa etapa do jogo.");
	}
	
}
