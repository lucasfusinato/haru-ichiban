package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidRedFlowerTimeException extends InvallidMoveException {

	public InvallidRedFlowerTimeException() {
		super("Você deve aguardar à etapa de seleção de flores para selecionar uma nova flor vermelha.");
	}
	
}
