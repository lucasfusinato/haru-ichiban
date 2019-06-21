package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidRedWithdrawTimeException extends InvallidMoveTimeException {

	public InvallidRedWithdrawTimeException() {
		super("Você deve aguardar à etapa de saque de flores para sacar uma nova flor vermelha.");
	}
	
}
