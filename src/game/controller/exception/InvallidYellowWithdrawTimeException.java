package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidYellowWithdrawTimeException extends InvallidMoveTimeException {

	public InvallidYellowWithdrawTimeException() {
		super("Voc� deve aguardar � etapa de saque de flores para sacar uma nova flor amarela.");
	}
	
}
