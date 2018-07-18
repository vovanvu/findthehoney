package mainmenu.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AboutFrame extends JFrame {
	public AboutFrame() {
		setTitle("About");
		setSize(700, 400);

		JPanel p = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				ImageIcon help = new ImageIcon("images/about.png");
				g.drawImage(help.getImage(), 0, 0, null);
			}
		};
		add(p);
		setVisible(true);
		setLocationRelativeTo(null);
}
}