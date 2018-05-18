package view;

import java.util.ArrayList;

import javax.swing.JFrame;

public class InGameFrame extends JFrame {
	public InGameFrame() {
		setTitle("Find The Honey");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(780, 430);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new InGameFrame();
	}
}
