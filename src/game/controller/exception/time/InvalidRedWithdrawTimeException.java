package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidRedWithdrawTimeException extends InvalidMoveTimeException {

	public InvalidRedWithdrawTimeException() {
		super("Voc� deve aguardar � etapa de saque de flores para sacar uma nova flor vermelha.");
	}
	
}
