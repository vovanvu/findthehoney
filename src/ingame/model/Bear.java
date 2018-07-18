package ingame.model;

import java.awt.*;

import javax.swing.*;

public class Bear {

	private int tileX, tileY;
	private int xPrevious, yPrevious;

	public Bear() {
		tileX = 1;
		tileY = 1;

	}

	public void move(Direction direction) {
		int tx = 0;
		int ty = 0;
		switch (direction) {
		case UP:
			tx = 0;
			ty = -1;
			break;
		case DOWN:
			tx = 0;
			ty = 1;
			break;
		case LEFT:
			tx = -1;
			ty = 0;
			break;
		case RIGHT:
			tx = 1;
			ty = 0;
			break;
		}
		tileX += tx;
		tileY += ty;
	}

	public void setTile(int tileX, int tileY) {
		this.tileX = tileX;
		this.tileY = tileY;
	}

	public void resetTile() {
		tileX = 1;
		tileY = 1;
	}

	public int getTitleX() {
		return tileX;
	}

	public int getTitleY() {
		return tileY;

	}

	public int getxPrevious() {
		return xPrevious;
	}

	public int getyPrevious() {
		return yPrevious;
	}

	public void setPrevious(int xPrevious, int yPrevious) {
		this.xPrevious = xPrevious;
		this.yPrevious = yPrevious;
	}

	public void getPosition() {
		System.out.println("x: " + getTitleX() + ", y: " + getTitleY());
	}
}