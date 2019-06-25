package game.controller.state;

import game.model.visitor.BoardBloomPointsVisitor;
import game.model.visitor.BoardRedBloomPointsVisitor;
import game.model.visitor.BoardYellowBloomPointsVisitor;

public abstract class AbstractPontuableState extends AbstractEndFlowState {

	private static final int POINTS_TO_WIN = 5;

	public AbstractPontuableState(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	protected void goToNextStep() {
		if(checkBloomPoints()) {
			if(existsWinner()) {
				gameController.setState(new FinishedGame(gameController));
			} else {
				gameController.goToNextRound();
				gameController.setState(new WithdrawFlower(gameController));
			}
		} else {
			defaultStateChange();
		}
	}

	protected abstract void defaultStateChange();
	
	private boolean existsWinner() {
		int redPoints = gameController.getRedPoints();
		int yellowPoints = gameController.getYellowPoints();
		return (redPoints != yellowPoints && Math.max(redPoints, yellowPoints) >= POINTS_TO_WIN);
	}

	private boolean checkBloomPoints() {
		int redPoints = calculateRedPoints();
		int yellowPoints = calculateYellowPoints();
		if(Math.max(redPoints, yellowPoints) > 0) {
			gameController.addScore(redPoints, yellowPoints);
			return true;
		}
		return false;
	}
	
	private int calculateRedPoints() {
		return calculatePoints(new BoardRedBloomPointsVisitor());
	}

	private int calculateYellowPoints() {
		return calculatePoints(new BoardYellowBloomPointsVisitor());
	}
	
	private int calculatePoints(BoardBloomPointsVisitor visitor) {
		gameController.getCurrentBoard().accept(visitor);
		return visitor.getPoints();
	}

}
