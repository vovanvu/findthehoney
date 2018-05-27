package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpFrame extends JFrame {
	public HelpFrame() {
		setTitle("Help");
		setSize(782, 600);

		JPanel p = new JPanel() {
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				ImageIcon help = new ImageIcon("resources/image.png");
				g.drawImage(help.getImage(), 0, 0, null);

			}
		};
		this.add(p);
		setVisible(true);
		setLocationRelativeTo(null);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}