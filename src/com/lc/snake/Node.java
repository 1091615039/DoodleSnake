package com.lc.snake;
//节点类，对应窗体上的小网格
public class Node {
	
	private final int x;
	private final int y;
	
	public Node(int x,int y) {
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
