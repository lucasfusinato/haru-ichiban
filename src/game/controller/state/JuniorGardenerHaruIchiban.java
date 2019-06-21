package game.controller.state;

import game.controller.GameController;
import game.model.Direction;
import game.model.GameStatus;
import game.model.board.Board;
import game.model.board.Square;
import game.model.nenufar.Nenufar;

public class JuniorGardenerHaruIchiban extends AbstractControllerState {

	public JuniorGardenerHaruIchiban(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public void executeHaruIchiban(int row, int column) throws Exception {
		if(gameController.hasSelectedSquare()) {
			callHaruIchiban(row, column);
		} else {
			selectSquareToHaruIchiban(row, column);
		}
	}
	
	@Override
	public boolean hasBoardInfoAt(int rowIndex, int columnIndex) {
		return getHaruIchibanDirection(rowIndex, columnIndex) != null;
	}

	@Override
	public String getBoardInfoAt(int rowIndex, int columnIndex) {
		return getHaruIchibanDirection(rowIndex, columnIndex).getDescription();
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.JUNIOR_GARDENER_HARU_ICHIBAN;
	}

	private void callHaruIchiban(int row, int column) {
		Square<Nenufar> square = gameController.getCurrentBoard().getSquare(row, column);
		if(square.getElement() == null) {
			if(getHaruIchibanDirection(square) != null) {
				callHaruIchiban(square);
			} else {
				selectSquareToHaruIchiban(null);
			}
		} else if(square == gameController.getSelectedSquare()) {
			selectSquareToHaruIchiban(null);
		} else {
			selectSquareToHaruIchiban(square);
		}
	}
	
	private void callHaruIchiban(Square<Nenufar> square) {
		Direction direction = getHaruIchibanDirection(square);
		if(direction != null) {
			Board<Nenufar> currentBoard = gameController.getCurrentBoard();
			Square<Nenufar> selectedSquare = gameController.getSelectedSquare();
			Square<Nenufar> nextSquare;
			while(square != selectedSquare) {
				nextSquare = currentBoard.getSquare(getHaruIchibanX(direction, square.getRow()), getHaruIchibanY(direction, square.getColumn()));
				square.setElement(nextSquare.getElement());
				square = nextSquare;
			}
			selectedSquare.setElement(null);
			selectSquareToHaruIchiban(null);
			goToNextStep();
		}
	}

	private void selectSquareToHaruIchiban(int row, int column) {
		selectSquareToHaruIchiban(gameController.getCurrentBoard().getSquare(row, column));
	}
	
	private void selectSquareToHaruIchiban(Square<Nenufar> square) {
		gameController.setSelectedSquare(square);
	}

	private int getHaruIchibanY(Direction direction, int y) {
		switch(direction) {
			case LEFT: 	return y + 1;
			case RIGHT: return y - 1;
			default: 	return y;
		}
	}

	private int getHaruIchibanX(Direction direction, int x) {
		switch(direction) {
			case UP: 	return x + 1;
			case DOWN: 	return x - 1;
			default: 	return x;
		}
	}

	private Direction getHaruIchibanDirection(int rowIndex, int columnIndex) {
		return getHaruIchibanDirection(gameController.getCurrentBoard().getSquare(rowIndex, columnIndex));
	}

	private Direction getHaruIchibanDirection(Square<Nenufar> square) {
		Direction direction = null;
		Square<Nenufar> selectedSquare = gameController.getSelectedSquare();
		if(selectedSquare != null) {
			if(square != selectedSquare && square.getElement() == null) {
				if(selectedSquare.getRow() == square.getRow()) {
					if(square.getColumn() < selectedSquare.getColumn()) {
						direction = Direction.LEFT;
					} else if(square.getColumn() > selectedSquare.getColumn()) {
						direction = Direction.RIGHT;
					}
				} else if(selectedSquare.getColumn() == square.getColumn()) {
					if(square.getRow() < selectedSquare.getRow()) {
						direction = Direction.UP;
					} else if(square.getRow() > selectedSquare.getRow()) {
						direction = Direction.DOWN;
					}
				}
				direction = (canMoveToDirection(direction, square)) ? direction : null;
			}
		}
		return direction;
	}

	private boolean canMoveToDirection(Direction direction, Square<Nenufar> square) {
		if(direction != null) {
			Board<Nenufar> currentBoard = gameController.getCurrentBoard();
			Square<Nenufar> selectedSquare = gameController.getSelectedSquare();
			Square<Nenufar> nextSquare = selectedSquare;
			while(square != selectedSquare) {
				nextSquare = currentBoard.getSquare(getHaruIchibanX(direction, square.getRow()), getHaruIchibanY(direction, square.getColumn()));
				if(nextSquare.getElement() == null) {
					return false;
				}
				square = nextSquare;
			}
			return true;
		} else {
			return false;
		}
	}

	private void goToNextStep() {
		// TODO Auto-generated method stub
		
	}
	
}
