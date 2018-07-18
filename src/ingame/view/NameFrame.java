package ingame.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainmenu.view.HighScoreFrame;

public class NameFrame extends JFrame {
	private ExtensionPanel extensionPanel;
	private JTextField typeName;
	private Player player;
	private ArrayList<Player> players;
	private ArrayList<String> readArr;
	private ArrayList<Player> newPlayers;
	private JFrame name;
	private JButton btnOk;
	private JButton btnCancel;
	private JPanel confirmPanel;

	public NameFrame(ExtensionPanel extensionPanel) {
		this.extensionPanel = extensionPanel;
		players = new ArrayList<Player>();
		convertTextToArrayList();
		ArrayList<Player> historyPlayers = getInfoPlayer();
		for (Player pA1 : historyPlayers) {
			players.add(pA1);
		}
		//
		name = new JFrame();
		initComponent();
		addListenerForConfirmButton();
	}

	private void initComponent() {
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
		confirmPanel = new JPanel();
		btnOk = new JButton("OK");
		btnCancel = new JButton("Cancel");
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

	}

	// add Listener for button
	private void addListenerForConfirmButton() {
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

				// iterator ( make more pattern)
				Iterator iterator = players.iterator();
				while (iterator.hasNext()) {
					Player p = (Player) iterator.next();
					record += p.getName() + " " + p.getHour() + ":" + p.getMinute() + ":" + p.getSecond() + "\n";
				}
				// for (Player p : players) {
				// record += p.getName() + " " + p.getHour() + ":" + p.getMinute() + ":" +
				// p.getSecond() + "\n";
				// }
				writeToFile(record);
				// create HighScoreFrame
				new HighScoreFrame();
				name.dispose();
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

}
