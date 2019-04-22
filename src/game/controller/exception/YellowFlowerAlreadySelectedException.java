package game.controller.exception;

@SuppressWarnings("serial")
public class YellowFlowerAlreadySelectedException extends ActionNotAllowedException {

	public YellowFlowerAlreadySelectedException() {
		super("A flor amarela da rodada já foi selecionada.");
	}
	
}
