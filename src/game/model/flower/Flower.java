package game.model.flower;

import game.model.ComponentInterface;

public abstract class Flower implements ComponentInterface, Cloneable {

	private boolean numberVisible;
	private boolean disabled;
	private boolean selected;
	private int number;
	
	public Flower(int number) {
		this.number = number;
		this.numberVisible = false;
	}

	@Override
	public String getImagePath() {
		if(this.isDisabled()) {
			return this.getDisabledFlowerImagePath();
		} else if(this.isNumberVisible()) {
			return this.getNumberFlowerImagePath();
		} else {
			return this.getFlowerImagePath();
		}
	}

	@Override
	public Flower clone() throws CloneNotSupportedException {
		return (Flower) super.clone();
	}

	protected abstract String getDisabledFlowerImagePath();
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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
