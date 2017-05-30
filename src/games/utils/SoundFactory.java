package games.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SoundFactory {
	
	public enum Type {
		MP3, WAV;
	}
	
	public List<Sound> sounds = new ArrayList<>();
	
	public SoundFactory () {
	}
	
	public void newSound (File file, boolean loop, Type type) {
		
		Sound sound = null;
		
		switch (type) {
		case MP3:
			sound = new MP3Sound(file, loop);
			break;
		case WAV:
			sound = new WAVSound(file, loop);
			break;
		}
		
		
		sounds.add(sound);
	}
	
	public void stop() {
		for (Sound sound : sounds) {
			sound.stop();
		}
	}
}
