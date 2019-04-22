package game.controller.checker;

import java.util.ArrayList;
import java.util.List;

import game.model.NenufarBoard;
import game.model.bloom.BasicNenufarBloom;
import game.model.bloom.DiagonalNenufarBloom;
import game.model.bloom.NenufarBloom;
import game.model.bloom.StraightNenufarBloom;
import game.model.nenufar.Nenufar;

public class NenufarBloomFinder {
	
	private NenufarBoard scope;
	private Class<? extends Nenufar> target;
	
	public NenufarBloomFinder(NenufarBoard scope, Class<? extends Nenufar> target) {
		this.scope = scope;
		this.target = target;
	}

	public List<NenufarBloom> find() {
		List<NenufarBloom> blooms = new ArrayList<>();
		for(int i = 0; i < this.scope.getRows(); i++) {
			for(int j = 0; j < this.scope.getCols(); j++) {
				if(this.foundPremiumNenufarBloom(i, j)) {
					blooms.add(new StraightNenufarBloom());
				}
				if(this.foundDiagonalNenufarBloom(i, j)) {
					blooms.add(new DiagonalNenufarBloom());
				}
				if(this.foundStraightNenufarBloom(i, j)) {
					blooms.add(new StraightNenufarBloom());
				}
				if(this.foundBasicNenufarBloom(i, j)) {
					blooms.add(new BasicNenufarBloom());
				}
			}
		}
		return blooms;
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
		int founded = 0;
		int currentI, currentJ;
		for(int k = 0; k < length; k++) {
			currentI = i + k;
			currentJ = j + k;
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

	private boolean foundLeftDiagonalNenufarBloom(int i, int j, int length) {
		int founded = 0;
		int currentI, currentJ;
		for(int k = 0; k < length; k++) {
			currentI = i - k;
			currentJ = j - k;
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

	private boolean foundVerticalNenufarBloom(int i, int j, int length) {
		int founded = 0;
		int currentI, currentJ;
		for(int k = 0; k < length; k++) {
			currentI = i + k;
			currentJ = j;
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

	private boolean foundHorizontalNenufarBloom(int i, int j, int length) {
		int founded = 0;
		int currentI, currentJ;
		for(int k = 0; k < length; k++) {
			currentI = i;
			currentJ = j + k;
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
			Nenufar element = this.scope.getElementAtSquare(i, j);
			return (element != null && element.getClass() == this.target);
		} else {
			return false;
		}
	}

	private boolean isOutOfBounds(int i, int j) {
		return (i < 0 || i >= this.scope.getRows() || j < 0 || j >= this.scope.getCols());
	}

}
