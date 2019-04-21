package game.model;

public enum GameStatus {

	FLOWER_SELECTION("Aguardando a sele��o de flores."),
	RED_FLOWER_SELECTION("Aguardando a sele��o de flor vermelha."),
	YELLOW_FLOWER_SELECTION("Aguardando a sele��o de flor amarela."),
	TURN_1("Aguardando jardineiro j�nior mover flor ao nenufar escuro."),
	TURN_2("Aguardando jardineiro s�nior mover flor ao nenufar de sua escolha."),
	TURN_2_MOVE_FROG("Aguardando jardineiro s�nior mover sapo para outro nenufar."),
	TURN_3("Aguardando jardineiro j�nior usar o vento da primavera para mover o(s) nen�far(es) de sua escolha."),
	TURN_4("Aguardando jardineiro s�nior definir qual ser� o novo nenufar escuro.");
	
	private String status;
	
	private GameStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}

	public static boolean isFlowerSelection(GameStatus gameStatus) {
		return (gameStatus != null && (gameStatus == FLOWER_SELECTION || gameStatus == RED_FLOWER_SELECTION || gameStatus == YELLOW_FLOWER_SELECTION));
	}
	
}
