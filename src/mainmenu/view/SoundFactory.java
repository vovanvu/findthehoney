package mainmenu.view;

import ingame.view.SoundPlaying;

public class SoundFactory {
	public Sound createSound(String filename) {
		Sound sound = null;
		if (filename == "main")
			sound = new SoundMain();
		else if (filename == "ingame")
			sound = new SoundPlaying();
		return sound;
	}
}