package ingame.model;


public class LibraryFactory {
	public static Library createLibrary() {
		return new SocialLibrary();
	}
}
