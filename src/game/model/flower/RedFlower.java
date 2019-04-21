package game.model.flower;

public class RedFlower extends Flower {

	public RedFlower(int number) {
		super(number);
	}

	@Override
	protected String getSelectedFlowerImagePath() {
		return "images/red-flower.png";
	}

	@Override
	protected String getNumberFlowerImagePath() {
		return "images/red-flower-"+this.getNumber()+".png";
	}

	@Override
	protected String getFlowerImagePath() {
		return "images/darkened-red-flower.png";
	}

}
