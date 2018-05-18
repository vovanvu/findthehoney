package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ExtensionPanel extends JPanel {
	private GameTimerPanel gameTimerPanel;
	private QuestionPanel questionPanel;
	private InGameMenuPanel inGameMenuPanel;

	public ExtensionPanel() {
		setPreferredSize(new Dimension(360, 300));
		setLayout(new BorderLayout());
		initChildPanel();
	}

	private void initChildPanel() {
		// init
		gameTimerPanel = new GameTimerPanel();
		questionPanel = new QuestionPanel();
		inGameMenuPanel = new InGameMenuPanel();
		// add
		add(gameTimerPanel, BorderLayout.NORTH);
		add(questionPanel, BorderLayout.CENTER);
		add(inGameMenuPanel, BorderLayout.SOUTH);
	}

	public QuestionPanel getQuestionPanel() {
		return questionPanel;
	}

	public InGameMenuPanel getInGameMenuPanel() {
		return inGameMenuPanel;
	}

	public GameTimerPanel getGameTimerPanel() {
		return gameTimerPanel;
	}
}
