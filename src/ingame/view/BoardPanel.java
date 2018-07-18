package ingame.view;

import java.awt.*;// * is all
import java.awt.event.*;

import javax.swing.*;

import ingame.model.Bear;
import ingame.model.GameMap;

public class BoardPanel extends JPanel {
	private GameImage gameImage;
	private GameMap gameMap;
	private Bear bear;

	public BoardPanel() {
		gameImage = new GameImage();
		setPreferredSize(new Dimension(400, 300));
		setFocusable(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (gameMap.getMap(x, y).equals("h")) {
					g.drawImage(gameImage.getHoney(), x * 50, y * 50, null);
				}
				if (gameMap.getMap(x, y).equals("g")) {
					g.drawImage(gameImage.getGrass(), x * 50, y * 50, null);
				}

				if (gameMap.getMap(x, y).equals("r")) {
					g.drawImage(gameImage.getRock(), x * 50, y * 50, null);
				}
				if (gameMap.getMap(x, y).equals("q")) {
					g.drawImage(gameImage.getQuestion(), x * 50, y * 50, null);
				}
			}
		}
		g.drawImage(gameImage.getBear(), bear.getTitleX() * 50, bear.getTitleY() * 50, null);
	}

	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	public void setBear(Bear bear) {
		this.bear = bear;
	}

	public void updateBoard() {
		repaint();
	}

}
