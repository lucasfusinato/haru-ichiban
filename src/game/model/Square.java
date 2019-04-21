package game.model;

public class Square<E> {
	
	private E element;
	private int x;
	private int y;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
		this.element = null;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
