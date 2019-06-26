package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidEquiparJardineiroTimeException extends InvalidMoveTimeException {

	public InvalidEquiparJardineiroTimeException(String etapa) {
		super("A escolha de itens para o jardineiro n�o � permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvalidEquiparJardineiroTimeException() {
		this(null);
	}
	
	
}
