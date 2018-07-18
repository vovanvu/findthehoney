package mainmenu.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HighScoreFrame extends JFrame {
	private JTextArea scoreTableTextArea;

	public HighScoreFrame() {
		setTitle("HighScores");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}

		});
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		scoreTableTextArea = new JTextArea(400, 300);
		scoreTableTextArea.setFocusable(false);
		JScrollPane jScrollPane = new JScrollPane(scoreTableTextArea);
		//
		readFromFile();
		//
		panel.add(back, BorderLayout.SOUTH);
		panel.add(jScrollPane, BorderLayout.CENTER);
		add(panel);
		//
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formatTextFileWhenClose();
				e.getWindow().dispose();
			}

		});
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				formatTextFileWhenClose();
				dispose();
			}
		});
	}
	// read from score text file and append it to scoreTableTextArea for user can
		// see it
	private void readFromFile() {
		FileReader fr = null;
		try {
			fr = new FileReader("file.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		int i;
		try {
			while ((i = br.read()) != -1) {
				String s = Character.toString((char) i);
				scoreTableTextArea.append(s);
				;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// remove all non-content line, if not it will be error in the next time read
	// file
	private void formatTextFileWhenClose() {
		File file1 = new File("file.txt");
		File file2 = new File("file2.txt");
		Scanner sFile;
		PrintWriter writer;

		try {

			sFile = new Scanner(new File("file.txt"));
			writer = new PrintWriter("file2.txt");

			while (sFile.hasNext()) {
				String line = sFile.nextLine();
				if (!line.isEmpty()) {
					writer.write(line);
					writer.write("\n");
				}
			}

			sFile.close();
			writer.close();

		} catch (FileNotFoundException ex) {
			// do nothing
		}
		file1.delete();
		file2.renameTo(file1);
	}
}
