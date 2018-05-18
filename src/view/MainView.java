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

	// private BoardPanel boardPanel;
	// private ExtensionPanel extensionPanel;
	public MainView(MainModel mainModel) {
		this.mainModel = mainModel;
		createView();
	}

	private void createView() {
		//init
		createBoardPanel();
		createExtensionPanel();
		createInGameFrame();
		//add to Frame
		inGameFrame.add(boardPanel, BorderLayout.LINE_START);
		inGameFrame.add(extensionPanel, BorderLayout.LINE_END);
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
	}

	public static void main(String[] args) {
		MainModel mainModel = new MainModel();
		new MainView(mainModel);
	}
}
