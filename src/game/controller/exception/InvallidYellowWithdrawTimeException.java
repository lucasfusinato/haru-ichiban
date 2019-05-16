package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidYellowWithdrawTimeException extends InvallidMoveTimeException {

	public InvallidYellowWithdrawTimeException() {
		super("Você deve aguardar à etapa de saque de flores para sacar uma nova flor amarela.");
	}
	
}
