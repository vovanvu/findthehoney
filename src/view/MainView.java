package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import model.MainModel;

public class MainView {
	private MainModel mainModel;
	private MainMenuFrame mainMenuFrame;
	private InGameFrame inGameFrame;
	private BoardPanel boardPanel;
	private ExtensionPanel extensionPanel;

	
	public MainView(MainModel mainModel) {
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
			// todo informWin
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

}
