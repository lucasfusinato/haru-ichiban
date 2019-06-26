package game.controller.state;

import game.model.board.Board;
import game.model.board.Square;
import game.model.game.GameStatus;
import game.model.nenufar.Nenufar;
import game.model.strategy.move.DownMoveStrategy;
import game.model.strategy.move.LeftMoveStrategy;
import game.model.strategy.move.MoveStrategy;
import game.model.strategy.move.RightMoveStrategy;
import game.model.strategy.move.UpMoveStrategy;

public class JuniorGardenerHaruIchiban extends AbstractPontuableState {

	public JuniorGardenerHaruIchiban(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	@Override
	public void executeHaruIchiban(int row, int column) {
		if(gameController.hasSelectedSquare()) {
			callHaruIchiban(row, column);
		} else {
			selectSquareToHaruIchiban(row, column);
		}
	}
	
	@Override
	public boolean hasBoardInfoAt(int rowIndex, int columnIndex) {
		return getHaruIchibanMoveStrategy(rowIndex, columnIndex) != null;
	}

	@Override
	public String getBoardInfoAt(int rowIndex, int columnIndex) {
		return getHaruIchibanMoveStrategy(rowIndex, columnIndex).getDirection().getDescription();
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.JUNIOR_GARDENER_HARU_ICHIBAN;
	}

	private void callHaruIchiban(int row, int column) {
		Square<Nenufar> square = gameController.getCurrentBoard().getSquare(row, column);
		if(square.getElement() == null) {
			if(getHaruIchibanMoveStrategy(square) != null) {
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
		MoveStrategy strategy = getHaruIchibanMoveStrategy(square);
		if(strategy != null) {
			Board<Nenufar> currentBoard = gameController.getCurrentBoard();
			Square<Nenufar> selectedSquare = gameController.getSelectedSquare();
			Square<Nenufar> nextSquare;
			while(square != selectedSquare) {
				nextSquare = currentBoard.getSquare(strategy.calculateNextRow(square.getRow()), strategy.calculateNextColumn(square.getColumn()));
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

	private MoveStrategy getHaruIchibanMoveStrategy(int rowIndex, int columnIndex) {
		return getHaruIchibanMoveStrategy(gameController.getCurrentBoard().getSquare(rowIndex, columnIndex));
	}

	private MoveStrategy getHaruIchibanMoveStrategy(Square<Nenufar> square) {
		MoveStrategy strategy = null;
		Square<Nenufar> selectedSquare = gameController.getSelectedSquare();
		if(selectedSquare != null) {
			if(square != selectedSquare && square.getElement() == null) {
				if(selectedSquare.getRow() == square.getRow()) {
					if(square.getColumn() < selectedSquare.getColumn()) {
						strategy = new LeftMoveStrategy();
					} else if(square.getColumn() > selectedSquare.getColumn()) {
						strategy = new RightMoveStrategy();
					}
				} else if(selectedSquare.getColumn() == square.getColumn()) {
					if(square.getRow() < selectedSquare.getRow()) {
						strategy = new UpMoveStrategy();
					} else if(square.getRow() > selectedSquare.getRow()) {
						strategy = new DownMoveStrategy();
					}
				}
				strategy = (canUseMoveStrategy(strategy, square)) ? strategy : null;
			}
		}
		return strategy;
	}

	private boolean canUseMoveStrategy(MoveStrategy strategy, Square<Nenufar> square) {
		if(strategy != null) {
			Board<Nenufar> currentBoard = gameController.getCurrentBoard();
			Square<Nenufar> selectedSquare = gameController.getSelectedSquare();
			Square<Nenufar> nextSquare = selectedSquare;
			while(square != selectedSquare) {
				nextSquare = currentBoard.getSquare(strategy.calculateNextRow(square.getRow()), strategy.calculateNextColumn(square.getColumn()));
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


	@Override
	protected void defaultStateChange() {
		gameController.setState(new SeniorGardenerDarkenedNenufar(gameController));
	}
	
}
