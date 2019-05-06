package game.model;

public enum TurnStatus {

	WITHDRAW_FLOWER("Saque de flores"),
	FLOWER_SELECTION("Seleção de flores"),
	JUNIOR_GARDENER_FLOWER_SQUARE("Floração do jardineiro júnior"),
	SENIOR_GARDENER_FLOWER_SQUARE("Floração do jardineiro sênior"),
	JUNIOR_GARDENER_HARU_ICHIBAN("Jardineiro júnior chama o vento da primavera"),
	SENIOR_GARDENER_DARKENED_NENUFAR("Jardinêiro sênior selecione o novo nenúfar escuro");

	private String descricao;
	
	private TurnStatus(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
