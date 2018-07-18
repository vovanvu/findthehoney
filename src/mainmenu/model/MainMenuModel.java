package mainmenu.model;

import java.util.Observable;

public class MainMenuModel extends Observable {
	private boolean isHelp;
	private boolean isAbout;
	private boolean isHighScore;
	private boolean isStart;

	private void notifyChanged() {
		setChanged();
		notifyObservers();
	}

	public void setHelp() {
		this.isHelp = true;
		notifyChanged();
		this.isHelp = false;
	}

	public boolean isHelp() {
		return isHelp;
	}

	public void setAbout() {
		this.isAbout = true;
		notifyChanged();
		this.isAbout = false;
	}

	public boolean isAbout() {
		return isAbout;
	}

	public void setHighScore() {
		this.isHighScore = true;
		notifyChanged();
		this.isHighScore = false;
	}

	public boolean isHighScore() {
		return isHighScore;
	}

	public void setStart() {
		this.isStart = true;
		notifyChanged();
		this.isStart = false;
	}

	public boolean isStart() {
		return isStart;
	}
}
