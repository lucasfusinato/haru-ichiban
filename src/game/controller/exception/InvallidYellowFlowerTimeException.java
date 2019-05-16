package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidYellowFlowerTimeException extends InvallidMoveTimeException {

	public InvallidYellowFlowerTimeException() {
		super("Você deve aguardar à etapa de seleção de flores para selecionar uma nova flor amarela.");
	}
	
}
