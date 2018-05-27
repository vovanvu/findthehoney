package model;

import java.util.Observable;

public class MainModel extends Observable {
	private Bear bear;
	private GameMap gameMap;
	private QuestionLibrary library;
	private boolean gameWin;
	private boolean isUpdatedIndex;
	private boolean isGameStarted;
	private boolean isExit;
	private boolean hasMove;
	private boolean hasAnswerTrue;
	private boolean hasAnswerFalse;
	private boolean hasReset;
	private boolean hasPaue;
	private boolean hasMute;
	private boolean hasGoMainMenu;

	public MainModel() {
		bear = new Bear();
		gameMap = new GameMap();
		library = new QuestionLibrary();
	}

	public Bear getBear() {
		return bear;
	}

	public GameMap getMap() {
		return gameMap;
	}

	public QuestionLibrary getLibrary() {
		return library;
	}

	public boolean isInMapRange(MapRange mapRange) {
		int x = bear.getTitleX();
		int y = bear.getTitleY();
		switch (mapRange) {
		case ROW:
			return x >= 1 && x <= 6;
		case COL:
			return y >= 1 && y <= 6;
		}
		return false;

	}

	// check if bear is on question tile
	public boolean onTile(MapElement mapElement, Direction direction) {
		int x = bear.getTitleX();
		int y = bear.getTitleY();
		switch (mapElement) {
		case QUESTION:
			if (direction == Direction.RIGHT)
				return gameMap.getMap(x + 1, y).equals("q");
			else if (direction == Direction.LEFT)
				return gameMap.getMap(x - 1, y).equals("q");
			else if (direction == Direction.UP)
				return gameMap.getMap(x, y - 1).equals("q");
			else if (direction == Direction.DOWN)
				return gameMap.getMap(x, y + 1).equals("q");
		case ROCK:
			if (direction == Direction.RIGHT)
				return gameMap.getMap(x + 1, y).equals("r");
			else if (direction == Direction.LEFT)
				return gameMap.getMap(x - 1, y).equals("r");
			else if (direction == Direction.UP)
				return gameMap.getMap(x, y - 1).equals("r");
			else if (direction == Direction.DOWN)
				return gameMap.getMap(x, y + 1).equals("r");
		case HONEY:
			if (direction == Direction.RIGHT)
				return gameMap.getMap(x + 1, y).equals("h");
			else if (direction == Direction.LEFT)
				return gameMap.getMap(x - 1, y).equals("h");
			else if (direction == Direction.UP)
				return gameMap.getMap(x, y - 1).equals("h");
			else if (direction == Direction.DOWN)
				return gameMap.getMap(x, y + 1).equals("h");
		default:
			return false;
		}
	}

	public Question getCurrentQuestion() {
		return library.getQuestions().get(library.getQuestionIndex());
	}

	public void resetGame() {
		hasReset = true;
		gameMap.resetMap();
		bear.resetTile();
		notifyChanged();
		hasReset = false;
	}

	public boolean isGameWin() {
		return gameWin;
	}

	public boolean isUpdatedIndex() {
		return isUpdatedIndex;
	}

	public boolean isGameStarted() {
		return isGameStarted;
	}

	public boolean isHasMove() {
		return hasMove;
	}

	public void updateQuestionIndex() {
		isUpdatedIndex = true;
		library.updateQuestionIndex();
		notifyChanged();
		isUpdatedIndex = false;
	}

	public void setGameWin() {
		this.gameWin = true;
		notifyChanged();
		gameWin = false;
	}

	public void setGameStarted() {
		this.isGameStarted = true;
		notifyChanged();
		isGameStarted = false;
	}

	private void notifyChanged() {
		setChanged();
		notifyObservers();
	}

	public void moveBear(Direction direction) {
		this.hasMove = true;
		bear.move(direction);
		notifyChanged();
		this.hasMove = false;
	}

	public void updateMapTrueAnswer() {
		hasAnswerTrue = true;
		gameMap.updateMap(bear.getTitleX(), bear.getTitleY(), "g");
		notifyChanged();
		hasAnswerTrue = false;
	}

	public void updateMapFalseAnswer() {
		hasAnswerFalse = true;
		gameMap.updateMap(bear.getTitleX(), bear.getTitleY(), "r");
		notifyChanged();
		hasAnswerFalse = false;

	}

	public void setExit() {
		this.isExit = true;
		notifyChanged();
		this.isExit = false;
	}

	public void setPause() {
		this.hasPaue = true;
		notifyChanged();
		this.hasPaue = false;
	}

	public void setMute() {
		this.hasMute = true;
		notifyChanged();
		this.hasMute = false;
	}

	public void setHasGoMainMenu() {
		this.hasGoMainMenu = true;
		// reset position before go mainMenu, because MainModel will not be destroyed.
		gameMap.resetMap();
		bear.resetTile();
		// end reset
		notifyChanged();
		this.hasGoMainMenu = false;
	}

	public boolean isHasAnswerTrue() {
		return hasAnswerTrue;
	}

	public boolean isHasAnswerFalse() {
		return hasAnswerFalse;
	}

	public boolean isExit() {
		return isExit;
	}

	public boolean isHasReset() {
		return hasReset;
	}

	public boolean isHasPause() {
		return hasPaue;
	}

	public boolean isHasMute() {
		return hasMute;
	}

	public boolean isHasGoMainMenu() {
		return hasGoMainMenu;
	}
}
