package game.controller.state;

import game.controller.GameController;
import game.model.Game;
import game.model.GameStatus;
import game.model.builder.game.GameBuilder;
import game.model.builder.game.GameBuilderHashMap;
import game.model.builder.game.GameDirector;
import game.model.nenufar.Nenufar;

public class UnitializedGame extends AbstractControllerState {

	public UnitializedGame(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public void startGame(String redGardener, String yellowGardener, int gameType) throws Exception {
		gameController.setGame(createGame(redGardener, yellowGardener, gameType));
		gameController.setState(new WithdrawFlower(gameController));
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.UNITIALIZED_GAME;
	}

	private Game<Nenufar> createGame(String redPlayer, String yellowPlayer, int gameType) {
		GameBuilderHashMap 	  games    = GameBuilderHashMap.getInstance();
		GameBuilder<Nenufar>  builder  = games.get(gameType);
		GameDirector<Nenufar> director = new GameDirector<>(builder);
		director.construct(redPlayer, yellowPlayer);
		return builder.getGame();
	}
	
}
