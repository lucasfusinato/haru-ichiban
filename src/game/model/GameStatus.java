package game.model;

public enum GameStatus {

	UNITIALIZED_GAME("Jogo não iniciado"),
	WITHDRAW_FLOWER("Saque de flores"),
	FLOWER_SELECTION("Seleção de flores"),
	GARDENER_CROAK("Aguardando jardineiro coaxar"),
	GARDENER_CROAK_FROG_SQUARE("Escolha de casa para o sapo após coaxar"),
	JUNIOR_GARDENER_FLOWER_SQUARE("Floração do jardineiro júnior"),
	SENIOR_GARDENER_FLOWER_SQUARE("Floração do jardineiro sênior"),
	JUNIOR_GARDENER_HARU_ICHIBAN("Jardineiro júnior chama o Haru Ichiban"),
	SENIOR_GARDENER_DARKENED_NENUFAR("Escolha de nenúfar escuro"),
	SENIOR_GARDENER_FROG_SQUARE("Escolha de casa para o sapo após floração"),
	SENIOR_GARDENER_DARKENED_FROG_NENUFAR("Escolha de casa para o sapo após escolha de nenúfar escuro"),
	FINISHED_GAME("Jogo finalizado");

	private String descricao;
	
	private GameStatus(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
