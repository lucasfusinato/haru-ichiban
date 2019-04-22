package game.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.controller.checker.NenufarBloomFinder;
import game.controller.exception.ActionNotAllowedException;
import game.controller.exception.InvalidDarkenedNenufarMoveException;
import game.controller.exception.InvalidFrogNenufarMoveException;
import game.controller.exception.InvalidHaruIchibanSelectedSquareException;
import game.controller.exception.InvalidMoveException;
import game.controller.exception.InvalidNenufarMoveException;
import game.controller.exception.InvalidSelectedDarkenedNenufarException;
import game.controller.exception.RedFlowerAlreadySelectedException;
import game.controller.exception.YellowFlowerAlreadySelectedException;
import game.model.GameRound;
import game.model.GameStatus;
import game.model.NenufarBoard;
import game.model.Square;
import game.model.bloom.NenufarBloom;
import game.model.factory.FlowerFactory;
import game.model.factory.NenufarBoardFactory;
import game.model.factory.NenufarFactory;
import game.model.factory.RedGardenerFactory;
import game.model.factory.YellowGardenerFactory;
import game.model.flower.Flower;
import game.model.flower.RedFlower;
import game.model.flower.YellowFlower;
import game.model.frog.Frog;
import game.model.frog.RedFrog;
import game.model.frog.YellowFrog;
import game.model.gardener.Gardener;
import game.model.gardener.GardenerColor;
import game.model.nenufar.DarkenedNenufar;
import game.model.nenufar.Nenufar;
import game.model.nenufar.RedFlowerNenufar;
import game.model.nenufar.RedFrogNenufar;
import game.model.nenufar.StandardNenufar;
import game.model.nenufar.YellowFlowerNenufar;
import game.model.nenufar.YellowFrogNenufar;

public class GameController implements GameControllerInterface {
	
	private static GameController instance;
	private final int ROUND_QUANTITY = 8;
	private final int LIMIT_AVAILABLE_FLOWER = 3;
	private final int POINTS_TO_WIN = 5;
	private List<GameControllerObserver> observers;
	private NenufarBoard board;
	private Gardener redGardener;
	private Gardener yellowGardener;
	private GameRound[] rounds;
	private int currentRound;
	private GameStatus gameStatus;
	private NenufarFactory nenufarFactory;
	private Nenufar waitingFrogNenufar;
	private Square<Nenufar> selectedSquare;
	
	public static synchronized GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	private GameController() {
		this.observers   			= new ArrayList<>();
		this.board 		 			= NenufarBoardFactory.createEmptyBoard();
		this.redGardener 			= RedGardenerFactory.getInstance().create();
		this.yellowGardener 		= YellowGardenerFactory.getInstance().create();
		this.rounds					= null;
		this.currentRound   		= -1;
		this.gameStatus				= null;
		this.nenufarFactory			= NenufarFactory.getInstance();
	}

	@Override
	public void startGame() {
		this.board = NenufarBoardFactory.createStartBoard();
		this.rounds = new GameRound[ROUND_QUANTITY];
		this.currentRound = -1;
		this.waitingFrogNenufar = null;
		this.redGardener.setFlowers(this.createRedGardenerFlowers());
		this.yellowGardener.setFlowers(this.createYellowGardenerFlowers());
		this.nofifyGameHasStarted();
		this.goToNextRound();
	}

	@Override
	public void attach(GameControllerObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public int getBoardRowCount() {
		return this.board.getRows();
	}

	@Override
	public int getBoardColumnCount() {
		return this.board.getCols();
	}

	@Override
	public String getBoardElementImageAt(int rowIndex, int columnIndex) {
		String image = "";
		Nenufar nenufar = this.board.getElementAtSquare(rowIndex, columnIndex);
		if(nenufar != null) {
			image = nenufar.getImagePath();
		} else if(this.isSquareToMove(rowIndex, columnIndex)) {
			image = this.getSquareToMoveImage(rowIndex, columnIndex);
		}
		return image;
	}

	private boolean isSquareToMove(int rowIndex, int columnIndex) {
		if(this.gameStatus == GameStatus.TURN_3 && this.selectedSquare != null) {
			Square<Nenufar> square = this.board.getSquare(rowIndex, columnIndex);
			if(square != this.selectedSquare && square.getElement() == null) {
				if(this.selectedSquare.getX() == rowIndex) {
					if(columnIndex < this.selectedSquare.getY()) { //Esquerda
						Square<Nenufar> currentSquare;
						int currentIndex = this.selectedSquare.getY() - 1;
						while(currentIndex >= columnIndex) {
							currentSquare = this.board.getSquare(rowIndex, currentIndex);
							if(currentSquare == square) {
								return true;
							} else if(currentSquare.getElement() == null) {
								return false;
							}
							currentIndex--;
						}
					} else if(columnIndex > this.selectedSquare.getY()) { //Direita
						Square<Nenufar> currentSquare;
						int currentIndex = this.selectedSquare.getY() + 1;
						while(currentIndex <= columnIndex) {
							currentSquare = this.board.getSquare(rowIndex, currentIndex);
							if(currentSquare == square) {
								return true;
							} else if(currentSquare.getElement() == null) {
								return false;
							}
							currentIndex++;
						}
					}
				} else if(this.selectedSquare.getY() == columnIndex) {
					if(rowIndex < this.selectedSquare.getX()) { //Cima
						Square<Nenufar> currentSquare;
						int currentIndex = this.selectedSquare.getX() - 1;
						while(currentIndex >= rowIndex) {
							currentSquare = this.board.getSquare(currentIndex, columnIndex);
							if(currentSquare == square) {
								return true;
							} else if(currentSquare.getElement() == null) {
								return false;
							}
							currentIndex--;
						}
					} else if(rowIndex > this.selectedSquare.getX()) { //Direita
						Square<Nenufar> currentSquare;
						int currentIndex = this.selectedSquare.getX() + 1;
						while(currentIndex <= rowIndex) {
							currentSquare = this.board.getSquare(currentIndex, columnIndex);
							if(currentSquare == square) {
								return true;
							} else if(currentSquare.getElement() == null) {
								return false;
							}
							currentIndex++;
						}
					}
				}
			}
		}
		return false;
	}

	private String getSquareToMoveImage(int rowIndex, int columnIndex) {
		if(this.gameStatus == GameStatus.TURN_3 && this.selectedSquare != null) {
			Square<Nenufar> square = this.board.getSquare(rowIndex, columnIndex);
			if(square != this.selectedSquare && square.getElement() == null) {
				if(this.selectedSquare.getX() == rowIndex) {
					if(columnIndex < this.selectedSquare.getY()) { //Esquerda
						return "images/arrow-left.png";
					} else if(columnIndex > this.selectedSquare.getY()) { //Direita
						return "images/arrow-right.png";
					}
				} else if(this.selectedSquare.getY() == columnIndex) {
					if(rowIndex < this.selectedSquare.getX()) { //Cima
						return "images/arrow-up.png";
					} else if(rowIndex > this.selectedSquare.getX()) { //Baixo
						return "images/arrow-down.png";
					}
				}
			}
		}
		return null;
	}

	@Override
	public void mouseClickedInBoardSquare(int selectedRow, int selectedColumn) {
		try {
			switch(this.gameStatus) {
				case TURN_1:
					this.moveJuniorGardenerFlowerToDarkenedNenufar(selectedRow, selectedColumn);
					this.checkRoundWinner();
					break;
				case TURN_2:
					this.moveSeniorGardenerFlowerToNenufar(selectedRow, selectedColumn);
					this.checkRoundWinner();
					break;
				case TURN_2_MOVE_FROG:
					this.moveWaitingFrogToNenufar(selectedRow, selectedColumn);
					break;
				case TURN_3:
					this.callHaruIchiban(selectedRow, selectedColumn);
					this.checkRoundWinner();
					break;
				case TURN_4:
					this.defineDarkenedNenufar(selectedRow, selectedColumn);
					break;
				default:
					break;
			}
		} catch(InvalidMoveException exception) {
			this.notifyInvalidMove(exception.getMessage());
		}
	}

	private void checkRoundWinner() {
		List<NenufarBloom> redBlooms 	= this.findBlooms(this.board, RedFlowerNenufar.class);
		List<NenufarBloom> yellowBlooms = this.findBlooms(this.board, YellowFlowerNenufar.class);
		if(redBlooms.size() > 0 || yellowBlooms.size() > 0) {
			this.addGardenerBlooms(this.redGardener, redBlooms);
			this.addGardenerBlooms(this.yellowGardener, yellowBlooms);
			Gardener currentWinner = this.getWinner();
			if(currentWinner != null && currentWinner.getPoints() >= this.POINTS_TO_WIN) {
				this.endGame();
			} else {
				this.goToNextRound();
			}
		}
	}

	private List<NenufarBloom> findBlooms(NenufarBoard board, Class<? extends Nenufar> element) {
		NenufarBloomFinder bloomFinder = new NenufarBloomFinder(board, element);
		return bloomFinder.find();
	}

	private void addGardenerBlooms(Gardener gardener, List<NenufarBloom> blooms) {
		for(NenufarBloom bloom : blooms) {
			gardener.addPoints(bloom.getPoints());
		}
	}

	private void defineDarkenedNenufar(int selectedRow, int selectedColumn) throws InvalidSelectedDarkenedNenufarException {
		Square<Nenufar> square = this.board.getSquare(selectedRow, selectedColumn);
		if(square.getElement() != null && square.getElement().getClass() == StandardNenufar.class) {
			square.setElement(nenufarFactory.createDarkenedNenufar());
			this.notifyUpdatedGameBoard();
			this.goToNextRound();
		} else {
			throw new InvalidSelectedDarkenedNenufarException();
		}
	}

	private void callHaruIchiban(int selectedRow, int selectedColumn) throws InvalidHaruIchibanSelectedSquareException {
		if(this.selectedSquare != null) {
			Square<Nenufar> square = this.board.getSquare(selectedRow, selectedColumn);
			if(square.getElement() == null) {
				this.callHaruIchiban(square);
			} else if(square == this.selectedSquare) {
				this.updateHaruIchibanSelectedSquare(null);
			} else {
				this.updateHaruIchibanSelectedSquare(square);
			}
		} else {
			this.defineHaruIchibanSelectedSquare(selectedRow, selectedColumn);
		}
	}

	private void callHaruIchiban(Square<Nenufar> square) {
		Square<Nenufar> nextSquare;
		if(square.getX() == this.selectedSquare.getX()) {
			if(square.getY() < this.selectedSquare.getY()) { //Esquerda
				while(square != this.selectedSquare) {
					nextSquare = this.board.getSquare(square.getX(), square.getY() + 1);
					square.setElement(nextSquare.getElement());
					square = nextSquare;
				}
				selectedSquare.setElement(null);
			} else if(square.getY() > this.selectedSquare.getY()) { //Direita
				while(square != this.selectedSquare) {
					nextSquare = this.board.getSquare(square.getX(), square.getY() - 1);
					square.setElement(nextSquare.getElement());
					square = nextSquare;
				}
				selectedSquare.setElement(null);
			}
		} else if(square.getY() == this.selectedSquare.getY()) {
			if(square.getX() < this.selectedSquare.getX()) { //Cima
				while(square != this.selectedSquare) {
					nextSquare = this.board.getSquare(square.getX() + 1, square.getY());
					square.setElement(nextSquare.getElement());
					square = nextSquare;
				}
				selectedSquare.setElement(null);
			} else if(square.getX() > this.selectedSquare.getX()) { //Baixo
				while(square != this.selectedSquare) {
					nextSquare = this.board.getSquare(square.getX() - 1, square.getY());
					square.setElement(nextSquare.getElement());
					square = nextSquare;
				}
				selectedSquare.setElement(null);
			}
		}
		this.updateHaruIchibanSelectedSquare(null);
		this.updateGameStatus(GameStatus.TURN_4);
	}

	private void defineHaruIchibanSelectedSquare(int selectedRow, int selectedColumn) throws InvalidHaruIchibanSelectedSquareException {
		Square<Nenufar> square = this.board.getSquare(selectedRow, selectedColumn);
		if(square.getElement() != null) {
			this.updateHaruIchibanSelectedSquare(square);
		} else {
			this.updateHaruIchibanSelectedSquare(null);
			throw new InvalidHaruIchibanSelectedSquareException();
		}
	}

	private void updateHaruIchibanSelectedSquare(Square<Nenufar> square) {
		this.selectedSquare = square;
		this.notifyUpdatedGameBoard();
	}

	private void moveWaitingFrogToNenufar(int selectedRow, int selectedColumn) throws InvalidFrogNenufarMoveException {
		Square<Nenufar> square = this.board.getSquare(selectedRow, selectedColumn);
		Nenufar selectedNenufar = square.getElement();
		if(selectedNenufar != null && (selectedNenufar.getClass() == StandardNenufar.class)) {
			this.moveWaitingFrogToNenufar(square);
		} else {
			throw new InvalidFrogNenufarMoveException();
		}
	}

	private void moveWaitingFrogToNenufar(Square<Nenufar> square) {
		square.setElement(this.waitingFrogNenufar);
		this.waitingFrogNenufar = null;
		this.notifyUpdatedGameBoard();
		this.updateGameStatus(GameStatus.TURN_3);
	}

	protected void notifyInvalidMove(String message) {
		for(GameControllerObserver observer : this.observers) {
			observer.showInvalidMoveError(message);
		}
	}

	private void moveJuniorGardenerFlowerToDarkenedNenufar(int selectedRow, int selectedColumn) throws InvalidMoveException {
		Square<Nenufar> square = this.board.getSquare(selectedRow, selectedColumn);
		Nenufar selectedNenufar = square.getElement();
		if(selectedNenufar != null && selectedNenufar.getClass() == DarkenedNenufar.class) {
			this.moveJuniorGardenerFlowerToDarkenedNenufar(square);
		} else {
			throw new InvalidDarkenedNenufarMoveException();
		}
	}

	private void moveJuniorGardenerFlowerToDarkenedNenufar(Square<Nenufar> square) {
		square.setElement(this.createJuniorGardenerFlowerNenufar());
		this.notifyUpdatedGameBoard();
		this.updateGameStatus(GameStatus.TURN_2);
	}

	private GardenerColor getJuniorGardenerColor() {
		return this.getCurrentRound().getJuniorGardener().getColor();
	}

	private void moveSeniorGardenerFlowerToNenufar(int selectedRow, int selectedColumn) throws InvalidMoveException {
		Square<Nenufar> square = this.board.getSquare(selectedRow, selectedColumn);
		Nenufar selectedNenufar = square.getElement();
		if(selectedNenufar != null && selectedNenufar.getClass() != RedFlowerNenufar.class && selectedNenufar.getClass() != YellowFlowerNenufar.class) {
			this.moveSeniorGardenerFlowerToNenufar(square);
		} else {
			throw new InvalidNenufarMoveException();
		}
	}

	private void moveSeniorGardenerFlowerToNenufar(Square<Nenufar> square) {
		Nenufar nenufar = square.getElement();
		square.setElement(this.createSeniorGardenerFlowerNenufar());
		this.notifyUpdatedGameBoard();
		if(nenufar != null && (nenufar.getClass() == RedFrogNenufar.class || nenufar.getClass() == YellowFrogNenufar.class)) {
			this.waitingFrogNenufar = nenufar;
			this.updateGameStatus(GameStatus.TURN_2_MOVE_FROG);
		} else {
			this.updateGameStatus(GameStatus.TURN_3);
		}
	}

	private Nenufar createJuniorGardenerFlowerNenufar() {
		return this.nenufarFactory.createFlowerNenufar(this.getJuniorGardenerColor());
	}

	private Nenufar createSeniorGardenerFlowerNenufar() {
		return this.nenufarFactory.createFlowerNenufar(this.getSeniorGardenerColor());
	}

	private GardenerColor getSeniorGardenerColor() {
		return this.getCurrentRound().getSeniorGardener().getColor();
	}

	@Override
	public void mouseMovedToBoardSquare(int rowIndex, int columnIndex) {
		//TODO
	}

	protected void notifyUpdatedGameBoard() {
		for(GameControllerObserver observer : this.observers) {
			observer.gameBoardHasBeenUpdated();
		}
	}

	@Override
	public int getAvailableFlowerQuantity(GardenerColor flowerColor) {
		int availableFlowerQuantity = 0;
		switch(flowerColor) {
			case RED:
				availableFlowerQuantity = this.redGardener.getFlowers().size();
				break;
			case YELLOW:
				availableFlowerQuantity = this.redGardener.getFlowers().size();
				break;
		}
		return (availableFlowerQuantity > this.LIMIT_AVAILABLE_FLOWER) ? this.LIMIT_AVAILABLE_FLOWER : availableFlowerQuantity;
	}

	@Override
	public String getFlowerImageAt(GardenerColor flowerColor, int index) {
		String flowerImagePath = "";
		switch(flowerColor) {
			case RED:
				flowerImagePath = this.redGardener.getFlower(index).getImagePath();
				break;
			case YELLOW:
				flowerImagePath = this.yellowGardener.getFlower(index).getImagePath();
				break;
		}
		return flowerImagePath;
	}

	@Override
	public String getGameStatus() {
		return (this.gameStatus != null) ? this.gameStatus.getStatus() : "";
	}

	@Override
	public void mouseMovedToFlower(GardenerColor gardenerColor, int index) {
		if(GameStatus.isFlowerSelection(this.gameStatus)) {
			switch(gardenerColor) {
				case RED:
					this.mouseMovedToRedFlower(index);
					break;
				case YELLOW:
					this.mouseMovedToYellowFlower(index);
					break;
			}
		}
	}

	@Override
	public void mouseExitedFlowers(GardenerColor gardenerColor) {
		if(GameStatus.isFlowerSelection(this.gameStatus)) {
			switch(gardenerColor) {
				case RED:
					this.mouseExitedRedFlower();
					break;
				case YELLOW:
					this.mouseExitedYellowFlower();
					break;
			}
		}
	}

	@Override
	public void mouseClickedInFlower(GardenerColor gardenerColor, int index) {
		if(GameStatus.isFlowerSelection(this.gameStatus)) {
			try {
				switch(gardenerColor) {
					case RED:
						this.mouseClickedInRedFlower(index);
						break;
					case YELLOW:
						this.mouseClickedInYellowFlower(index);
						break;
				}
			} catch(ActionNotAllowedException ex) {
				this.notifyInvalidMove(ex.getMessage());
			}
		}
	}

	@Override
	public String getStatusImage() {
		String statusImage = null;
		switch(this.gameStatus) {
			case TURN_1:
				statusImage = this.getJuniorGardenerFlower().getImagePath();
				break;
			case TURN_2:
				statusImage = this.getSeniorGardenerFlower().getImagePath();
				break;
			case TURN_2_MOVE_FROG:
				statusImage = this.getWaitingFrog().getImagePath();
				break;
			default:
				break;
		}
		return statusImage;
	}

	private Frog getWaitingFrog() {
		return (this.waitingFrogNenufar.getClass() == RedFrogNenufar.class) ? new RedFrog() : new YellowFrog();
	}

	private Flower getSeniorGardenerFlower() {
		return (this.getSeniorGardenerColor() == GardenerColor.RED) ? new RedFlower(1) : new YellowFlower(1);
	}

	private Flower getJuniorGardenerFlower() {
		return (this.getJuniorGardenerColor() == GardenerColor.RED) ? new RedFlower(1) : new YellowFlower(1);
	}

	private void mouseClickedInRedFlower(int index) throws RedFlowerAlreadySelectedException {
		if(this.getCurrentRound().getSelectedRedFlower() == null) {
			this.updateSelectedRedFlower(index);
		} else if(this.getCurrentRound().getSelectedRedFlower() == this.redGardener.getFlower(index)) {
			this.updateSelectedRedFlower(-1);
		} else {
			throw new RedFlowerAlreadySelectedException();
		}
	}

	private void mouseClickedInYellowFlower(int index) throws YellowFlowerAlreadySelectedException {
		if(this.getCurrentRound().getSelectedYellowFlower() == null) {
			this.updateSelectedYellowFlower(index);
		} else if(this.getCurrentRound().getSelectedYellowFlower() == this.yellowGardener.getFlower(index)) {
			this.updateSelectedYellowFlower(-1);
		} else {
			throw new YellowFlowerAlreadySelectedException();
		}
	}

	private void mouseMovedToRedFlower(int index) {
		this.updateVisibleRedFlowerNumber(index);
	}

	private void mouseMovedToYellowFlower(int index) {
		this.updateVisibleYellowFlowerNumber(index);
	}

	private void mouseExitedRedFlower() {
		this.hideRedFlowersNumbers();
	}

	private void mouseExitedYellowFlower() {
		this.hideYellowFlowersNumbers();
	}

	private void hideFlowersNumbers() {
		this.hideRedFlowersNumbers();
		this.hideYellowFlowersNumbers();
	}

	private void hideRedFlowersNumbers() {
		this.updateVisibleRedFlowerNumber(-1);
	}

	private void hideYellowFlowersNumbers() {
		this.updateVisibleYellowFlowerNumber(-1);
	}

	private void updateSelectedRedFlower(int index) {
		List<Flower> flowers = this.redGardener.getFlowers();
		Flower selectedFlower = this.updateSelectedFlower(flowers, index);
		this.getCurrentRound().setSelectedRedFlower(selectedFlower);
		this.notifyUpdatedRedFlowers();
		this.checkSelectedFlowers();
	}

	private void updateSelectedYellowFlower(int index) {
		List<Flower> flowers = this.yellowGardener.getFlowers();
		Flower selectedFlower = this.updateSelectedFlower(flowers, index);
		this.getCurrentRound().setSelectedYellowFlower(selectedFlower);
		this.notifyUpdatedYellowFlowers();
		this.checkSelectedFlowers();
	}

	private void checkSelectedFlowers() {
		if(GameStatus.isFlowerSelection(this.gameStatus)) {
			Flower selectedRedFlower = this.getCurrentRound().getSelectedRedFlower();
			Flower selectedYellowFlower = this.getCurrentRound().getSelectedYellowFlower();
			if(selectedRedFlower != null && selectedYellowFlower != null) {
				this.hideFlowersNumbers();
				this.defineRoundGardeners(selectedRedFlower, selectedYellowFlower);
			} else if(selectedRedFlower != null) {
				this.updateGameStatus(GameStatus.YELLOW_FLOWER_SELECTION);
			} else if(selectedYellowFlower != null) {
				this.updateGameStatus(GameStatus.RED_FLOWER_SELECTION);
			} else {
				this.updateGameStatus(GameStatus.FLOWER_SELECTION);
			}
		}
	}

	private void defineRoundGardeners(Flower selectedRedFlower, Flower selectedYellowFlower) {
		if(selectedRedFlower.getNumber() > selectedYellowFlower.getNumber()) {
			this.defineRoundGardeners(this.redGardener, this.yellowGardener);
		} else if(selectedRedFlower.getNumber() < selectedYellowFlower.getNumber()) {
			this.defineRoundGardeners(this.yellowGardener, this.redGardener);
		} else {
			this.tieInFlowerSelection();
		}
	}

	private void defineRoundGardeners(Gardener seniorGardener, Gardener juniorGardener) {
		this.getCurrentRound().setSeniorGardener(seniorGardener);
		this.getCurrentRound().setJuniorGardener(juniorGardener);
		this.notifyRoundGardenersAreDefined(seniorGardener.getName(), juniorGardener.getName());
		this.updateGameStatus(GameStatus.TURN_1);
	}

	protected void notifyRoundGardenersAreDefined(String seniorGardener, String juniorGardener) {
		for(GameControllerObserver observer : this.observers) {
			observer.updateRoundGardeners(seniorGardener, juniorGardener);
		}
	}

	private void tieInFlowerSelection() {
		this.currentRound--;
		this.goToNextRound();
	}

	private Flower updateSelectedFlower(List<Flower> flowers, int index) {
		Flower selectedFlower = null;
		Flower flower;
		for(int i = 0; i < flowers.size(); i++) {
			flower = flowers.get(i);
			if(flower != null) {
				flower.setSelected(i == index);
				if(flower.isSelected()) {
					flower.setDisabled(false);
					selectedFlower = flower;
				} else {
					flower.setDisabled(index != -1);
				}
			}
		}
		return selectedFlower;
	}

	private void updateVisibleRedFlowerNumber(int index) {
		List<Flower> flowers = this.redGardener.getFlowers();
		this.updateVisibleFlowerNumber(flowers, index);
		this.notifyUpdatedRedFlowers();
	}

	private void updateVisibleYellowFlowerNumber(int index) {
		List<Flower> flowers = this.yellowGardener.getFlowers();
		this.updateVisibleFlowerNumber(flowers, index);
		this.notifyUpdatedYellowFlowers();
	}

	private void updateVisibleFlowerNumber(List<Flower> flowers, int index) {
		for(int i = 0; i < flowers.size(); i++) {
			Flower flower = flowers.get(i);
			if(flower != null) {
				flower.setNumberVisible(i == index);
			}
		}
	}

	protected void notifyUpdatedGardenerFlowers(GardenerColor gardenerColor) {
		switch (gardenerColor) {
		case RED:
			this.notifyUpdatedRedFlowers();
			break;
		case YELLOW:
			this.notifyUpdatedYellowFlowers();
			break;
		}
	}

	protected void notifyUpdatedRedFlowers() {
		for(GameControllerObserver observer : this.observers) {
			observer.redFlowersHasBeenUpdated();
		}
	}

	protected void notifyUpdatedYellowFlowers() {
		for(GameControllerObserver observer : this.observers) {
			observer.yellowFlowersHasBeenUpdated();
		}
	}

	private List<Flower> createRedGardenerFlowers() {
		return this.createGardenerFlowers(GardenerColor.RED);
	}

	private List<Flower> createYellowGardenerFlowers() {
		return this.createGardenerFlowers(GardenerColor.YELLOW);
	}
	
	private List<Flower> createGardenerFlowers(GardenerColor gardenerColor) {
		List<Flower> flowers 		= new ArrayList<>();
		FlowerFactory flowerFactory = FlowerFactory.getInstance();
		for(int i = 1; i <= this.ROUND_QUANTITY; i++) {
			flowers.add(flowerFactory.create(gardenerColor, i));
		}
		Collections.shuffle(flowers);
		return flowers;
	}

	private void goToNextRound() {
		this.currentRound++;
		if(this.currentRound < this.rounds.length ) {
			this.refreshFlowers();
			this.rounds[this.currentRound] = new GameRound();
			this.updateGameStatus(GameStatus.FLOWER_SELECTION);
		} else {
			this.endGame();
		}
	}

	private void refreshFlowers() {
		this.refreshFlowers(this.redGardener.getFlowers());
		this.notifyUpdatedRedFlowers();

		this.refreshFlowers(this.yellowGardener.getFlowers());
		this.notifyUpdatedYellowFlowers();
	}

	private void refreshFlowers(List<Flower> flowers) {
		Flower currentFlower;
		int i = flowers.size() - 1;
		while(i >= 0) {
			currentFlower = flowers.get(i);
			if(currentFlower.isSelected()) {
				flowers.remove(currentFlower);
			} else {
				currentFlower.setDisabled(false);
			}
			i--;
		}
	}

	private void endGame() {
		this.notifyGameEnded();
	}

	private Gardener getWinner() {
		int redGardenerPoints 	 = this.redGardener.getPoints();
		int yellowGardenerPoints = this.yellowGardener.getPoints();
		if(redGardenerPoints > yellowGardenerPoints) {
			return this.redGardener;
		} else if(yellowGardenerPoints > redGardenerPoints) {
			return this.yellowGardener;
		} else {
			return null;
		}
	}

	private void updateGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.notifyUpdatedGameStatus();
	}

	protected void notifyGameEnded() {
		Gardener winner = this.getWinner();
		for(GameControllerObserver observer : this.observers) {
			if(winner != null) {
				observer.gameHasEnded(winner.getName());
			} else {
				observer.gameHasEnded();
			}
		}
	}

	protected void notifyUpdatedGameStatus() {
		String gameStatus = this.gameStatus.getStatus();
		for(GameControllerObserver observer : this.observers) {
			observer.gameStatusHasBeenUpdated(gameStatus);
		}
	}

	protected void nofifyGameHasStarted() {
		for(GameControllerObserver observer : this.observers) {
			observer.gameHasStarted();
		}
	}

	private GameRound getCurrentRound() {
		return (this.currentRound >= 0 && this.currentRound < this.rounds.length) ? this.rounds[this.currentRound] : null;
	}

}
