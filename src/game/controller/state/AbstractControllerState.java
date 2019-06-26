package game.controller.state;

import java.util.ArrayList;
import java.util.List;

import game.controller.exception.move.InvalidSeniorGardenerDarkenedNenufarTimeException;
import game.controller.exception.time.InvalidEquiparJardineiroTimeException;
import game.controller.exception.time.InvalidHaruIchibanTimeException;
import game.controller.exception.time.InvalidJuniorGardenerFlowerTimeException;
import game.controller.exception.time.InvlidRedCroakTimeException;
import game.model.game.GameStatus;
import game.model.gardener.GardenerItem;
import game.model.nenufar.Nenufar;
import game.controller.exception.time.InvalidRedFlowerTimeException;
import game.controller.exception.time.InvalidSeniorGardenerFlowerTimeException;
import game.controller.exception.time.InvalidSeniorGardenerFrogTimeException;
import game.controller.exception.time.InvalidStartGameTimeException;
import game.controller.exception.time.InvalidWithdrawRedFlowerTimeExpcetion;
import game.controller.exception.time.InvalidWithdrawYellowFlowerTimeExpcetion;
import game.controller.exception.time.InvalidYellowCroakTimeException;
import game.controller.exception.time.InvalidYellowFlowerTimeException;

public abstract class AbstractControllerState {
	
	protected GameControllerStateAccess gameController;
	
	public AbstractControllerState(GameControllerStateAccess gameController) {
		this.gameController = gameController;
	}
	
	public void startGame(String redGardener, String yellowGardener, int gameType) throws Exception {
		throw new InvalidStartGameTimeException(toString());
	}
	
	public void withdrawRedFlower(int index) throws Exception {
		throw new InvalidWithdrawRedFlowerTimeExpcetion(toString());
	}
	
	public void withdrawYellowFlower(int index) throws Exception {
		throw new InvalidWithdrawYellowFlowerTimeExpcetion(toString());
	}
	
	public void selectRedFlower(int index) throws Exception {
		throw new InvalidRedFlowerTimeException(toString());
	}
	
	public void selectYellowFlower(int index) throws Exception {
		throw new InvalidYellowFlowerTimeException(toString());
	}
	
	public void redGardenerCroak() throws Exception {
		throw new InvlidRedCroakTimeException(toString());
	}
	
	public void yellowGardenerCroak() throws Exception {
		throw new InvalidYellowCroakTimeException(toString());
	}

	public void defineJuniorGardenerFlowerSquare(int row, int column) throws Exception {
		throw new InvalidJuniorGardenerFlowerTimeException(toString());
	}
	
	public void defineSeniorGardenerFlowerSquare(int row, int column) throws Exception {
		throw new InvalidSeniorGardenerFlowerTimeException(toString());
	}
	
	public void defineGardenerFrogSquare(int row, int column) throws Exception {
		throw new InvalidSeniorGardenerFrogTimeException(toString());
	}
	
	public void executeHaruIchiban(int row, int column) throws Exception {
		throw new InvalidHaruIchibanTimeException(toString());
	}
	
	public void defineDarkenedNenufar(int row, int column) throws Exception {
		throw new InvalidSeniorGardenerDarkenedNenufarTimeException(toString());
	}

	public boolean hasBoardInfoAt(int rowIndex, int columnIndex) {
		return false;
	}

	public String getBoardInfoAt(int rowIndex, int columnIndex) {
		return null;
	}
	
	public boolean canSelectRedFlower() {
		return false;
	}
	
	public boolean canSelectYellowFlower() {
		return false;
	}
	
	public void showRedFlowerNumber(int index) {
		return;
	}
	
	public void showYellowFlowerNumber(int index) {
		return;
	}
	
	public boolean canWithdrawRedFlowerAt(int index) {
		return false;
	}
	
	public boolean canWithdrawYellowFlowerAt(int index) {
		return false;
	}
	
	public String getStatusDescription() {
		return getStatus().getDescription();
	}

	public int getBoardRowCount() {
		return gameController.getCurrentBoard().getRows();
	}

	public int getboardColumnCount() {
		return gameController.getCurrentBoard().getCols();
	}

	public boolean hasBoardElementAt(int rowIndex, int columnIndex) {
		return gameController.getCurrentBoard().getElementAtSquare(rowIndex, columnIndex) != null;
	}

	public String getBoardElementAt(int rowIndex, int columnIndex) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(rowIndex, columnIndex); 
		String element = nenufar.getDescription();
		if(nenufar.getElement() != null) {
			element += "-" + nenufar.getElement().getDescription();
		}
		return element;
	}

	public void equiparRedGardener(int item) throws Exception {
		throw new InvalidEquiparJardineiroTimeException(toString());
	}

	public void equiparYellowGardener(int item) throws Exception {
		throw new InvalidEquiparJardineiroTimeException(toString());
	}
	
	public abstract GameStatus getStatus();

	@Override
	public String toString() {
		return getStatus().getDescription();
	}

	public List<Integer> getYellowItemsToSelection() {
		List<Integer> items = new ArrayList<>();
		for(GardenerItem item : gameController.getGameYellowGardenerItems()) {
			items.add(item.ordinal());
		}
		return items;
	}

	public String getItemDescription(Integer item) {
		return GardenerItem.getByNumber(item).getDescription();
	}

	public List<Integer> getRedItemsToSelection() {
		List<Integer> items = new ArrayList<>();
		for(GardenerItem item : gameController.getGameRedGardenerItems()) {
			items.add(item.ordinal());
		}
		return items;
	}
		
}
