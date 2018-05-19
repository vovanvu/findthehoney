package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.MainModel;

public class MainView {
	private MainModel mainModel;
	private MainMenuFrame mainMenuFrame;
	private InGameFrame inGameFrame;
	private BoardPanel boardPanel;
	private ExtensionPanel extensionPanel;
	/***** SCORE VARIABLE *****/
	private JTextField typeName;
	private Player player;
	private ArrayList<Player> players;
	private ArrayList<String> readArr;
	private ArrayList<Player> newPlayers;
	private JTextArea scoreTableTextArea;
	private JFrame name;
	private JFrame highScoreFrame;
	private JButton back;

	public MainView(MainModel mainModel) {
		/***** HIGH SCORE INIT *****/
		players = new ArrayList<Player>();
		convertTextToArrayList();// day la cai chuyen txt sang arraylist

		ArrayList<Player> historyPlayers = getInfoPlayer();// lay thong tin tu txt tao ra cai thu 2 choi
		for (Player pA1 : historyPlayers) {
			players.add(pA1);
		}
		// Contructor
		this.mainModel = mainModel;
		createMainMenuFrame();
	}

	public void createMainMenuFrame() {
		mainMenuFrame = new MainMenuFrame();
	}

	public void createInGameFrame() {
		createBoardPanel();
		createExtensionPanel();
		inGameFrame = new InGameFrame();
		// add to Frame
		inGameFrame.add(boardPanel, BorderLayout.LINE_START);
		inGameFrame.add(extensionPanel, BorderLayout.LINE_END);
		inGameFrame.requestFocus();
	}

	private void createBoardPanel() {
		boardPanel = new BoardPanel();
		boardPanel.setGameMap(mainModel.getMap());
		boardPanel.setBear(mainModel.getBear());
	}

	private void createExtensionPanel() {
		extensionPanel = new ExtensionPanel();
		extensionPanel.getQuestionPanel().setLibrary(mainModel.getLibrary());
	}

	// show hide
	// question panel

	public void hideQuestion() {
		extensionPanel.getQuestionPanel().setVisible(false);
	}

	public void showQuestion() {
		extensionPanel.getQuestionPanel().setVisible(true);
		extensionPanel.getQuestionPanel().updateQuestion();
		inGameFrame.setFocusable(false);
	}

	public void startClock() {
		extensionPanel.getGameTimerPanel().runClock();
	}

	public void stopClock() {
		extensionPanel.getGameTimerPanel().stopClock();
	}

	public void resetFocus() {
		inGameFrame.setFocusable(true);
		inGameFrame.requestFocus();
	}

	public void removeFocus() {
		inGameFrame.setFocusable(false);
	}

	public void resetClock() {
		extensionPanel.getGameTimerPanel().resetClock();
	}

	public void informWin() {
		if (mainModel.isGameWin()) {
			showFinishFrame();
		}
	}

	// get Panel
	public InGameFrame getInGameFrame() {
		return inGameFrame;
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public ExtensionPanel getExtensionPanel() {
		return extensionPanel;
	}

	public MainMenuFrame getMainMenuFrame() {
		return mainMenuFrame;
	}

	/***** SCORE METHOD *****/
	private void showFinishFrame() {
		name = new JFrame();
		JPanel namePanel = new JPanel();
		JLabel nameLabel = new JLabel("Ban da chien thang, nhap ten de luu diem: ");
		namePanel.add(nameLabel);
		typeName = new JTextField();
		typeName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				// block user input non-alpha char and space
				if (!Character.isAlphabetic(evt.getKeyChar()) && !(evt.getKeyCode() == KeyEvent.VK_SPACE)) {
					evt.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		JPanel confirmPanel = new JPanel();
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		confirmPanel.add(btnOk);
		confirmPanel.add(btnCancel);
		name.setLayout(new GridLayout(3, 0));
		name.add(namePanel);
		name.add(typeName);
		name.add(confirmPanel);
		name.setSize(400, 200);
		name.setLocationRelativeTo(null);
		name.setVisible(true);
		name.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				name.dispose();

			}
		});

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String playerName = typeName.getText();
				player = new Player(playerName, extensionPanel.getGameTimerPanel().getHour(),
						extensionPanel.getGameTimerPanel().getMinute(), extensionPanel.getGameTimerPanel().getSecond());
				players.add(player);

				sort();
				String record = "";
				for (Player p : players) {
					record += p.getName() + " " + p.getHour() + ":" + p.getMinute() + ":" + p.getSecond() + "\n";
				}

				writeToFile(record);

				createHighScoreJframe();

				readFromFile();

				highScoreFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						formatTextFileWhenClose();
						e.getWindow().dispose();
					}

				});

				//

				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						formatTextFileWhenClose();
						highScoreFrame.dispose();
						name.dispose();
					}
				});
			}

		});

	}

	// write to file with para: String ap, each ap is one line
	private void writeToFile(String ap) {
		File file = new File("file.txt");
		if (file.exists()) {
			file.delete();
		}
		FileWriter writer;
		try {
			writer = new FileWriter(file, true);
			BufferedWriter output = new BufferedWriter(writer);
			output.newLine();
			output.append(ap);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// read from score text file and append it to scoreTableTextArea for user can
	// see it
	private void readFromFile() {
		// TODO Auto-generated method stub
		FileReader fr = null;
		try {
			fr = new FileReader("file.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
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

	// get a ArrayList of Player from string array (readArr), use substring to get
	// it
	private ArrayList<Player> getInfoPlayer() {
		newPlayers = new ArrayList<Player>();
		for (int i = 0; i < readArr.size(); i++) {
			String player = readArr.get(i);
			String name = "";
			name = player.substring(0, player.indexOf(" "));
			// hour
			String sHour = "";
			sHour = player.substring(player.indexOf(" ") + 1, player.indexOf(":"));
			int hour = Integer.parseInt(sHour);
			//
			String sMinute = "";
			sMinute = player.substring(player.indexOf(":") + 1, player.lastIndexOf(":"));
			int minute = Integer.parseInt(sMinute);
			//
			String sSecond = "";
			sSecond = player.substring(player.lastIndexOf(":") + 1, player.length());
			int second = Integer.parseInt(sSecond);
			newPlayers.add(new Player(name, hour, minute, second));
		}
		return newPlayers;

	}

	// Convert from text file to array of string, each line of text will be a line
	// in the string array
	private void convertTextToArrayList() {
		readArr = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				readArr.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(players, comparator);
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

	private void createHighScoreJframe() {
		highScoreFrame = new JFrame();
		JPanel panel;
		highScoreFrame.setTitle("HighScores");
		highScoreFrame.setSize(300, 300);
		highScoreFrame.setLocationRelativeTo(null);
		highScoreFrame.setVisible(true);
		highScoreFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formatTextFileWhenClose();
				highScoreFrame.dispose();
				name.dispose();
			}

		});
		highScoreFrame.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		back = new JButton("Back");
		scoreTableTextArea = new JTextArea(400, 300);
		JScrollPane jScrollPane = new JScrollPane(scoreTableTextArea);
		panel.add(back, BorderLayout.SOUTH);
		panel.add(jScrollPane, BorderLayout.CENTER);
		highScoreFrame.add(panel);
	}
}
