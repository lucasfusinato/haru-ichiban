package game.model;

public enum TurnStatus {

	WITHDRAW_FLOWER("Saque de flores"),
	FLOWER_SELECTION("Sele��o de flores"),
	JUNIOR_GARDENER_FLOWER_SQUARE("Flora��o do jardineiro j�nior"),
	SENIOR_GARDENER_FLOWER_SQUARE("Flora��o do jardineiro s�nior"),
	JUNIOR_GARDENER_HARU_ICHIBAN("Jardineiro j�nior chama o vento da primavera"),
	SENIOR_GARDENER_DARKENED_NENUFAR("Jardin�iro s�nior selecione o novo nen�far escuro");

	private String descricao;
	
	private TurnStatus(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}