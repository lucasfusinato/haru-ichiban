package game.model.nenufar;

import java.util.Random;

import game.model.Element;
import game.model.flower.RedFlower;
import game.model.flower.YellowFlower;

public abstract class Nenufar implements Element {

	private NenufarSide topSide;
	private NenufarSide bottomSide;
	private NenufarSide activeSide;
	private int rotation;
	private Element element;

	public Nenufar(NenufarSide topSide, NenufarSide bottomSide) {
		this.topSide = topSide;
		this.bottomSide = bottomSide;
		defineRotation();
		activeTopSide();
	}
	
	private void defineRotation() {
		Random random = new Random();
		rotation = random.nextInt(360); //TODO unused
	}

	public void activeTopSide() {
		if(isFlowered()) {
			activeSide = NenufarSide.LIGHTED;
		} else {
			activeSide = topSide;
		}
	}
	
	public void activeBottomSide() {
		activeSide = bottomSide;
	}

	@Override
	public String getDescription() {
		return activeSide.getDescription();
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	public Element getElement() {
		return element;
	}
	
	public void setElement(Element element) {
		this.element = element;
	}

	public boolean isFlowered() {
		return element != null && (element.getClass() == RedFlower.class || element.getClass() == YellowFlower.class);
	}

	public NenufarSide getActiveSide() {
		return activeSide;
	}

}
