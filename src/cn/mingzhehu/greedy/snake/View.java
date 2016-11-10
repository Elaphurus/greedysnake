package cn.mingzhehu.greedy.snake;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Game entrance.
 * 
 * @author mingzhe_hu
 */
public class View {

	/**
	 * Invoke Class <code>Draw</code>, initialize grid, snake, food & game
	 * controller, set window properties.
	 */
	public void init() {
		Grid grid = new Grid(Constant.GRID_SIZE.getValue(),
				Constant.GRID_SIZE.getValue());
		Draw draw = new Draw(grid);
		draw.init();
		draw.getCanvas().setPreferredSize(
				new Dimension(grid.getWidth(), grid.getHeight()));

		JFrame window = new JFrame("Greedy Snake");
		Container contentPane = window.getContentPane();
		contentPane.add(draw.getCanvas(), BorderLayout.CENTER);

		Controller controller = new Controller(draw);
		window.addKeyListener(controller);
		new Thread(controller).start();

		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		View view = new View();
		view.init();
	}

}