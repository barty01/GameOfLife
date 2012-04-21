package gameOfLife.model;

import gameOfLife.util.BoardUtil;
import gameOfLife.util.StringUtil;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class TestBoard extends TestCase {

	@Test
	public void testBoard() throws Exception {
		int x = 3;
		int y = 3;

		List<Cell> cells1 = Arrays.asList(new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true));
		List<Cell> cells2 = Arrays.asList(new Cell(false), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true));
		List<Cell> cells3 = Arrays.asList(new Cell(false), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(false), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(false));

		String expected1 = "YYY" +
						   "YYY" +
						   "YYY";
		String expected2 = "NYY" +
						   "YYY" +
						   "YYY";
		String expected3 = "NYY" +
						   "YNY" +
						   "YYN";

		Board board1 = BoardUtil.fillBoard(x, y, cells1);
		Board board2 = BoardUtil.fillBoard(x, y, cells2);
		Board board3 = BoardUtil.fillBoard(x, y, cells3);

		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(board1.toString()));
		assertEquals(expected2, StringUtil.stripSpaceAndNewLineCharacters(board2.toString()));
		assertEquals(expected3, StringUtil.stripSpaceAndNewLineCharacters(board3.toString()));

	}

	@Test
	public void testGetCellAndNeighbors3By3() throws Exception {
		int x = 3;
		int y = 3;

		List<Cell> cells1 = Arrays.asList(new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(false), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true));

		String expected1 = "YYY" +
						   "NYY" +
						   "YYY";

		Board board1 = BoardUtil.fillBoard(x, y, cells1);
		Board subBoard1 = board1.getCellAndNeighbors(1, 1);

		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(subBoard1.toString()));
	}
	
	@Test
	public void testGetCellAndNeighbors4By4() throws Exception {
		int x = 4;
		int y = 4;

		List<Cell> cells1 = Arrays.asList(new Cell(true), new Cell(true), new Cell(true), new Cell(true),
										  new Cell(false), new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true), new Cell(true),
										  new Cell(true), new Cell(true), new Cell(true), new Cell(true));

		String expected1 = "YYY" +
				   		   "NYY" +
				   		   "YYY";

		Board board1 = BoardUtil.fillBoard(x, y, cells1);
		Board subBoard1 = board1.getCellAndNeighbors(1, 1);

		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(subBoard1.toString()));
	}
	
	@Test
	public void testGetCellAndNeighbors4By4_withOverflowNorthWest() throws Exception {
		int x = 4;
		int y = 4;

		List<Cell> cells1 = Arrays.asList(new Cell(true), new Cell(true), new Cell(true), new Cell(true),
										  new Cell(false), new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true), new Cell(true),
										  new Cell(true), new Cell(true), new Cell(true), new Cell(true));

		String expected1 = "YYY" +
				   		   "YYY" +
				   		   "YNY";

		Board board1 = BoardUtil.fillBoard(x, y, cells1);
		Board subBoard1 = board1.getCellAndNeighbors(0, 0);

		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(subBoard1.toString()));
	}
	
	@Test
	public void testGetCellAndNeighbors4By4_withOverflowEast() throws Exception {
		int x = 4;
		int y = 4;

		List<Cell> cells1 = Arrays.asList(new Cell(true), new Cell(true), new Cell(true), new Cell(true),
										  new Cell(true), new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true), new Cell(false),
										  new Cell(true), new Cell(true), new Cell(true), new Cell(true));

		String expected1 = "YYY" +
				   		   "YNY" +
				   		   "YYY";

		Board board1 = BoardUtil.fillBoard(x, y, cells1);
		Board subBoard1 = board1.getCellAndNeighbors(2, 3);

		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(subBoard1.toString()));
	}
}
