package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidHaruIchibanTimeException extends InvallidMoveTimeException {
	
	public InvallidHaruIchibanTimeException(String etapa) {
		super("A execução do Haru Ichiban não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidHaruIchibanTimeException() {
		this(null);
	}
	
}
