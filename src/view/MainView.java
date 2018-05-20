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
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.MainModel;

public class MainView implements Observer {
	private MainModel mainModel;
	private MainMenuFrame mainMenuFrame;
	private InGameFrame inGameFrame;
	private BoardPanel boardPanel;
	private ExtensionPanel extensionPanel;

	public MainView(MainModel mainModel) {
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
		removeFocus();
		stopClock();
		new NameFrame(extensionPanel);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		informWin();
	}
}
