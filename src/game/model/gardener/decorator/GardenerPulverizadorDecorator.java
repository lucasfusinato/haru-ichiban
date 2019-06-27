package game.model.gardener.decorator;

import java.util.List;

import game.model.board.Square;
import game.model.flower.Flower;
import game.model.gardener.Gardener;
import game.model.nenufar.Nenufar;
import game.model.strategy.square.CompareEmptyNenufarStrategy;
import game.model.visitor.BuscarCasasProximasVisitor;

public class GardenerPulverizadorDecorator extends AbstractGardenerDecorator {

	public GardenerPulverizadorDecorator(Gardener gardener) {
		super(gardener);
	}
	
	@Override
	public void florescer(Square<Nenufar> nenufar, Flower flower) {
		super.florescer(nenufar, flower);
		criarFloresAoRedor(nenufar, flower);
	}

	private void criarFloresAoRedor(Square<Nenufar> square, Flower flower) {
		Nenufar nenufar;
		Flower clone;
		for(Square<Nenufar> casa : getCasasProximas(square)) {
			try {
				clone = flower.clone();
				nenufar = casa.getElement();
				nenufar.setElement(clone);
			} catch (Exception e) {}
		}
	}

	private List<Square<Nenufar>> getCasasProximas(Square<Nenufar> nenufar) {
		BuscarCasasProximasVisitor<Nenufar> visitor = new BuscarCasasProximasVisitor<>(new CompareEmptyNenufarStrategy());
		nenufar.accept(visitor);
		return visitor.getCasasProximas();
	}

}
