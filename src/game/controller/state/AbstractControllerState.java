package game.controller.state;

import game.controller.GameController;
import game.controller.exception.move.InvallidSeniorGardenerDarkenedNenufarTimeException;
import game.controller.exception.time.InvallidHaruIchibanTimeException;
import game.controller.exception.time.InvallidJuniorGardenerFlowerTimeException;
import game.controller.exception.time.InvallidRedFlowerTimeException;
import game.controller.exception.time.InvallidSeniorGardenerFlowerTimeException;
import game.controller.exception.time.InvallidSeniorGardenerFrogTimeException;
import game.controller.exception.time.InvallidStartGameTimeException;
import game.controller.exception.time.InvallidWithdrawRedFlowerTimeExpcetion;
import game.controller.exception.time.InvallidWithdrawYellowFlowerTimeExpcetion;
import game.controller.exception.time.InvallidYellowFlowerTimeException;
import game.model.GameStatus;

public abstract class AbstractControllerState {
	
	protected GameController gameController;
	
	public AbstractControllerState(GameController gameController) {
		this.gameController = gameController;
	}
	
	public void startGame(String redGardener, String yellowGardener, int gameType) throws Exception {
		throw new InvallidStartGameTimeException(toString());
	}
	
	public void withdrawRedFlower(int index) throws Exception {
		throw new InvallidWithdrawRedFlowerTimeExpcetion(toString());
	}
	
	public void withdrawYellowFlower(int index) throws Exception {
		throw new InvallidWithdrawYellowFlowerTimeExpcetion(toString());
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

	public boolean hasBoardInfoAt(int rowIndex, int columnIndex) {
		return false;
	}

	public String getBoardInfoAt(int rowIndex, int columnIndex) {
		return null;
	}
	
	@Override
	public String toString() {
		return getStatus().getDescricao();
	}
	
	public abstract GameStatus getStatus();
		
}
