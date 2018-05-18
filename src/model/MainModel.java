package model;

public class MainModel {
	private Bear bear;
	private GameMap map;
	private QuestionLibrary library;

	public MainModel() {
		bear = new Bear();
		map = new GameMap();
		library = new QuestionLibrary();
	}

	public Bear getBear() {
		return bear;
	}

	public GameMap getMap() {
		return map;
	}

	public QuestionLibrary getLibrary() {
		return library;
	}
	
}
