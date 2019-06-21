package game.controller.state;

import game.controller.GameController;

public class JuniorGardenerHaruIchiban extends AbstractControllerState {

	public JuniorGardenerHaruIchiban(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Jardineiro júnior chama o Haru Ichiban";
	}

}
