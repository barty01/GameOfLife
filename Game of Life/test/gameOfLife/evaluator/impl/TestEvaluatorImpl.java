package gameOfLife.evaluator.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gameOfLife.evaluator.Evaluator;
import gameOfLife.model.Board;
import gameOfLife.model.Cell;
import gameOfLife.model.ThreeByThreeBoard;
import gameOfLife.util.BoardUtil;
import gameOfLife.util.StringUtil;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestEvaluatorImpl {

	@Test
	public void testEvaluate3By3_AliveButOverCrowded() throws Exception {
		List<Cell> cells1 = Arrays.asList(new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true));
		List<Cell> cells2 = Arrays.asList(new Cell(false), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(true));
		List<Cell> cells3 = Arrays.asList(new Cell(false), new Cell(true), new Cell(false), 
										  new Cell(true), new Cell(true), new Cell(true), 
										  new Cell(true), new Cell(true), new Cell(false));
		
		ThreeByThreeBoard board1 = BoardUtil.fillBoard(cells1);
		ThreeByThreeBoard board2 = BoardUtil.fillBoard(cells2);
		ThreeByThreeBoard board3 = BoardUtil.fillBoard(cells3);
		
		assertTrue(board1.getCellAt(1, 1).isAlive());
		assertTrue(board2.getCellAt(1, 1).isAlive());
		assertTrue(board3.getCellAt(1, 1).isAlive());
		
		Evaluator evaluator = new EvaluatorImpl();
		assertFalse(evaluator.evaluate(board1));
		assertEquals(evaluator.getNumAliveNeighbors(board1), 8);

		assertFalse(evaluator.evaluate(board2));
		assertEquals(evaluator.getNumAliveNeighbors(board2), 7);
		
		assertFalse(evaluator.evaluate(board3));
		assertEquals(evaluator.getNumAliveNeighbors(board3), 5);
	}
	
	@Test
	public void testEvaluate3By3_AliveAndFewerThanTwoAliveNeighbors() throws Exception {
		List<Cell> cellsOneAliveNeighbor = Arrays.asList(new Cell(false), new Cell(false), new Cell(false), 
				  						  				 new Cell(true), new Cell(true), new Cell(false), 
				  						  				 new Cell(false), new Cell(false), new Cell(false));
		List<Cell> cellsNoAliveNeighbors = Arrays.asList(new Cell(false), new Cell(false), new Cell(false), 
				  						                 new Cell(false), new Cell(true), new Cell(false), 
				  						                 new Cell(false), new Cell(false), new Cell(false));
		
		ThreeByThreeBoard board1 = BoardUtil.fillBoard(cellsOneAliveNeighbor);
		ThreeByThreeBoard board2 = BoardUtil.fillBoard(cellsNoAliveNeighbors);
		
		assertTrue(board1.getCellAt(1, 1).isAlive());
		assertTrue(board2.getCellAt(1, 1).isAlive());
		
		Evaluator evaluator = new EvaluatorImpl();
		assertFalse(evaluator.evaluate(board1));
		assertEquals(evaluator.getNumAliveNeighbors(board1), 1);
		
		assertFalse(evaluator.evaluate(board2));
		assertEquals(evaluator.getNumAliveNeighbors(board2), 0);
	}
	
	@Test
	public void testEvaluate3By3_AliveAndTwoOrThreeAliveNeighbors() throws Exception {
		List<Cell> cellsTwoAliveNeighbors = Arrays.asList(new Cell(false), new Cell(true), new Cell(false), 
				  						  				 new Cell(true), new Cell(true), new Cell(false), 
				  						  				 new Cell(false), new Cell(false), new Cell(false));
		List<Cell> cellsThreeAliveNeighbors = Arrays.asList(new Cell(true), new Cell(false), new Cell(false), 
				  						                 new Cell(false), new Cell(true), new Cell(false), 
				  						                 new Cell(true), new Cell(true), new Cell(false));
		
		ThreeByThreeBoard board1 = BoardUtil.fillBoard(cellsTwoAliveNeighbors);
		ThreeByThreeBoard board2 = BoardUtil.fillBoard(cellsThreeAliveNeighbors);
		
		assertTrue(board1.getCellAt(1, 1).isAlive());
		assertTrue(board2.getCellAt(1, 1).isAlive());
		
		Evaluator evaluator = new EvaluatorImpl();
		assertTrue(evaluator.evaluate(board1));
		assertEquals(evaluator.getNumAliveNeighbors(board1), 2);
		
		assertTrue(evaluator.evaluate(board2));
		assertEquals(evaluator.getNumAliveNeighbors(board2), 3);
	}
	
	@Test
	public void testEvaluate3By3_DeadButWithThreeAliveNeighbors() throws Exception {
		List<Cell> cellsThreeAliveNeighbors = Arrays.asList(new Cell(false), new Cell(true), new Cell(false), 
				  						  				 	new Cell(true), new Cell(false), new Cell(false), 
				  						  				 	new Cell(false), new Cell(false), new Cell(true));
		
		ThreeByThreeBoard board1 = BoardUtil.fillBoard(cellsThreeAliveNeighbors);
		
		assertFalse(board1.getCellAt(1, 1).isAlive());
		
		Evaluator evaluator = new EvaluatorImpl();
		assertTrue(evaluator.evaluate(board1));
		assertEquals(evaluator.getNumAliveNeighbors(board1), 3);
	}
	
	@Test
	public void testEvaluate() throws Exception{
		List<Cell> cellsThreeAliveNeighbors = Arrays.asList(new Cell(false), new Cell(true), new Cell(false), 
	  				 										new Cell(true), new Cell(false), new Cell(false), 
	  				 										new Cell(false), new Cell(false), new Cell(true));
		
		String expected1 = "YYY" +
				   		   "YYY" +
				   		   "YYY";
		
		Board board = BoardUtil.fillBoard(3, 3, cellsThreeAliveNeighbors);
		
		Evaluator evaluator = new EvaluatorImpl();
		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(evaluator.evaluate(board).toString()));
	}
	
	@Test
	public void testEvaluate_AllDead() throws Exception{
		List<Cell> cells = Arrays.asList(new Cell(false), new Cell(false), new Cell(false), 
	  				 					 new Cell(false), new Cell(false), new Cell(false), 
	  				 					 new Cell(false), new Cell(false), new Cell(false));
		
		String expected1 = "NNN" +
				   		   "NNN" +
				   		   "NNN";
		
		Board board = BoardUtil.fillBoard(3, 3, cells);
		
		Evaluator evaluator = new EvaluatorImpl();
		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(evaluator.evaluate(board).toString()));
	}
	
	@Test
	public void testEvaluate_ConditionOne() throws Exception{
		List<Cell> cells = Arrays.asList(new Cell(true), new Cell(false), new Cell(false), 
	  				 	   				 new Cell(false), new Cell(false), new Cell(false), 
	  				 	   				 new Cell(false), new Cell(false), new Cell(false));
		// cell 0,0 is target of test
		String expected1 = "NNN" +
				   		   "NNN" +
				   		   "NNN";
		
		Board board = BoardUtil.fillBoard(3, 3, cells);
		
		Evaluator evaluator = new EvaluatorImpl();
		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(evaluator.evaluate(board).toString()));
	}
	
	@Test
	public void testEvaluate_ConditionTwo() throws Exception{
		List<Cell> cells = Arrays.asList(new Cell(false), new Cell(true), new Cell(false), new Cell(false), 
	  				 					 new Cell(false), new Cell(true), new Cell(true), new Cell(false),  
	  				 					 new Cell(false), new Cell(false), new Cell(false), new Cell(false),
										 new Cell(false), new Cell(false), new Cell(false), new Cell(false));

		// cell 1,1 is target of test
		String expected1 = "NYYN" +
				   		   "NYYN" +
				   		   "NNNN" +
				   		   "NNNN";
		
		Board board = BoardUtil.fillBoard(4, 4, cells);
		
		Evaluator evaluator = new EvaluatorImpl();
		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(evaluator.evaluate(board).toString()));
	}
	
	@Test
	public void testEvaluate_ConditionThree() throws Exception{
		List<Cell> cells = Arrays.asList(new Cell(false), new Cell(true), new Cell(false), new Cell(false), 
	  				 					 new Cell(true), new Cell(true), new Cell(true), new Cell(false),  
	  				 					 new Cell(false), new Cell(true), new Cell(false), new Cell(false),
										 new Cell(false), new Cell(false), new Cell(false), new Cell(false));

		// cell 1,1 is target of test
		String expected1 = "YYYN" +
				   		   "YNYN" +
				   		   "YYYN" +
				   		   "NNNN";
		
		Board board = BoardUtil.fillBoard(4, 4, cells);
		
		Evaluator evaluator = new EvaluatorImpl();
		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(evaluator.evaluate(board).toString()));
	}
	
	@Test
	public void testEvaluate_ConditionFour() throws Exception{
		List<Cell> cells = Arrays.asList(new Cell(false), new Cell(true), new Cell(false), new Cell(false), 
	  				 					 new Cell(true), new Cell(false), new Cell(false), new Cell(false),  
	  				 					 new Cell(false), new Cell(true), new Cell(false), new Cell(false),
										 new Cell(false), new Cell(false), new Cell(false), new Cell(false));

		// cell 1,1 is target of test
		String expected1 = "NNNN" +
				   		   "YYNN" +
				   		   "NNNN" +
				   		   "NNNN";
		
		Board board = BoardUtil.fillBoard(4, 4, cells);
		
		Evaluator evaluator = new EvaluatorImpl();
		assertEquals(expected1, StringUtil.stripSpaceAndNewLineCharacters(evaluator.evaluate(board).toString()));
	}
}
