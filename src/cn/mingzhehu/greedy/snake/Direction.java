package cn.mingzhehu.greedy.snake;

public enum Direction {

	UP(0), RIGHT(1), DOWN(2), LEFT(3);

	private final int directionCode;

	Direction(int directionCode) {
		this.directionCode = directionCode;
	}

	public int directionCode() {
		return directionCode;
	}

	/**
	 * Judge whether new pressed direction key is compatible with the old one.
	 * <p>
	 * <i>For example: </i>right key is forbidden when moving to the left.
	 * 
	 * @param newDirection
	 * @return
	 */
	public boolean compatibleWith(Direction newDirection) {
		if (Math.abs(directionCode - newDirection.directionCode) == 2) {
			return false;
		} else {
			return true;
		}
	}

}