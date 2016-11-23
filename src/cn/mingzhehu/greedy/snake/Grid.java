package cn.mingzhehu.greedy.snake;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Game container having snake, food and game events & logics.
 * 
 * @author mingzhe_hu
 */
public class Grid {

	// map size
	private final int width;
	private final int height;
	// map grid point property, true when covered by snake body
	public final boolean status[][];
	// the number of food the snake ate before it dead
	public int score = 0;

	private Snake snake;

	private Node food;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Snake getSnake() {
		return snake;
	}

	public Node getFood() {
		return food;
	}

	// initial moving direction: right
	public Direction snakeDirection = Direction.RIGHT;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		status = new boolean[width][height];

		initSnake();

		do {
			createFood();
		} while (status[food.getX()][food.getY()]);
	}

	private Snake initSnake() {
		snake = new Snake();
		LinkedList<Node> body = new LinkedList<Node>();
		// initial length of snake: 1/4 width of grid
		// with head in the center, body in the left
		int length = width / 4;
		for (int i = 0; i * Constant.NODE_SIZE.getValue() < length; i++) {
			int x = width / 2 - i * Constant.NODE_SIZE.getValue();
			int y = height / 2;
			Node node = new Node(x, y);
			body.add(node);
			status[x][y] = true;
		}
		snake.setBody(body);

		return snake;
	}

	/**
	 * Create food in the area of grid.
	 * 
	 * @return food
	 */
	public Node createFood() {
		// food cannot be around the border
		int x = getRandom(10, width - 10);
		int y = getRandom(10, height - 10);

		food = new Node(x, y);
		return food;
	}

	public void changeDirection(Direction newDirection) {
		if (isAlive()) {
			if (snakeDirection.compatibleWith(newDirection)) {
				snakeDirection = newDirection;
				changeStatus(snake.move(newDirection));
				if (changeStatus(snake.getHead())) {
					if (snake.eat(food) != null) {
						score++;
						changeStatus(food);
						do {
							createFood();
						} while (status[food.getX()][food.getY()]);
					} else {
					}
				} else {
					showGameOverMessage();
				}
			} else {
			}
		} else {
			showGameOverMessage();
		}
	}

	private int getRandom(int min, int max) {
		Random random = new Random();
		int n = 10 * random.nextInt((max - min) / 10) + min;
		return n;
	}

	/**
	 * True & false exchange.
	 * 
	 * @param node
	 * @return false when the snake has hit the wall
	 */
	private boolean changeStatus(Node node) {
		int x = node.getX();
		int y = node.getY();
		if (x > width || y > height || x == width || y == height || x < 0 || y < 0 || x == 0 || y == 0) {
			return false;
		} else {
			if (status[x][y]) {
				status[x][y] = false;
			} else {
				status[x][y] = true;
			}
			return true;
		}
	}

	public boolean isAlive() {
		Node head = snake.getHead();
		int x = head.getX();
		int y = head.getY();
		if (intoWall(x, y, width, height) || eatSelf(x, y, snake)) {
			return false;
		} else {
			return true;
		}
	}

	private boolean intoWall(int x, int y, int borderX, int borderY) {
		if (x > borderX || y > borderY || x == borderX || y == borderY || x == 0 || y == 0 || x < 0 || y < 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean eatSelf(int headX, int headY, Snake snake) {
		for (int i = 2; i < snake.getBody().size(); i++) {
			Node node = snake.getBody().get(i);
			int x = node.getX();
			int y = node.getY();
			if (Math.abs(headX - x) + Math.abs(headY - y) < 1) {
				return true;
			} else {
			}
		}
		return false;
	}

	public void showGameOverMessage() {
		JOptionPane.showMessageDialog(null, "Greedy End !" + "\n" + "Your Score : " + score + "\n" + "Bug feedback : humingzhework@163.com", "Greedy Snake",
				JOptionPane.INFORMATION_MESSAGE);
	}

}