package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidSeniorGardenerDarkenedNenufarTimeException extends Exception {

	public InvalidSeniorGardenerDarkenedNenufarTimeException(String etapa) {
		super("Não é permitido definir o nenúfar escuro na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvalidSeniorGardenerDarkenedNenufarTimeException() {
		this(null);
	}
}
