package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidYellowCroakTimeException extends InvalidMoveTimeException {

	public InvalidYellowCroakTimeException(String etapa) {
		super("O jardineiro amarelo n�o pode coaxar na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidYellowCroakTimeException() {
		this(null);
	}
	
}
