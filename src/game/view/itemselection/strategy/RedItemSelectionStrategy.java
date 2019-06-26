package game.view.itemselection.strategy;

import java.util.List;

import game.controller.GameControllerInterface;

public class RedItemSelectionStrategy implements ItemSelectionStrategy {

	@Override
	public List<Integer> getItens(GameControllerInterface gameController) {
		return gameController.getRedItemsToSelection();
	}

	@Override
	public String getDescricaoItem(GameControllerInterface gameController, Integer item) {
		return gameController.getItemDescription(item);
	}

	@Override
	public void selecionarItem(GameControllerInterface gameController, Integer item) throws Exception {
		gameController.equiparRedGardener(item);
	}

}
