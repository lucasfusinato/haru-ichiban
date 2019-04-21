package game.model.flower;

import game.model.ComponentInterface;

public abstract class Flower implements ComponentInterface, Cloneable {

	private boolean numberVisible;
	private boolean selected;
	private int number;
	
	public Flower(int number) {
		this.number = number;
		this.numberVisible = false;
	}

	@Override
	public String getImagePath() {
		return (this.isNumberVisible() ? this.getNumberFlowerImagePath() : (this.isSelected() ? this.getSelectedFlowerImagePath() : this.getFlowerImagePath()));
	}

	@Override
	public Flower clone() throws CloneNotSupportedException {
		return (Flower) super.clone();
	}

	protected abstract String getSelectedFlowerImagePath();
	protected abstract String getNumberFlowerImagePath();
	protected abstract String getFlowerImagePath();

	public int getNumber() {
		return number;
	}

	public boolean isNumberVisible() {
		return numberVisible;
	}

	public void setNumberVisible(boolean numberVisible) {
		this.numberVisible = numberVisible;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
