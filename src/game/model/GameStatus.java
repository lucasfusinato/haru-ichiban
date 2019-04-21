package game.model;

public enum GameStatus {

	FLOWER_SELECTION("Aguardando a seleção de flores."),
	RED_FLOWER_SELECTION("Aguardando a seleção de flor vermelha."),
	YELLOW_FLOWER_SELECTION("Aguardando a seleção de flor amarela."),
	TURN_1("Aguardando jardineiro júnior mover flor ao nenufar escuro."),
	TURN_2("Aguardando jardineiro sênior mover flor ao nenufar de sua escolha."),
	TURN_2_MOVE_FROG("Aguardando jardineiro sênior mover sapo para outro nenufar."),
	TURN_3("Aguardando jardineiro júnior usar o vento da primavera para mover o(s) nenúfar(es) de sua escolha."),
	TURN_4("Aguardando jardineiro sênior definir qual será o novo nenufar escuro.");
	
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
