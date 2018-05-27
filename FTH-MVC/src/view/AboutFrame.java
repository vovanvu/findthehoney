package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class AboutFrame extends JFrame {
	JTextArea text;

	public AboutFrame() throws FileNotFoundException {
		setTitle("About");
		setSize(800, 350);
		text = new JTextArea();
		setLayout(new BorderLayout());

		FileInputStream fis = new FileInputStream("about.txt");
		Scanner scanner = new Scanner(fis);

		while (scanner.hasNextLine()) {
			text.setText(text.getText() + scanner.nextLine() + "\n");
		}

		scanner.close();

		text.setFont(new Font("Arial", Font.ITALIC, 17));
		text.setEditable(false);
		add(text, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
