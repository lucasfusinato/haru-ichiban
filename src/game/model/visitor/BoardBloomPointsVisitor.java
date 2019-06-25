package game.model.visitor;

import game.model.board.Board;
import game.model.board.Square;
import game.model.nenufar.Nenufar;
import game.model.strategy.bloom.DirectionNenufarBloomStrategy;
import game.model.strategy.bloom.HorizontalNenufarBloomStrategy;
import game.model.strategy.bloom.LeftNenufarBloomStrategy;
import game.model.strategy.bloom.RightNenufarBloomStrategy;
import game.model.strategy.bloom.VerticalNenufarBloomStrategy;
import game.model.strategy.nenufar.CompareNenufarElementStrategy;

public class BoardBloomPointsVisitor implements BoardVisitor<Nenufar> {

	private static final int PREMIUM_NENUFAR_BLOOM = 5;
	private static final int DIAGONAL_NENUFAR_BLOOM = 4;
	private static final int STRAIGHT_NENUFAR_BLOOM = 3;
	private static final int BASIC_NENUFAR_BLOOM = 1;

	private DirectionNenufarBloomStrategy rightStrategy;
	private DirectionNenufarBloomStrategy leftStrategy;
	private DirectionNenufarBloomStrategy verticalStrategy;
	private DirectionNenufarBloomStrategy horizontalStrategy;
	
	private Board<Nenufar> scope;
	private int points;
	private CompareNenufarElementStrategy compareStrategy;
	
	public BoardBloomPointsVisitor(CompareNenufarElementStrategy compareStrategy) {
		this.rightStrategy = new RightNenufarBloomStrategy();
		this.leftStrategy = new LeftNenufarBloomStrategy();
		this.verticalStrategy = new VerticalNenufarBloomStrategy();
		this.horizontalStrategy = new HorizontalNenufarBloomStrategy();
		this.compareStrategy = compareStrategy;
		this.scope = null;
		this.points = 0;
	}
	
	@Override
	public void visit(Board<Nenufar> board) {
		this.scope = board;
		this.find();
		this.scope = null;
	}

	private void find() {
		for(int i = 0; i < this.scope.getRows(); i++) {
			for(int j = 0; j < this.scope.getCols(); j++) {
				if(this.foundPremiumNenufarBloom(i, j)) {
					points += PREMIUM_NENUFAR_BLOOM;
				}
				if(this.foundDiagonalNenufarBloom(i, j)) {
					points += DIAGONAL_NENUFAR_BLOOM;
				}
				if(this.foundStraightNenufarBloom(i, j)) {
					points += STRAIGHT_NENUFAR_BLOOM;
				}
				if(this.foundBasicNenufarBloom(i, j)) {
					points += BASIC_NENUFAR_BLOOM;
				}
			}
		}
	}

	private boolean foundPremiumNenufarBloom(int i, int j) {
		return this.foundVerticalNenufarBloom(i, j, 5)
			|| this.foundHorizontalNenufarBloom(i, 	j, 5)
			|| this.foundRightDiagonalNenufarBloom(i, j, 5)
			|| this.foundLeftDiagonalNenufarBloom(i, j, 5);
	}

	private boolean foundDiagonalNenufarBloom(int i, int j) {
		return this.foundRightDiagonalNenufarBloom(i, j, 4)
			|| this.foundLeftDiagonalNenufarBloom(i, j, 4);
	}

	private boolean foundStraightNenufarBloom(int i, int j) {
		return this.foundVerticalNenufarBloom(i, j, 4)
			|| this.foundHorizontalNenufarBloom(i, 	j, 4);
	}

	private boolean foundBasicNenufarBloom(int i, int j) {
		return this.foundTargetAtSquare(i, 		j)
			&& this.foundTargetAtSquare(i, 		j + 1)
			&& this.foundTargetAtSquare(i + 1, 	j)
			&& this.foundTargetAtSquare(i + 1, 	j + 1);
	}

	private boolean foundRightDiagonalNenufarBloom(int i, int j, int length) {
		return foundDirectionNenufarBloom(rightStrategy, i, j, length);
	}

	private boolean foundLeftDiagonalNenufarBloom(int i, int j, int length) {
		return foundDirectionNenufarBloom(leftStrategy, i, j, length);
	}

	private boolean foundVerticalNenufarBloom(int i, int j, int length) {
		return foundDirectionNenufarBloom(verticalStrategy, i, j, length);
	}

	private boolean foundHorizontalNenufarBloom(int i, int j, int length) {
		return foundDirectionNenufarBloom(horizontalStrategy, i, j, length);
	}

	private boolean foundDirectionNenufarBloom(DirectionNenufarBloomStrategy strategy, int i, int j, int length) {
		int founded = 0;
		int currentI, currentJ;
		for(int k = 0; k < length; k++) {
			currentI = strategy.calculateI(i, k);
			currentJ = strategy.calculateJ(j, k);
			if(!this.isOutOfBounds(currentI, currentJ)) {
				if(this.foundTargetAtSquare(currentI, currentJ)) {
					founded++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return founded == length;
	}

	private boolean foundTargetAtSquare(int i, int j) {
		if(!this.isOutOfBounds(i, j)) {
			Square<Nenufar> square = this.scope.getSquare(i, j);
			return compareStrategy.compare(square);
		} else {
			return false;
		}
	}

	private boolean isOutOfBounds(int i, int j) {
		return (i < 0 || i >= this.scope.getRows() || j < 0 || j >= this.scope.getCols());
	}

	public int getPoints() {
		return points;
	}

}
