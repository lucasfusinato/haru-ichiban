package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidYellowWithdrawTimeException extends InvalidMoveTimeException {

	public InvalidYellowWithdrawTimeException() {
		super("Voc� deve aguardar � etapa de saque de flores para sacar uma nova flor amarela.");
	}
	
}
