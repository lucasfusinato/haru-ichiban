package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidYellowWithdrawTimeException extends InvalidMoveTimeException {

	public InvalidYellowWithdrawTimeException() {
		super("Você deve aguardar à etapa de saque de flores para sacar uma nova flor amarela.");
	}
	
}
