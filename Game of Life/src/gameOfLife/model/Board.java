package gameOfLife.model;

import org.apache.commons.lang3.Validate;

import gameOfLife.util.MathUtil;

public class Board {

	private Cell[][] board;
	private int height;
	private int width;

	private static final int MIN_HEIGHT = 3;
	private static final int MIN_WIDTH = 3;

	public Board(int height, int width) {
		Validate.isTrue(height >= MIN_HEIGHT, "Height must be at least " + MIN_HEIGHT);
		Validate.isTrue(width >= MIN_WIDTH, "Width must be at least " + MIN_WIDTH);
		
		this.board = new Cell[height][width];
		this.height = height;
		this.width = width;
	}

	public Cell getCellAt(int x, int y) {
		return this.board[x][y];
	}

	public void setCellAt(Cell cell, int x, int y) {
		this.board[x][y] = cell;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public ThreeByThreeBoard getCellAndNeighbors(int x, int y){
		ThreeByThreeBoard b = new ThreeByThreeBoard();
		Nav[] navs = Nav.values();
		int k = 0;
		
		for (int i = 0; i < b.getHeight(); i++) {
			for(int j = 0; j < b.getWidth(); j++){
				Cell c = this.getCellAt(MathUtil.makePositiveInt((navs[k].getX() + x + this.getHeight()) % (this.getHeight())), 
						           MathUtil.makePositiveInt((navs[k].getY() + y + this.getWidth()) % (this.getWidth())));
				b.setCellAt(c, navs[k].getX() + 1, navs[k].getY() + 1);
				k++;
			}
		}
		return b;
	}
	
	public String toString(){
		String s = new String();
		
		for(int i = 0; i < this.height; i++){
			for(int j = 0; j < this.width; j++){
				s = s + this.getCellAt(i, j).toString() + " ";
			}
			s = s + "\n";
		}
		return s;
	}
}
