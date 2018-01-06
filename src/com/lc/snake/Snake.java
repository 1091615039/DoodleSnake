package com.lc.snake;

import java.util.LinkedList;

public class Snake {

	private LinkedList<Node> body = new LinkedList<>();
	
//	public Node eat(Node food) {
//		if(isNeighbor(food, this.getHead())) {
//			body.addFirst(food);
//			return food;
//		}
//		return null;
//	}
	//贪吃蛇移动一步
	public Node move(Direction direction) {
		Node newHead = null;
		switch (direction) {
		case UP:
			newHead = new Node(this.getHead().getX(),this.getHead().getY()-1);
			break;
		case DOWN:
			newHead = new Node(this.getHead().getX(),this.getHead().getY()+1);
			break;
		case LEFT:
			newHead = new Node(this.getHead().getX()-1,this.getHead().getY());
			break;
		case RIGHT:
			newHead = new Node(this.getHead().getX()+1,this.getHead().getY());
			break;
		}
		body.addFirst(newHead);
		Node oldTail = body.getLast();
		body.removeLast();
		return oldTail;
 
	}
	
	public Node getHead() {
		return body.getFirst();
	}
	
	public Node addTail(Node area) {
		this.body.addLast(area);
		return area;
	}

	public LinkedList<Node> getBody() {
		return body;
	}
	
//	private boolean isNeighbor(Node a,Node b) {
//		return Math.abs(a.getX()-b.getX())+Math.abs(a.getY()-b.getY()) == 1;
//	}
}
