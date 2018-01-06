package com.lc.snake;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
//“关于”窗口
public class AboutFrame extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel label1 = new JLabel("version : 1.0");
	private JLabel label2 = new JLabel("developer:LiuChang");
	private JButton but = new JButton("OK");

	public AboutFrame() {
		setTitle("About");
		setSize(235, 140);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		label1.setBounds(50, 12, 120, 20);
		label2.setBounds(50, 37, 120, 20);
		but.setBounds(80, 67, 80, 20);
		but.addActionListener(this);
		add(label1);
		add(label2);
		add(but);
		setModal(true);
	}
	//释放内存
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
