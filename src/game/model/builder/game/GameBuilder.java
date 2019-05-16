package game.model.builder.game;

import game.model.Game;
import game.model.builder.board.BoardBuilder;
import game.model.builder.board.BoardDirector;

public abstract class GameBuilder<E> {
	
	private Game<E> game;
	
	public void reset() {
		game = new Game<>();
	}
	
	public abstract void defineRoundQuantity();
	
	protected abstract void definePlayer1(String playerName);
	
	protected abstract void definePlayer2(String playerName);
	
	protected abstract BoardBuilder<E> getBoardBuilder();
	
	public void constructBoard() {
		BoardBuilder<E> builder   = getBoardBuilder();
		BoardDirector<E> director = new BoardDirector<>(builder);
		director.construct();
		game.setBoard(builder.getBoard());
	}

	public Game<E> getGame() {
		return game;
	}

}
