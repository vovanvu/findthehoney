package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import model.MainModel;

public class MainView {
	private MainModel mainModel;
	private InGameFrame inGameFrame;
	private BoardPanel boardPanel;
	private ExtensionPanel extensionPanel;

	public MainView(MainModel mainModel) {
		this.mainModel = mainModel;
		createView();
	}

	private void createView() {
		// init
		createBoardPanel();
		createExtensionPanel();
		createInGameFrame();
		// add to Frame
		inGameFrame.add(boardPanel, BorderLayout.LINE_START);
		inGameFrame.add(extensionPanel, BorderLayout.LINE_END);
		inGameFrame.requestFocus();
	}

	private void createInGameFrame() {
		inGameFrame = new InGameFrame();
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
		boardPanel.requestFocus(false);
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
}
