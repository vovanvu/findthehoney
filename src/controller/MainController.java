package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import model.Direction;
import model.MainModel;
import model.MapElement;
import model.Question;
import view.CustomButton;
import view.MainView;

public class MainController implements GameController {
	private MainModel mainModel;
	private MainView mainView;

	public MainController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
		mainModel.addObserver(mainView);
		xulyBatDauGame();
	}

	public void xulyBatDauGame() {
		mainView.getMainMenuFrame().getStart().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainModel.setGameStarted();
				xulyInGame();
			}
		});
	}

	public void xulyInGame() {
		xulyDiChuyen();
		xulyButtonTraLoiCauHoi();
		xulySuKienInGameMenu();
		xulyThoatGame();
	}

	public void xulyDiChuyen() {
		mainView.getInGameFrame().addKeyListener(new KeyAdapter() {
			private void move(Direction direction) {
				// can move if in map range and next move postion is not rock
				//no need check map range, rock is enough, because map has a wall 
				//if (mainModel.isInMapRange(mapRange) && !mainModel.onTile(MapElement.ROCK,
				// direction)) {
				//check if direction is not on Rock tile
				if (!mainModel.onTile(MapElement.ROCK, direction)) {
					if (mainModel.onTile(MapElement.QUESTION, direction)) {
						xulyGapCauHoi();
					}
					if (mainModel.onTile(MapElement.HONEY, direction)) {
						xulyChienThang();
					}
					mainModel.moveBear(direction);
					// print coordinate (toa do)
					mainModel.getBear().getPosition();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_W) {
					mainModel.getBear().setPrevious(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY());
					move(Direction.UP);

				}
				if (keycode == KeyEvent.VK_S) {
					mainModel.getBear().setPrevious(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY());
					move(Direction.DOWN);

				}
				if (keycode == KeyEvent.VK_D) {
					mainModel.getBear().setPrevious(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY());
					move(Direction.RIGHT);

				}
				if (keycode == KeyEvent.VK_A) {
					mainModel.getBear().setPrevious(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY());
					move(Direction.LEFT);
				}
			}
		});
	}

	public void xulyGapCauHoi() {
		mainModel.updateQuestionIndex();
	}

	public void xulyButtonTraLoiCauHoi() {
		CustomButton btA = mainView.getExtensionPanel().getQuestionPanel().getBtA();
		CustomButton btB = mainView.getExtensionPanel().getQuestionPanel().getBtB();
		CustomButton btC = mainView.getExtensionPanel().getQuestionPanel().getBtC();
		CustomButton btD = mainView.getExtensionPanel().getQuestionPanel().getBtD();
		// add Listener

		btA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ganXuLyButtonTraLoi(btA, mainModel.getBear().getxPrevious(), mainModel.getBear().getyPrevious());
			}
		});
		btB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ganXuLyButtonTraLoi(btB, mainModel.getBear().getxPrevious(), mainModel.getBear().getyPrevious());
			}
		});
		btC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ganXuLyButtonTraLoi(btC, mainModel.getBear().getxPrevious(), mainModel.getBear().getyPrevious());
			}
		});
		btD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ganXuLyButtonTraLoi(btD, mainModel.getBear().getxPrevious(), mainModel.getBear().getyPrevious());
			}
		});

	}

	public void ganXuLyButtonTraLoi(CustomButton CustomButton, int xPrevious, int yPrevious) {
		Question question = mainModel.getCurrentQuestion();

		if (question.isTrueAnswer(CustomButton.getBtnID())) {
			mainModel.updateMapTrueAnswer();
		} else {
			mainModel.updateMapFalseAnswer();
			mainModel.getBear().setTile(xPrevious, yPrevious);
		}
	}

	public void xulySuKienInGameMenu() {
		JButton btReset = mainView.getExtensionPanel().getInGameMenuPanel().getBtnReset();
		JButton btPause = mainView.getExtensionPanel().getInGameMenuPanel().getBtnPause();
		JButton btMute = mainView.getExtensionPanel().getInGameMenuPanel().getBtnMute();
		JButton btMainMenu = mainView.getExtensionPanel().getInGameMenuPanel().getBtnMainMenu();
		btReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainModel.resetGame();
			}
		});
		btPause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainModel.setPause();
			}
		});
		btMute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainModel.setMute();
			}
		});
		btMainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainModel.setHasGoMainMenu();
				xulyBatDauGame();
			}
		});
	}

	public void xulyChienThang() {
		mainModel.setGameWin();
	}

	public void xulyThoatGame() {
		mainView.getInGameFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mainModel.setExit();
			}
		});
	}

}
