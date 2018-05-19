package model;

public class MainModel {
	private Bear bear;
	private GameMap gameMap;
	private QuestionLibrary library;
	private boolean gameWin;

	public MainModel() {
		bear = new Bear();
		gameMap = new GameMap();
		library = new QuestionLibrary();
		gameWin = false;
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

	public void updateQuestionIndex() {
		library.updateQuestionIndex();
	}

	public Question getCurrentQuestion() {
		return library.getQuestions().get(library.getQuestionIndex());
	}

	public void resetGame() {
		gameMap.resetMap();
		bear.resetTile();
	}

	public boolean isGameWin() {
		return gameWin;
	}

	public void setGameWin(boolean gameWin) {
		this.gameWin = gameWin;
	}
}
