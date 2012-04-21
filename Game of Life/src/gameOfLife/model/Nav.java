package gameOfLife.model;

public enum Nav {

	NORTH(-1, 0), NORTHEAST(-1, 1), EAST(0, 1), SOUTHEAST(1, 1), SOUTH(1, 0), SOUTHWEST(
			1, -1), WEST(0, -1), NORTHWEST(-1, -1), CENTER(0, 0);

	private int x;
	private int y;

	private Nav(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
