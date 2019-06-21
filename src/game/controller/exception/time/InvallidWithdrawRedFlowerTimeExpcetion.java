package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidWithdrawRedFlowerTimeExpcetion extends Exception {

	public InvallidWithdrawRedFlowerTimeExpcetion(String etapa) {
		super("O saque de flor vermelha não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvallidWithdrawRedFlowerTimeExpcetion() {
		this(null);
	}

}
