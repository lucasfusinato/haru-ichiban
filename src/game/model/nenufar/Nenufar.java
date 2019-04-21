package game.model.nenufar;

import game.model.ComponentInterface;

public abstract class Nenufar implements ComponentInterface {

	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String getImagePath() {
		return (this.isSelected()) ? this.getSelectedNenufarImagePath() : this.getNenufarImagePath();
	}

	protected abstract String getNenufarImagePath();
	protected abstract String getSelectedNenufarImagePath();
	
}
