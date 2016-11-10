package cn.mingzhehu.greedy.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Draw {

	private final Grid grid;

	/**
	 * Constructor
	 * 
	 * @param grid game base map
	 */
	public Draw(Grid grid) {
		this.grid = grid;
	}

	public Grid getGrid() {
		return grid;
	}

	private JPanel canvas;

	/**
	 * Initialize map articles.
	 * <ul>
	 * <li>grid background
	 * <li>snake
	 * <li>food
	 * </ul>
	 */
	public void init() {
		canvas = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics graphics) {
				drawGridBackground(graphics);
				drawSnake(graphics, grid.getSnake());
				drawFood(graphics, grid.getFood());
			}
		};
	}

	public JPanel getCanvas() {
		return canvas;
	}

	public void fresh() {
		canvas.repaint();
	}

	public void drawGridBackground(Graphics graphics) {
		int width = grid.getWidth();
		int height = grid.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Node node = new Node(x, y);
				drawSquare(graphics, node, Color.gray);
			}
		}
	}

	public void drawSnake(Graphics graphics, Snake snake) {
		LinkedList<Node> body = snake.getBody();
		for (Node node : body) {
			drawSquare(graphics, node, Color.cyan);
		}
	}

	public void drawFood(Graphics graphics, Node food) {
		drawCircle(graphics, food, Color.orange);
	}

	private void drawSquare(Graphics graphics, Node squareArea, Color color) {
		graphics.setColor(color);
		int size = Constant.NODE_SIZE.getValue();
		graphics.fillRect(squareArea.getX(), squareArea.getY(), size - 1,
				size - 1);
	}

	private void drawCircle(Graphics graphics, Node squareArea, Color color) {
		graphics.setColor(color);
		int size = Constant.NODE_SIZE.getValue();
		graphics.fillOval(squareArea.getX(), squareArea.getY(), size, size);
	}

}