package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidRedFlowerTimeException extends InvallidMoveException {

	public InvallidRedFlowerTimeException() {
		super("Voc� deve aguardar � etapa de sele��o de flores para selecionar uma nova flor vermelha.");
	}
	
}
