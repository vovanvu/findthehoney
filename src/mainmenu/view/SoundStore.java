package mainmenu.view;

public class SoundStore {
	private SoundFactory soundFactory;

	public SoundStore(SoundFactory soundFactory) {
		this.soundFactory = soundFactory;
	}

	public Sound makeSound(String filename) {
		Sound sound;
		sound = soundFactory.createSound(filename);
		return sound;
	}
}
