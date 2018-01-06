package com.lc.snake;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
//主函数类
public class SnakeApp{
	
	private GameView gameview;
	private Grid grid;
	private GameController gameController;

	public SnakeApp() {
		grid = new Grid(Settings.DEFAULT_GRID_WIDTH/Settings.DEFAULT_NODE_SIZE, Settings.DEFAULT_GRID_HEIGHT/Settings.DEFAULT_NODE_SIZE);
		JFrame window = new JFrame("̰Doodle Snake Mini-Game");
		Container contentPane = window.getContentPane();	
		gameview = new GameView(grid);
		gameview.init();
		gameview.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT));
		contentPane.add(gameview.getCanvas(), BorderLayout.CENTER);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameController = new GameController(grid,gameview);
		gameController.initMenu();
		window.addKeyListener(gameController);
		window.setJMenuBar(gameController.getMenuBar());
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		new Thread(gameController).start();
	}
	
	public static void main(String[] args) {
		new SnakeApp();
	}
}
