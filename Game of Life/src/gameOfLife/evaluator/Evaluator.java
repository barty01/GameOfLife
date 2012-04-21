package gameOfLife.evaluator;

import gameOfLife.model.Board;
import gameOfLife.model.ThreeByThreeBoard;

public interface Evaluator {

	public boolean evaluate(ThreeByThreeBoard board);
	
	public Board evaluate(Board board);

	public int getNumAliveNeighbors(ThreeByThreeBoard threeByThreeBoard);
	
}
