package game.controller.state;

import game.controller.exception.move.InvalidGardenerFrogSquareException;
import game.model.board.Square;
import game.model.flower.Flower;
import game.model.frog.Frog;
import game.model.game.GameStatus;
import game.model.gardener.GardenerColor;
import game.model.nenufar.Nenufar;
import game.model.strategy.square.CompareEmptyNenufarStrategy;
import game.model.strategy.square.CompareLightedNenufarSideStrategy;
import game.model.strategy.square.CompareNenufarStrategy;
import game.model.strategy.square.CompareRedFrogNenufarStrategy;
import game.model.strategy.square.CompareYellowFrogNenufarStrategy;
import game.model.visitor.BoardFindNenufarVisitor;
import game.model.visitor.CountNenufarVisitor;

public class GardenerCroakFrogSquare extends AbstractPontuableState {

	private boolean movedRedFrog;
	private boolean movedYellowFrog;

	public GardenerCroakFrogSquare(GameControllerStateAccess gameController) {
		super(gameController);
		if(existsFrogs()) {
			next(false);
		} else {
			goToNextStep();
		}
	}
	
	@Override
	public void defineGardenerFrogSquare(int row, int column) throws Exception {
		if(canMoveFrogToSquare(row, column)) {
			moveFrogToSquare(row, column);
			next(true);
		} else {
			throw new InvalidGardenerFrogSquareException();
		}
	}
	
	@Override
	protected void defaultStateChange() {
		endFlow();
	}

	public boolean existsFrogs() {
		CountNenufarVisitor visitor1 = new CountNenufarVisitor(new CompareRedFrogNenufarStrategy());
		gameController.getCurrentBoard().accept(visitor1);
		CountNenufarVisitor visitor2 = new CountNenufarVisitor(new CompareYellowFrogNenufarStrategy());
		gameController.getCurrentBoard().accept(visitor2);
		return visitor1.getCount() > 0 || visitor2.getCount() > 0;
	}
	
	private void next(boolean message) {
		if(moveFrog(message)) {
			goToNextStep();
		}
	}

	private boolean moveFrog(boolean message) {
		if(!movedRedFrog) {
			movedRedFrog = true;
			Frog redFrog = defineFlowerInFrogSquare(findRedFrogSquare(), getSelectedRedFlower(), GardenerColor.RED);
			if(redFrog != null) {
				gameController.setCurrentFrog(redFrog);
				if(message) {
					gameController.requestMoveRedFrog();
				}
				return false;
			} 
		}
		if(!movedYellowFrog) {
			movedYellowFrog = true;
			Frog yellowFrog = defineFlowerInFrogSquare(findYellowFrogSquare(), getSelectedYellowFlower(), GardenerColor.YELLOW);
			if(yellowFrog != null) {
				gameController.setCurrentFrog(yellowFrog);
				if(message) {
					gameController.requestMoveYellowFrog();
				}
				return false;
			} 
		}
		return true;
	}
	
	private boolean canMoveFrogToSquare(int row, int column) {
		Square<Nenufar> square = gameController.getCurrentBoard().getSquare(row, column);
		CompareNenufarStrategy strategy1 = new CompareLightedNenufarSideStrategy();
		CompareNenufarStrategy strategy2 = new CompareEmptyNenufarStrategy();
		return strategy1.compare(square) && strategy2.compare(square);
	}

	private void moveFrogToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		nenufar.setElement(gameController.getCurrentFrog());
		gameController.setElementAtSquare(nenufar, row, column);
	}
	
	@Override
	public GameStatus getStatus() {
		return GameStatus.GARDENER_CROAK_FROG_SQUARE;
	}
	
	private Frog defineFlowerInFrogSquare(Square<Nenufar> square, Flower flower, GardenerColor color) {
		Frog frog = null;
		if(square != null) {
			frog = (Frog) square.getElement().getElement();
			gameController.moveGardenerFlowerToSquare(square, flower, color);
		}
		return frog;
	}

	private Flower getSelectedRedFlower() {
		return gameController.getCurrentTurn().getSelectedRedFlower();
	}
	
	private Flower getSelectedYellowFlower() {
		return gameController.getCurrentTurn().getSelectedYellowFlower();
	}

	private Square<Nenufar> findRedFrogSquare() {
		return findFrogSquare(new CompareRedFrogNenufarStrategy());
	}

	private Square<Nenufar> findYellowFrogSquare() {
		return findFrogSquare(new CompareYellowFrogNenufarStrategy());
	}
	
	private Square<Nenufar> findFrogSquare(CompareNenufarStrategy strategy) {
		BoardFindNenufarVisitor visitor = new BoardFindNenufarVisitor(strategy);
		gameController.getCurrentBoard().accept(visitor);
		return visitor.getSquare();
	}
	
	@Override
	protected void goToNextStep() {
		gameController.setCurrentFrog(null);
		gameController.setCroakGardener(null);
		super.goToNextStep();
	}

}
