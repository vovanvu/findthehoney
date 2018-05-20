package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.Direction;
import model.GameMap;
import model.MainModel;
import model.MapElement;
import model.MapRange;
import model.Question;
import view.CustomButton;
import view.MainMenuFrame;
import view.MainView;

public class MainController {
	private MainModel mainModel;
	private MainView mainView;

	public MainController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
		mainModel.addObserver(mainView);
		xulyBatDauGame();
	}

	private void xulyBatDauGame() {
		mainView.getMainMenuFrame().getStart().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.createInGameFrame();
				xulyInGame();
				mainView.getMainMenuFrame().dispose();
			}
		});
	}

	private void xulyInGame() {
		batDauDemThoiGian();
		xulyDiChuyen();
		xulyButtonTraLoiCauHoi();
		xulySuKienInGameMenu();
		xulyThoatGame();
	}

	private void batDauDemThoiGian() {
		mainView.startClock();
	}

	private void xulyDiChuyen() {
		mainView.getInGameFrame().addKeyListener(new KeyListener() {
			private void move(Direction direction, MapRange mapRange) {
				// can move if in map range and next move postion is not rock
				if (mainModel.isInMapRange(mapRange) && !mainModel.onTile(MapElement.ROCK, direction)) {
					if (mainModel.onTile(MapElement.QUESTION, direction)) {
						xulyGapCauHoi();
					}
					if (mainModel.onTile(MapElement.HONEY, direction)) {
						xulyChienThang();
					}
					mainModel.getBear().move(direction);
					mainModel.getBear().getPosition();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_W) {
					mainModel.getBear().setPrevious(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY());
					move(Direction.UP, MapRange.COL);

				}
				if (keycode == KeyEvent.VK_S) {
					mainModel.getBear().setPrevious(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY());
					move(Direction.DOWN, MapRange.COL);

				}
				if (keycode == KeyEvent.VK_D) {
					mainModel.getBear().setPrevious(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY());
					move(Direction.RIGHT, MapRange.ROW);

				}
				if (keycode == KeyEvent.VK_A) {
					mainModel.getBear().setPrevious(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY());
					move(Direction.LEFT, MapRange.ROW);
				}

			}

		});
	}

	private void xulyGapCauHoi() {
		mainModel.updateQuestionIndex();
		mainView.showQuestion();

	}

	private void xulyButtonTraLoiCauHoi() {
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

	private void ganXuLyButtonTraLoi(CustomButton CustomButton, int xPrevious, int yPrevious) {
		Question question = mainModel.getCurrentQuestion();

		if (question.isTrueAnswer(CustomButton.getBtnID())) {
			// replace with grass
			mainModel.getMap().updateMap(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY(), "g");
			// set visible and forcus after click
			mainView.getExtensionPanel().getQuestionPanel().setVisible(false);
			mainView.getInGameFrame().setFocusable(true);
			mainView.getInGameFrame().requestFocus();
		} else {
			// replace with rock
			mainModel.getMap().updateMap(mainModel.getBear().getTitleX(), mainModel.getBear().getTitleY(), "r");
			mainModel.getBear().setTile(xPrevious, yPrevious);
			// set visible and forcus after click
			mainView.getExtensionPanel().getQuestionPanel().setVisible(false);
			mainView.getInGameFrame().setFocusable(true);
			mainView.getInGameFrame().requestFocus();
		}
	}

	private void xulySuKienInGameMenu() {
		JButton btReset = mainView.getExtensionPanel().getInGameMenuPanel().getBtnReset();
		JButton btPause = mainView.getExtensionPanel().getInGameMenuPanel().getBtnPause();
		JButton btMainMenu = mainView.getExtensionPanel().getInGameMenuPanel().getBtnMainMenu();
		btReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainModel.resetGame();
				mainView.hideQuestion();
				mainView.resetClock();
				mainView.resetFocus();
			}
		});
		btPause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameMap map = mainModel.getMap();
				int x = mainModel.getBear().getTitleX();
				int y = mainModel.getBear().getTitleY();

				if (btPause.getText().equals("Tam dung") && (!map.getMap(x, y).equals("q"))) {
					mainView.getExtensionPanel().getGameTimerPanel().stopClock();
					mainView.getBoardPanel().setEnabled(false);
					mainView.removeFocus();
					btPause.setText("Tiep tuc");
				} else if (btPause.getText().equals("Tam dung") && (map.getMap(x, y).equals("q"))) {
					// do nothing
				} else {
					mainView.getExtensionPanel().getGameTimerPanel().resumeClock();
					mainView.getExtensionPanel().setEnabled(true);
					mainView.resetFocus();
					btPause.setText("Tam dung");
				}

			}
		});
		btMainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Ban muon tro ve menu chinh?", "Xac nhan thoat",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (choose == JOptionPane.YES_OPTION) {
					mainView.getInGameFrame().dispose();
					mainView.createMainMenuFrame();
					xulyBatDauGame();
				}
			}
		});
	}

	private void xulyChienThang() {
		mainModel.setGameWin(true);
	}

	private void xulyThoatGame() {
		mainView.getInGameFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Ban muon thoat game?", "Xac nhan thoat",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (choose == JOptionPane.YES_OPTION) {
					mainView.getInGameFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else {
					mainView.getInGameFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}

	public static void main(String[] args) {
		MainModel mainModel = new MainModel();
		MainView mainView = new MainView(mainModel);
		new MainController(mainModel, mainView);
	}
}
