package games.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3Sound implements Runnable, Sound {

	File file;
	private Player p1;
	private boolean loop;

	public MP3Sound(File file, boolean loop) {
		this.file = file;
		this.loop = loop;
		new Thread(this).start();
	}

	@Override
	public void run() {
		 do {
			try {
				FileInputStream in = new FileInputStream(file);
				this.p1 = new javazoom.jl.player.Player(in);
				p1.play();
				
			} catch (JavaLayerException jle) {
				jle.printStackTrace();
			} catch (FileNotFoundException fnf) {
				fnf.printStackTrace();
			}
			
		} while (loop);
		
		p1.close();
	}
	
	public void stop() {
		p1.close();
		loop = false;
	}
}
