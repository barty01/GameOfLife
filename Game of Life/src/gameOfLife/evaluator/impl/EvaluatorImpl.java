package gameOfLife.evaluator.impl;

import gameOfLife.evaluator.Evaluator;
import gameOfLife.model.Board;
import gameOfLife.model.Cell;
import gameOfLife.model.ThreeByThreeBoard;

public class EvaluatorImpl implements Evaluator{

	@Override
	public boolean evaluate(ThreeByThreeBoard threeByThreeBoard) {
		Cell targetCell = threeByThreeBoard.getCellAt(1, 1);

		int numAliveNeighbors = getNumAliveNeighbors(threeByThreeBoard);
		
		// Any live cell with fewer than two live neighbours dies, as if caused by under-population.
		if(targetCell.isAlive() && numAliveNeighbors < 2){
			return false;
		}
		
		// Any live cell with two or three live neighbours lives on to the next generation.
		if(targetCell.isAlive() && (numAliveNeighbors == 2 || numAliveNeighbors == 3)){
			return true;
		}
		
		// Any live cell with more than three live neighbours dies, as if by overcrowding.
		if(targetCell.isAlive() && numAliveNeighbors > 3){
			return false;
		}
		
		// Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
		if(!targetCell.isAlive() && numAliveNeighbors == 3){
			return true;
		}
		
		return targetCell.isAlive();
	}
	
	@Override
	public Board evaluate(Board board){
		Board newBoard = new Board(board.getHeight(), board.getWidth());
		
		for (int i = 0; i < newBoard.getHeight(); i++) {
			for (int j = 0; j < newBoard.getWidth(); j++) {
				ThreeByThreeBoard subBoard = board.getCellAndNeighbors(i, j);
				Cell c = new Cell(this.evaluate(subBoard));
				newBoard.setCellAt(c, i, j);
			}
		}
		
		return newBoard;
	}

	@Override
	public int getNumAliveNeighbors(ThreeByThreeBoard threeByThreeBoard) {
		int numAliveNeighbors = 0;
		for (int i = 0; i < threeByThreeBoard.getHeight(); i++) {
			for (int j = 0; j < threeByThreeBoard.getWidth(); j++) {
				// ignore the 1,1 cell, this is the target
				if (i == 1 && j == 1) {
					continue;
				} else {
					Cell c = threeByThreeBoard.getCellAt(i, j);
					if (c.isAlive()) {
						numAliveNeighbors++;
					}
				}
			}
		}
		return numAliveNeighbors;
	}

}
