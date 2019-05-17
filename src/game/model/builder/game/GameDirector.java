package game.model.builder.game;

public class GameDirector<E> {

	private GameBuilder<E> builder;
	
	public GameDirector(GameBuilder<E> builder) {
		this.builder = builder;
	}
	
	public void construct(String player1, String player2) {
		builder.reset();
		builder.definePlayer1(player1);
		builder.definePlayer2(player2);
		builder.constructBoard();
		builder.defineRoundQuantity();
	}
	
}
