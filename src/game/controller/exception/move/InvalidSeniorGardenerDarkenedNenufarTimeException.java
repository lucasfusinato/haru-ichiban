package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidSeniorGardenerDarkenedNenufarTimeException extends Exception {

	public InvalidSeniorGardenerDarkenedNenufarTimeException(String etapa) {
		super("N�o � permitido definir o nen�far escuro na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidSeniorGardenerDarkenedNenufarTimeException() {
		this(null);
	}
}
