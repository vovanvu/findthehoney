package model;

import java.awt.*;

import javax.swing.*;

public class Bear {

	private int tileX, tileY;

	public Bear() {
		tileX = 1;
		tileY = 1;

	}

	public void move(int tx, int ty) {
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
}