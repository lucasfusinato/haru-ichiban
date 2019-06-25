package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidWithdrawYellowFlowerTimeExpcetion extends Exception {

	public InvalidWithdrawYellowFlowerTimeExpcetion(String etapa) {
		super("O saque de flor amarela não é permitida na etapa "+(etapa!=null?etapa:"atual")+".");
	}
	
	public InvalidWithdrawYellowFlowerTimeExpcetion() {
		this(null);
	}

}
