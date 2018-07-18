package mainmenu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainmenu.model.MainMenuModel;
import mainmenu.view.MainMenuView;

public class MainMenuController {
	private MainMenuModel mainMenuModel;
	private MainMenuView mainMenuView;

	public MainMenuController(MainMenuModel model, MainMenuView view) {
		this.mainMenuModel = model;
		this.mainMenuView = view;
		handleMainMenu();
	}
	private void handleMainMenu() {
		handleHelp();
		handleAbout();
		handleHighScore();
		handleStart();

	}
	private void handleHelp() {
		mainMenuView.help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainMenuModel.setHelp();
			}
		});
	}

	private void handleAbout() {
		mainMenuView.about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainMenuModel.setAbout();
			}
		});
	}

	private void handleHighScore() {
		mainMenuView.highScrore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainMenuModel.setHighScore();
			}
		});
	}

	private void handleStart() {
		mainMenuView.start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainMenuModel.setStart();
			}
		});
	}
}
