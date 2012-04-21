package gameOfLife.model;

public class ThreeByThreeBoard extends Board{

	private ThreeByThreeBoard(int height, int width) {
		super(height, width);
	}
	
	public ThreeByThreeBoard() {
		super(3, 3);
	}

}
