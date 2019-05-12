package game.model.builder.game;

import game.model.Game;

public abstract class GameBuilder<E> {
	
	private Game<E> game;
	
	public void reset() {
		game = new Game<>();
	}
	
	public abstract void defineRoundQuantity();
	
	public abstract void constructBoard();
	
	protected abstract void definePlayer1(String playerName);
	
	protected abstract void definePlayer2(String playerName);

	public Game<E> getGame() {
		return game;
	}

}
