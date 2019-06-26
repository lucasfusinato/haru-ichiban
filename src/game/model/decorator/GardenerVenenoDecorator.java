package game.model.decorator;

import java.util.List;

import game.model.board.Square;
import game.model.flower.Flower;
import game.model.gardener.Gardener;
import game.model.nenufar.Nenufar;
import game.model.strategy.square.CompareFlowerNenufarStrategy;
import game.model.visitor.BuscarCasasProximasVisitor;

public class GardenerVenenoDecorator extends AbstractGardenerDecorator {

	public GardenerVenenoDecorator(Gardener gardener) {
		super(gardener);
	}
	
	@Override
	public void florescer(Square<Nenufar> nenufar, Flower flower) {
		super.florescer(nenufar, flower);
		removerFloresAoRedor(nenufar);
	}

	private void removerFloresAoRedor(Square<Nenufar> square) {
		Nenufar nenufar;
		for(Square<Nenufar> casa : getCasasProximas(square)) {
			try {
				nenufar = casa.getElement();
				nenufar.setElement(null);
			} catch (Exception e) {}
		}
	}

	private List<Square<Nenufar>> getCasasProximas(Square<Nenufar> nenufar) {
		BuscarCasasProximasVisitor<Nenufar> visitor = new BuscarCasasProximasVisitor<>(new CompareFlowerNenufarStrategy());
		nenufar.accept(visitor);
		return visitor.getCasasProximas();
	}

}
