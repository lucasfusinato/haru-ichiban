package core.controller;

public enum MainMenuOption {
	
	NEW_GAME("Novo Jogo"),
	ABOUT("Sobre"),
	EXIT("Sair");
	
	private String option;
	
	private MainMenuOption(String option) {
		this.init(option);
	}

	private void init(String option) {
		this.option = option;
	}
	
	public String getOption() {
		return this.option;
	}

}
