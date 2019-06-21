package game.model;

public enum GameStatus {

	UNITIALIZED_GAME("Jogo n�o iniciado"),
	WITHDRAW_FLOWER("Saque de flores"),
	FLOWER_SELECTION("Sele��o de flores"),
	GARDENER_CROAK("Aguardando jardineiro coaxar"),
	GARDENER_CROAK_FROG_SQUARE("Escolha de casa para o sapo ap�s coaxar"),
	JUNIOR_GARDENER_FLOWER_SQUARE("Flora��o do jardineiro j�nior"),
	SENIOR_GARDENER_FLOWER_SQUARE("Flora��o do jardineiro s�nior"),
	JUNIOR_GARDENER_HARU_ICHIBAN("Jardineiro j�nior chama o Haru Ichiban"),
	SENIOR_GARDENER_DARKENED_NENUFAR("Escolha de nen�far escuro"),
	SENIOR_GARDENER_FROG_SQUARE("Escolha de casa para o sapo ap�s flora��o"),
	SENIOR_GARDENER_DARKENED_FROG_NENUFAR("Escolha de casa para o sapo ap�s escolha de nen�far escuro"),
	FINISHED_GAME("Jogo finalizado");

	private String descricao;
	
	private GameStatus(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
