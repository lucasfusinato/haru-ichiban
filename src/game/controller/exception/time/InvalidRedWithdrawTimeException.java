package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidRedWithdrawTimeException extends InvalidMoveTimeException {

	public InvalidRedWithdrawTimeException() {
		super("Você deve aguardar à etapa de saque de flores para sacar uma nova flor vermelha.");
	}
	
}
