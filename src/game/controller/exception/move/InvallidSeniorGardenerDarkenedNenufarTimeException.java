package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerDarkenedNenufarTimeException extends Exception {

	public InvallidSeniorGardenerDarkenedNenufarTimeException(String etapa) {
		super("N�o � permitido definir o nen�far escuro na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidSeniorGardenerDarkenedNenufarTimeException() {
		this(null);
	}
}
