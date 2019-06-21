package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidHaruIchibanTimeException extends InvallidMoveTimeException {
	
	public InvallidHaruIchibanTimeException(String etapa) {
		super("A execu��o do Haru Ichiban n�o � permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidHaruIchibanTimeException() {
		this(null);
	}
	
}
