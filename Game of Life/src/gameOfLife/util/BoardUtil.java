package gameOfLife.util;

import gameOfLife.model.Board;
import gameOfLife.model.Cell;
import gameOfLife.model.ThreeByThreeBoard;

import java.util.List;

import org.apache.commons.lang3.Validate;

public class BoardUtil {

	public static Board fillBoard(int x, int y, List<Cell> cells1) {
		Board board = new Board(x, y);
		int k = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board.setCellAt(cells1.get(k), i, j);
				k++;
			}
		}
		return board;
	}
	
	public static ThreeByThreeBoard fillBoard(List<Cell> cells1) {
		Validate.isTrue(cells1.size() == 9, "Nine cells expected.  " + cells1.size() + " provided!");
		
		ThreeByThreeBoard board = new ThreeByThreeBoard();
		int k = 0;
		int x = 3;
		int y = 3;
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board.setCellAt(cells1.get(k), i, j);
				k++;
			}
		}
		return board;
	}
	
}
