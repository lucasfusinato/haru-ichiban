package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvallidSeniorGardenerDarkenedNenufarTimeException extends Exception {

	public InvallidSeniorGardenerDarkenedNenufarTimeException(String etapa) {
		super("Não é permitido definir o nenúfar escuro na etapa "+(etapa!=null?etapa:"atual")+".");
	}

	public InvallidSeniorGardenerDarkenedNenufarTimeException() {
		this(null);
	}
}
