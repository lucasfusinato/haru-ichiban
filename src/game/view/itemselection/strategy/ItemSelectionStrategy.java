package game.view.itemselection.strategy;

import java.util.List;

import game.controller.GameControllerInterface;

public interface ItemSelectionStrategy {

	List<Integer> getItens(GameControllerInterface gameController);

	String getDescricaoItem(GameControllerInterface gameController, Integer item);

	void selecionarItem(GameControllerInterface gameController, Integer item) throws Exception;

}
