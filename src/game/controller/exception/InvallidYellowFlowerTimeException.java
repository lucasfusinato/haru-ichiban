package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidYellowFlowerTimeException extends InvallidMoveTimeException {

	public InvallidYellowFlowerTimeException() {
		super("Voc� deve aguardar � etapa de sele��o de flores para selecionar uma nova flor amarela.");
	}
	
}
