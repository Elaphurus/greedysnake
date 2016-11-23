package cn.mingzhehu.greedy.snake;

import java.util.LinkedList;

public class Snake {

	private LinkedList<Node> body = new LinkedList<>();

	public LinkedList<Node> getBody() {
		return body;
	}

	public void setBody(LinkedList<Node> body) {
		this.body = body;
	}

	/**
	 * @param direction
	 * @return tail node
	 */
	public Node move(Direction direction) {
		Node tail = getTail();
		Node head = getHead();
		int x = head.getX();
		int y = head.getY();

		Node newHead;
		switch (direction) {
		case UP:
			newHead = new Node(x, y - Constant.NODE_SIZE.getValue());
			body.addFirst(newHead);
			break;
		case RIGHT:
			newHead = new Node(x + Constant.NODE_SIZE.getValue(), y);
			body.addFirst(newHead);
			break;
		case DOWN:
			newHead = new Node(x, y + Constant.NODE_SIZE.getValue());
			body.addFirst(newHead);
			break;
		case LEFT:
			newHead = new Node(x - Constant.NODE_SIZE.getValue(), y);
			body.addFirst(newHead);
			break;
		}
		body.removeLast();

		return tail;
	}

	public Node getHead() {
		return body.getFirst();
	}

	public Node getTail() {
		return body.getLast();
	}

	/**
	 * Judge whether this move ate food.
	 * 
	 * @param food
	 * @return food when ate, null when didn't
	 */
	public Node eat(Node food) {
		if (isNeighbor(getHead(), food)) {
			body.addFirst(food);
			return food;
		} else {
			return null;
		}
	}

	private boolean isNeighbor(Node a, Node b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) < Constant.NODE_SIZE.getValue();
	}

}