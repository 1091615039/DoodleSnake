package com.lc.snake;

//import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
//“Tips”窗口
public class TipsFrame extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel label1 = new JLabel("enter direction key to increase");
	private JLabel label2 = new JLabel("or decrease speed.");
	private JLabel label3 = new JLabel("space : pause/continue");
	private JLabel label4 = new JLabel("(you can still move when pause and");
	private JLabel label5 = new JLabel("game will never end whatever you do)");
	private JLabel label6 = new JLabel("enter : restart game when ");
	private JLabel label7 = new JLabel("pause or game over");
	
	public TipsFrame() {
		setTitle("Tips");
		setSize(265, 230);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
//		label1.setFont(new Font("宋体", Font.PLAIN, 12));
//		label2.setFont(new Font("宋体", Font.PLAIN, 12));
		label1.setBounds(25, 12, 180, 20);
		label2.setBounds(25, 37, 190, 20);
		label3.setBounds(25, 62, 180, 20);
		label4.setBounds(25, 87, 250, 20);
		label5.setBounds(25, 112, 250, 20);
		label6.setBounds(25, 137, 180, 20);
		label7.setBounds(25, 161, 180, 20);
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		add(label5);
		add(label6);
		add(label7);
		setModal(true);
	}

}
