package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidWithdrawRedFlowerTimeExpcetion extends Exception {

	public InvalidWithdrawRedFlowerTimeExpcetion(String etapa) {
		super("O saque de flor vermelha n�o � permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvalidWithdrawRedFlowerTimeExpcetion() {
		this(null);
	}

}
