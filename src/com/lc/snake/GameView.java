package com.lc.snake;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
//游戏视图类
public class GameView {

	private Grid grid;
	private JPanel canvas;
	public Boolean useGrid = true;
	
	public JPanel getCanvas() {
		return canvas;
	}

	public GameView(Grid grid) {
		this.grid = grid;
	}
	//初始化游戏窗口
	public void init() {
		canvas = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics graphics) {
				drawGridBackground(graphics);
				drawSnake(graphics,grid.getSnake());
				drawFood(graphics,grid.getFood());
			}
		};
	}
	//重绘窗体
	public void draw() {
		canvas.repaint();
	}
	//绘制食物
	public void drawFood(Graphics graphics, Node food) {
		drawCircle(graphics, food, Settings.DEFAULT_FOOD_COLOR);
	}
	//绘制蛇
	public void drawSnake(Graphics graphics, Snake snake) {
		for(int i=0;i<snake.getBody().size();i++) {
			drawSquare(graphics, snake.getBody().get(i), Settings.DEFAULT_SNAKE_COLOR);
		}
	}
	//绘制背景
	public void drawGridBackground(Graphics graphics) {
		if(useGrid) {
			drawGridBackgroundWithGrid(graphics);
		}else {
			drawGridBackgroundWithoutGrid(graphics);
		}
	}
	//绘制带网格的背景
	public void drawGridBackgroundWithGrid(Graphics graphics) {
		for(int i=0;i<Settings.DEFAULT_GRID_WIDTH;i++) {
			for(int j=0;j<Settings.DEFAULT_GRID_HEIGHT;j++) {
				graphics.setColor(Settings.DEFAULT_BACKGROUND_COLOR);
				graphics.fillRect(i*Settings.DEFAULT_NODE_SIZE, j*Settings.DEFAULT_NODE_SIZE, Settings.DEFAULT_NODE_SIZE, Settings.DEFAULT_NODE_SIZE);
				graphics.setColor(Color.GRAY);
				graphics.drawRect(i*Settings.DEFAULT_NODE_SIZE, j*Settings.DEFAULT_NODE_SIZE, Settings.DEFAULT_NODE_SIZE, Settings.DEFAULT_NODE_SIZE);
			}
		}
	}
	//绘制不带网格的背景
	public void drawGridBackgroundWithoutGrid(Graphics graphics) {
		graphics.setColor(Settings.DEFAULT_BACKGROUND_COLOR);
		graphics.fillRect(0, 0, Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT);
	}
	//绘制矩形（网格）
	public void drawSquare(Graphics graphics, Node squareArea,Color color) {
		graphics.setColor(color);
		int size = Settings.DEFAULT_NODE_SIZE;
		graphics.fillRect(squareArea.getX()*size, squareArea.getY()*size, size-1, size-1);
	}
	//绘制圆形（食物）
	public void drawCircle(Graphics graphics, Node squareArea,Color color) {
		graphics.setColor(color);
		int size = Settings.DEFAULT_NODE_SIZE;
		graphics.fillOval(squareArea.getX()*size, squareArea.getY()*size, size, size);
	}
}
