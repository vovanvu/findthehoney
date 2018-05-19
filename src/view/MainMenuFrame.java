package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class MainMenuFrame extends JFrame {
	JPanel p0, p1;
	JLabel Name;
	JButton start, help, highScrore, about;
	ImageIcon image, icon;
	// Sound sound;

	public JButton getStart() {
		return start;
	}

	public MainMenuFrame() {
		setTitle("Find The Honey");
		setSize(650, 700);
		setLayout(new BorderLayout());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Ban muon thoat game?", "Xac nhan thoat",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (choose == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		// disable sound
		// sound = new Sound("musicstart.wav");
		// sound.start();

		p0 = new JPanel();
		image = new ImageIcon("resources/Nen.jpg");
		p0 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};

		Name = new JLabel("FIND THE HONEY");
		Name.setPreferredSize(new Dimension(400, 50));
		Font font = new Font("Courier", Font.BOLD, 43);
		Name.setFont(font);
		p0.add(Name);

		icon = new ImageIcon("resources/Icon.png");
		start = new JButton("START", icon);
		start.setBackground(Color.WHITE);
		start.setFocusPainted(false);
		start.setFont(new Font("Arial", Font.BOLD, 13));
		start.setPreferredSize(new Dimension(180, 90));
		p0.add(start);

		p1 = new JPanel();
		help = new JButton("HELP");
		help.setPreferredSize(new Dimension(200, 50));
		highScrore = new JButton("HIGH SCORE");
		highScrore.setPreferredSize(new Dimension(200, 50));
		about = new JButton("ABOUT");
		about.setPreferredSize(new Dimension(200, 50));
		p1.add(help);
		p1.add(highScrore);
		p1.add(about);

		add(p0, BorderLayout.CENTER);
		add(p1, BorderLayout.SOUTH);

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpFrame();

			}
		});

		highScrore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				createHighScoreJframe();
				readFromFile();
			}

		});

		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new AboutFrame();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	JTextArea scoreTableTextArea;

	private void createHighScoreJframe() {
		JFrame highScoreFrame = new JFrame();
		JPanel panel;

		highScoreFrame.setTitle("HighScores");
		highScoreFrame.setSize(300, 300);
		highScoreFrame.setLocationRelativeTo(null);
		highScoreFrame.setVisible(true);
		highScoreFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				highScoreFrame.dispose();
			}

		});
		highScoreFrame.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				highScoreFrame.dispose();
			}
		});
		scoreTableTextArea = new JTextArea(400, 300);
		scoreTableTextArea.setFocusable(false);
		JScrollPane jScrollPane = new JScrollPane(scoreTableTextArea);
		panel.add(back, BorderLayout.SOUTH);
		panel.add(jScrollPane, BorderLayout.CENTER);
		highScoreFrame.add(panel);

	}

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

}
