package ingame.view;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ingame.model.GameMap;
import ingame.model.MainModel;
import mainmenu.controller.MainMenuController;
import mainmenu.model.MainMenuModel;
import mainmenu.view.MainMenuView;
import mainmenu.view.Sound;
import mainmenu.view.SoundFactory;
import mainmenu.view.SoundStore;

public class MainView implements Observer {
	private MainModel mainModel;
	private InGameFrame inGameFrame;
	private BoardPanel boardPanel;
	private ExtensionPanel extensionPanel;
	private Sound ingame;

	public MainView(MainModel mainModel) {
		mainModel.addObserver(this);
		this.mainModel = mainModel;
	}

	private void createInGameFrame() {
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

	private void hideQuestion() {
		extensionPanel.getQuestionPanel().setVisible(false);
	}

	private void showQuestion() {
		extensionPanel.getQuestionPanel().setVisible(true);
		extensionPanel.getQuestionPanel().updateQuestion();
		inGameFrame.setFocusable(false);
	}

	private void startClock() {
		extensionPanel.getGameTimerPanel().runClock();
	}

	private void stopClock() {
		extensionPanel.getGameTimerPanel().stopClock();
	}

	private void resetFocus() {
		inGameFrame.setFocusable(true);
		inGameFrame.requestFocus();
	}

	private void removeFocus() {
		inGameFrame.setFocusable(false);
	}

	private void resetClock() {
		extensionPanel.getGameTimerPanel().resetClock();
	}

	private void informWin() {
		extensionPanel.getInGameMenuPanel().getBtnPause().setEnabled(false);
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

	private void updateView() {
		boardPanel.updateBoard();
	}

	private void updateAfterAnswer() {
		getExtensionPanel().getQuestionPanel().setVisible(false);
		getInGameFrame().setFocusable(true);
		getInGameFrame().requestFocus();
	}

	private void showExit() {
		int choose = JOptionPane.showConfirmDialog(null, "Ban muon thoat game?", "Xac nhan thoat",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (choose == JOptionPane.YES_OPTION) {
			inGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			inGameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

	private void resetGame() {
		extensionPanel.getInGameMenuPanel().getBtnPause().setEnabled(true);
		hideQuestion();
		resetClock();
		resetFocus();
	}

	private void pauseGame() {
		JButton btPause = getExtensionPanel().getInGameMenuPanel().getBtnPause();
		GameMap map = mainModel.getMap();
		int x = mainModel.getBear().getTitleX();
		int y = mainModel.getBear().getTitleY();

		if (btPause.getText().equals("Tam dung") && (!map.getMap(x, y).equals("q"))) {
			getExtensionPanel().getGameTimerPanel().stopClock();
			getBoardPanel().setEnabled(false);
			removeFocus();
			btPause.setText("Tiep tuc");
		} else if (btPause.getText().equals("Tam dung") && (map.getMap(x, y).equals("q"))) {
			// do nothing
		} else {
			getExtensionPanel().getGameTimerPanel().resumeClock();
			getExtensionPanel().setEnabled(true);
			resetFocus();
			btPause.setText("Tam dung");
		}

	}

	private void showMainMenu() {
		int choose = JOptionPane.showConfirmDialog(null, "Ban muon tro ve menu chinh?", "Xac nhan thoat",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (choose == JOptionPane.YES_OPTION) {
			ingame.stop();// stop InGameSound when exit to MainMenu
			getInGameFrame().dispose();
			// create MainMenu Frame
			MainMenuModel model = new MainMenuModel();
			MainMenuView view = new MainMenuView(model);
			MainMenuController controller = new MainMenuController(model, view);
		}
	}

	private void muteSound() {
		JButton btMute = getExtensionPanel().getInGameMenuPanel().getBtnMute();
		if (btMute.getText().equals("Mute")) {
			ingame.suspend();
			btMute.setText("Music");
		} else {
			ingame.resume();
			btMute.setText("Mute");
		}
	}

	private void startInGameSound() {
		SoundFactory soundFactory = new SoundFactory();
		SoundStore ss = new SoundStore(soundFactory);
		ingame = ss.makeSound("ingame");
		ingame.start();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (mainModel.isGameWin()) {
			informWin();
		}
		if (mainModel.isUpdatedIndex()) {
			showQuestion();
		}
		if (mainModel.isGameStarted()) {
			createInGameFrame();
			startClock();
			startInGameSound();
		}
		if (mainModel.isHasMove()) {

		}
		if (mainModel.isHasAnswerTrue()) {
			updateAfterAnswer();
		}
		if (mainModel.isHasAnswerFalse()) {
			updateAfterAnswer();
		}
		if (mainModel.isExit()) {
			showExit();
		}
		if (mainModel.isHasReset()) {
			resetGame();
		}
		if (mainModel.isHasPause()) {
			pauseGame();
		}
		if (mainModel.isHasMute()) {
			muteSound();
		}
		if (mainModel.isHasGoMainMenu()) {
			showMainMenu();
		}
		updateView();
	}

}
