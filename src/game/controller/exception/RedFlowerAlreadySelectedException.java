package game.controller.exception;

@SuppressWarnings("serial")
public class RedFlowerAlreadySelectedException extends ActionNotAllowedException {

	public RedFlowerAlreadySelectedException() {
		super("A flor vermelha da rodada já foi selecionada.");
	}
	
}
