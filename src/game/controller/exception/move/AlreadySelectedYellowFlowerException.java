package game.controller.exception.move;

@SuppressWarnings("serial")
public class AlreadySelectedYellowFlowerException extends InvallidMoveException {

	public AlreadySelectedYellowFlowerException() {
		super("A flor amarela j� foi selecionada.");
	}
	
}
