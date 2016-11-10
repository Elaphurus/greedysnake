package cn.mingzhehu.greedy.snake;

public enum Constant {
	NODE_SIZE(10), GRID_SIZE(300), INITIAL_SPEED(200);

	private final int constantValue;

	Constant(int constantValue) {
		this.constantValue = constantValue;
	}

	public int getValue() {
		return constantValue;
	}
}