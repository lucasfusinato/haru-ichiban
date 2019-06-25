package game.model.builder.game;

import java.util.HashMap;

import game.model.game.GameType;
import game.model.nenufar.Nenufar;

public class GameBuilderHashMap {
	
	private static GameBuilderHashMap instance;
	private HashMap<GameType, GameBuilder<Nenufar>> builders;
	
	public static GameBuilderHashMap getInstance() {
		if(instance == null) {
			instance = new GameBuilderHashMap();
		}
		return instance;
	}
	
	private GameBuilderHashMap() {
		builders = new HashMap<>();
		builders.put(GameType.NORMAL, 		new NormalGameBuilder());
		builders.put(GameType.ADVANCED, 	new AdvancedGameBuilder());
		builders.put(GameType.MASTER, 		new MasterGameBuilder());
	}

	public GameBuilder<Nenufar> get(int type) {
		return get(GameType.fromInteger(type));
	}

	public GameBuilder<Nenufar> get(GameType type) {
		return builders.get(type);
	}

}
