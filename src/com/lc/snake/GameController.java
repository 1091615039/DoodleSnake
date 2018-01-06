package com.lc.snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//游戏控制类
public class GameController implements KeyListener,Runnable,ActionListener{

	private Grid grid;
    private GameView gameView;
    private boolean run = true;
    
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGame = new JMenu();
	private JMenuItem menuItemGameStart = new JMenuItem();
	private JMenuItem menuItemGameExit = new JMenuItem();

	private JMenu menuHelp = new JMenu();
	private JMenuItem reducedifficult = new JMenuItem();
	private JMenuItem increasedifficult = new JMenuItem();
	private JMenuItem recoverdifficult = new JMenuItem();
	private JMenuItem removeOrUseGrid = new JMenuItem();

	private JMenu menuAbout = new JMenu();
	private JMenuItem menuItemTips = new JMenuItem();
	private JMenuItem menuItemAbout = new JMenuItem();

    public JMenuBar getMenuBar() {
		return menuBar;
	}

	public GameController(Grid grid, GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
    }
	//初始化菜单栏
	public void initMenu() {
		//设置开始与结束
		menuItemGameStart.setText("Start Again(A)");
		menuItemGameStart.setMnemonic('A');
		menuItemGameStart.addActionListener(this);
		menuItemGameExit.setText("End-Game(B)");
		menuItemGameExit.setMnemonic('B');
		menuItemGameExit.addActionListener(this);
		menuGame.setText("Option");
		menuGame.add(menuItemGameStart);
		menuGame.add(menuItemGameExit);
		//设置增加或减少难度以及是否使用网格背景
		menuHelp.setText("operate");
		increasedifficult.setText("Increase the difficulty(W)");
		increasedifficult.addActionListener(this);
		reducedifficult.setText("Reduce the difficulty(S)");
		reducedifficult.addActionListener(this);
		recoverdifficult.setText("Difficulty of initialization(D)");
		recoverdifficult.addActionListener(this);
		removeOrUseGrid.setText("Remove/Use grid(X)");
		removeOrUseGrid.addActionListener(this);
		menuHelp.add(increasedifficult);
		menuHelp.add(reducedifficult);
		menuHelp.add(recoverdifficult);
		menuHelp.add(removeOrUseGrid);
		//Help菜单
		menuAbout.setText("Help");
		menuItemTips.setText("Tips");
		menuItemTips.addActionListener(this);
		menuItemAbout.setText("About");
		menuItemAbout.addActionListener(this);
		menuAbout.add(menuItemTips);
		menuAbout.add(menuItemAbout);

		menuBar.add(menuGame);
		menuBar.add(menuHelp);
		menuBar.add(menuAbout);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	//键盘事件监听
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//上下左右操作
		if(key == KeyEvent.VK_LEFT) {
			grid.changeDirection(Direction.LEFT);
			grid.nextRound();
			gameView.draw();
		}else if(key == KeyEvent.VK_RIGHT) {
			grid.changeDirection(Direction.RIGHT);
			grid.nextRound();
			gameView.draw();
		}else if(key == KeyEvent.VK_UP) {
			grid.changeDirection(Direction.UP);
			grid.nextRound();
			gameView.draw();
		}else if(key == KeyEvent.VK_DOWN) {
			grid.changeDirection(Direction.DOWN);
			grid.nextRound();
			gameView.draw();
		//空格：暂停与继续
		}else if(key == KeyEvent.VK_SPACE){
			if(run) {	
				run = false;
			}else {
				run = true;
				new Thread(this).start();
			}
		//enter：暂停或结束游戏时重开游戏
		}else if(key == KeyEvent.VK_ENTER) {
			if(run == false) {
				grid.init();
				run = true;
				new Thread(this).start();
			}
		//重开游戏
		}else if(key == KeyEvent.VK_A) {
			initGame();
		//退出游戏
		}else if(key == KeyEvent.VK_B) {
			System.exit(0);
		}else if(key == KeyEvent.VK_W) {
			increaseDifficult();
		}else if(key == KeyEvent.VK_S) {
			reduceDifficult();
		//恢复初始难度
		}else if(key == KeyEvent.VK_D) {
			Settings.DEFAULT_MOVE_INTERVAL = 400;
		}else if(key == KeyEvent.VK_X) {
			useGridOrNot();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	/*
	 * 控制蛇移动的线程
	 */
	@Override
	public void run() {
		while(run) {		
				try {
					Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
				} catch (InterruptedException e) {
					break;
				}			
				if(grid.nextRound()) {
					gameView.draw();
				}else {
					run = false;
					showGameOverMessage();
				}
			}
		}
	
	/*
	 * 鼠标事件监听
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuItemGameStart) {
				initGame();
		}else if(e.getSource() == menuItemGameExit) {
			System.exit(0);
		}else if(e.getSource() == reducedifficult) {
			reduceDifficult();
		}else if(e.getSource() == increasedifficult) {
			increaseDifficult();
		}else if(e.getSource() == recoverdifficult) {
			Settings.DEFAULT_MOVE_INTERVAL = 400;
		}else if(e.getSource() == menuItemTips) {
			run = false;
			new TipsFrame().setVisible(true);
		}else if(e.getSource() == menuItemAbout) {
			run = false;
			new AboutFrame().setVisible(true);
		}else if(e.getSource() == removeOrUseGrid) {
			useGridOrNot();
		}
	}
	//是否使用网格背景
	public void useGridOrNot() {
		if(gameView.useGrid) {
			gameView.useGrid = false;			
		}else {
			gameView.useGrid = true;
		}
		gameView.draw();
	}
	//游戏结束弹窗
	public void showGameOverMessage() {
		JOptionPane.showMessageDialog(null, "Game Over", "Game over", JOptionPane.INFORMATION_MESSAGE);
	}
	//初始化游戏
	public void initGame() {
		if(run) {
			grid.init();
		}else {
			grid.init();
			run = true;
			new Thread(this).start();
		}
	}
	//增加难度
	public void increaseDifficult() {
		if(Settings.DEFAULT_MOVE_INTERVAL - 100 > 0) {
			Settings.DEFAULT_MOVE_INTERVAL -= 100;
		}
	}
	//降低难度
	public void reduceDifficult() {
		Settings.DEFAULT_MOVE_INTERVAL += 100;
	}

}
