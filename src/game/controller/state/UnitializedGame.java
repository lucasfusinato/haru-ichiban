package game.controller.state;

import game.model.builder.game.GameBuilder;
import game.model.builder.game.GameBuilderHashMap;
import game.model.builder.game.GameDirector;
import game.model.game.Game;
import game.model.game.GameStatus;
import game.model.nenufar.Nenufar;

public class UnitializedGame extends AbstractControllerState {

	public UnitializedGame(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	@Override
	public void startGame(String redGardener, String yellowGardener, int gameType) {
		gameController.setGame(createGame(redGardener, yellowGardener, gameType));
		gameController.setState(new WithdrawFlower(gameController));
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.UNITIALIZED_GAME;
	}

	@Override
	public int getBoardRowCount() {
		return 0;
	}

	@Override
	public int getboardColumnCount() {
		return 0;
	}
	
	@Override
	public boolean hasBoardElementAt(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override
	public String getBoardElementAt(int rowIndex, int columnIndex) {
		return null;
	}

	private Game<Nenufar> createGame(String redPlayer, String yellowPlayer, int gameType) {
		GameBuilderHashMap 	  games    = GameBuilderHashMap.getInstance();
		GameBuilder<Nenufar>  builder  = games.get(gameType);
		GameDirector<Nenufar> director = new GameDirector<>(builder);
		director.construct(redPlayer, yellowPlayer);
		return builder.getGame();
	}
	
}
