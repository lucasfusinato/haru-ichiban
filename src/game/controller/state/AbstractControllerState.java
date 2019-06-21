package game.controller.state;

import game.controller.GameController;
import game.controller.exception.InvallidHaruIchibanTimeException;
import game.controller.exception.InvallidJuniorGardenerFlowerTimeException;
import game.controller.exception.InvallidRedFlowerTimeException;
import game.controller.exception.InvallidSeniorGardenerDarkenedNenufarTimeException;
import game.controller.exception.InvallidSeniorGardenerFlowerTimeException;
import game.controller.exception.InvallidSeniorGardenerFrogTimeException;
import game.controller.exception.InvallidStartGameTimeException;
import game.controller.exception.InvallidYellowFlowerTimeException;

public abstract class AbstractControllerState {
	
	protected GameController gameController;
	
	public AbstractControllerState(GameController gameController) {
		this.gameController = gameController;
	}
	
	public void startGame(String redGardener, String yellowGardener, int gameType) throws Exception {
		throw new InvallidStartGameTimeException(toString());
	}
	
	public void selectRedFlower(int index) throws Exception {
		throw new InvallidRedFlowerTimeException(toString());
	}
	
	public void selectYellowFlower(int index) throws Exception {
		throw new InvallidYellowFlowerTimeException(toString());
	}

	public void defineJuniorGardenerFlowerSquare(int row, int column) throws Exception {
		throw new InvallidJuniorGardenerFlowerTimeException(toString());
	}
	
	public void defineSeniorGardenerFlowerSquare(int row, int column) throws Exception {
		throw new InvallidSeniorGardenerFlowerTimeException(toString());
	}
	
	public void defineSeniorGardenerFrogSquare(int row, int column) throws Exception {
		throw new InvallidSeniorGardenerFrogTimeException(toString());
	}
	
	public void executeHaruIchiban(int row, int column) throws Exception {
		throw new InvallidHaruIchibanTimeException(toString());
	}
	
	public void defineDarkenedNenufar(int row, int column) throws Exception {
		throw new InvallidSeniorGardenerDarkenedNenufarTimeException(toString());
	}
	
	@Override
	public abstract String toString();
		
}
