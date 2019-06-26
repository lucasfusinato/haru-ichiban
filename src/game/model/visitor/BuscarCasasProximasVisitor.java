package game.model.visitor;

import java.util.ArrayList;
import java.util.List;

import game.model.board.Board;
import game.model.board.Square;
import game.model.strategy.move.DownMoveStrategy;
import game.model.strategy.move.LeftMoveStrategy;
import game.model.strategy.move.MoveStrategy;
import game.model.strategy.move.RightMoveStrategy;
import game.model.strategy.move.UpMoveStrategy;
import game.model.strategy.square.CompareSquareStrategy;

public class BuscarCasasProximasVisitor<E> implements SquareVisitor<E> {

	private CompareSquareStrategy<E> searchStrategy;
	private List<MoveStrategy> strategies;
	private List<Square<E>> casasProximas;

	public BuscarCasasProximasVisitor(CompareSquareStrategy<E> searchStrategy) {
		strategies = new ArrayList<>();
		strategies.add(new UpMoveStrategy());
		strategies.add(new LeftMoveStrategy());
		strategies.add(new DownMoveStrategy());
		strategies.add(new RightMoveStrategy());
		casasProximas = new ArrayList<>();
		this.searchStrategy = searchStrategy;
	}
	
	@Override
	public void visit(Square<E> square) {
		Square<E> newSquare;
		for(MoveStrategy strategy : strategies) {
			newSquare = buscarCasaProxima(square, strategy);
			if(searchStrategy.compare(newSquare)) {
				casasProximas.add(newSquare);
			}
		}
	}

	private Square<E> buscarCasaProxima(Square<E> square, MoveStrategy move) {
		try {
			Board<E> board = square.getBoard();
			return board.getSquare(move.calculateNextRow(square.getRow()), move.calculateNextColumn(square.getColumn()));
		} catch(Exception ex) {
			return null;
		}
	}

	public List<Square<E>> getCasasProximas() {
		return casasProximas;
	}

}
