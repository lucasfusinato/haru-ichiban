package game.model.builder.board;

public class BoardDirector<T> {

	private BoardBuilder<T> builder;

	public BoardDirector(BoardBuilder<T> builder) {
		this.builder = builder;
	}

	public void construct() {
		builder.reset();
		builder.constructStartPosition();
	}

}
