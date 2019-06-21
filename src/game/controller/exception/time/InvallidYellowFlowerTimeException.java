package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidYellowFlowerTimeException extends InvallidMoveTimeException {

	public InvallidYellowFlowerTimeException(String etapa) {
		super("A sele��o de flor amarela n�o � permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvallidYellowFlowerTimeException() {
		this(null);
	}
	
}
