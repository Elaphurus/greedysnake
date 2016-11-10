package cn.mingzhehu.greedy.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Game controller, including:
 * <ul>
 * <li>key press listener
 * <li>game thread, keep the snake moving
 * </ul>
 * 
 * @author mingzhe_hu
 */
public class Controller implements KeyListener, Runnable {

	private final Draw draw;

	private final Grid grid;

	/**
	 * Constructor
	 * 
	 * @param draw
	 */
	public Controller(Draw draw) {
		this.draw = draw;
		this.grid = draw.getGrid();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP:
			grid.changeDirection(Direction.UP);
			break;
		case KeyEvent.VK_RIGHT:
			grid.changeDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_DOWN:
			grid.changeDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			grid.changeDirection(Direction.LEFT);
			break;
		}

		draw.fresh();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void run() {
		while (grid.isAlive()) {
			// update speed(moving interval) according to score
			// with the minimum of 50
			int speed = Constant.INITIAL_SPEED.getValue() - grid.score * 10;
			if (speed < 50) {
				speed = 50;
			}
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			grid.changeDirection(grid.snakeDirection);
			draw.fresh();
		}
	}

}