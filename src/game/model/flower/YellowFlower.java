package game.model.flower;

public class YellowFlower extends Flower {

	public YellowFlower(int number) {
		super(number);
	}

	@Override
	protected String getDisabledFlowerImagePath() {
		return "images/lighted-yellow-flower.png";
	}

	@Override
	protected String getNumberFlowerImagePath() {
		return "images/yellow-flower-"+this.getNumber()+".png";
	}

	@Override
	protected String getFlowerImagePath() {
		return "images/yellow-flower.png";
	}

}
