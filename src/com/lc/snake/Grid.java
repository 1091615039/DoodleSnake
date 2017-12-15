package com.lc.snake;

import java.util.Arrays;
import java.util.Random;

public class Grid {
	
	private final boolean status[][];
	private Snake snake;
	private final int width;
	private final int height;
	private Node food;
	
	private Direction snakeDirection; 
	
	public Snake getSnake() {
		return snake;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Node getFood() {
		return food;
	}

	public Grid(int width,int height) {
		this.width = width;
		this.height = height;	
		status = new boolean[width][height];	
		init();
	}
	
	public void init() {
		for(int i=0;i<width;i++) {
			Arrays.fill(status[i], false);
		}
		snakeDirection = Direction.LEFT;
		initSnake();
		createFood();	
	}

	public Node createFood() {
		Random r = new Random();
		int x,y;
		do {
			x = r.nextInt(width);
			y = r.nextInt(height);
		}while(status[x][y]);
		food = new Node(x,y);
		return food;
	}

	public Snake initSnake() {
		snake = new Snake();
		for(int i=0;i<Math.min(width, height)/3;i++) {
			snake.getBody().addLast(new Node(width/2+i,height/2));
			status[width/2+i][height/2] = true;
		}
		return snake;
	}
	
	public boolean nextRound() {
		Node node = snake.move(snakeDirection);
		if(validPosition(snake.getHead())) {
			status[snake.getHead().getX()][snake.getHead().getY()] = true;
			if(isFood(snake.getHead())) {
				snake.getBody().addLast(node);		
				status[node.getX()][node.getY()] = true;
				createFood();
			}else {
				status[node.getX()][node.getY()] = false;
			}	
			return true;
		}
		return false;
	}
	
	public Direction changeDirection(Direction newDirection) {
		if(snakeDirection.compatibleWith(newDirection)) {
			snakeDirection = newDirection;
		}
		return snakeDirection;
	}
	
	public Direction getSnakeDirection() {
		return snakeDirection;
	}

	public boolean validPosition(Node area) {
		int x = area.getX();
		int y = area.getY();
		return x>=0 && x<width && y>=0 && y<height && !status[x][y];
	}
	
	public boolean isFood(Node area) {
		int x=area.getX();
		int y=area.getY();
		return x==food.getX() && y==food.getY();
	}
}
