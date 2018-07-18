package mainmenu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ingame.controller.MainController;
import ingame.model.MainModel;
import ingame.view.MainView;
import ingame.view.Sound;
import ingame.view.SoundMain;
import mainmenu.controller.MainMenuController;
import mainmenu.model.MainMenuModel;

public class MainMenuView extends JFrame implements Observer {
	JPanel p0, p1;
	JLabel Name;
	public JButton start, help, highScrore, about;
	ImageIcon image, icon;
	private SoundMain soundMain;
	public MainMenuView(MainMenuModel mainMenuModel) {
		mainMenuModel.addObserver(this);
		//
		startMainSound();
		//
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
		p0 = new JPanel();
		image = new ImageIcon("images/Nen.jpg");
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
		icon = new ImageIcon("images/Icon.png");
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
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void update(Observable o, Object arg) {
		MainMenuModel model = (MainMenuModel) o;
		if (model.isHelp()) {
			new HelpFrame();
		}
		if (model.isAbout()) {
			new AboutFrame();
		}
		if (model.isHighScore()) {
			new HighScoreFrame();
		}
		if (model.isStart()) {
			dispose();
			soundMain.stop();
			//create ingame frame
			MainModel mainModel = new MainModel();
			MainView mainView = new MainView(mainModel);
			new MainController(mainModel, mainView);
		}
	}
	private void startMainSound() {
		soundMain = new SoundMain();
		soundMain.start();
	}
	
}
