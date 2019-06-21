package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidWithdrawYellowFlowerTimeExpcetion extends Exception {

	public InvallidWithdrawYellowFlowerTimeExpcetion(String etapa) {
		super("O saque de flor amarela não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvallidWithdrawYellowFlowerTimeExpcetion() {
		this(null);
	}

}
