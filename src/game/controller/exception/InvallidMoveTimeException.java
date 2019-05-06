package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidMoveTimeException extends Exception {

	public InvallidMoveTimeException() {
		super("Movimento não permitido nessa etapa do jogo.");
	}
	
}
