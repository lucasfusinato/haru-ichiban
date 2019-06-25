package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvlidRedCroakTimeException extends InvalidMoveTimeException {

	public InvlidRedCroakTimeException(String etapa) {
		super("O jardineiro vermelho não pode coaxar na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvlidRedCroakTimeException() {
		this(null);
	}	
	
}
