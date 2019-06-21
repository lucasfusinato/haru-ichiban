package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidYellowFlowerTimeException extends InvallidMoveTimeException {

	public InvallidYellowFlowerTimeException(String etapa) {
		super("A seleção de flor amarela não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvallidYellowFlowerTimeException() {
		this(null);
	}
	
}
