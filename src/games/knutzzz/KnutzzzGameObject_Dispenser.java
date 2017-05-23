package games.knutzzz;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public class KnutzzzGameObject_Dispenser implements Updateable, Drawable {
	int step = 0;
	private int STARTTIME = 512;

	Image images[];
	// Image toadstoolImage;
	Image lighthouseImage;
	int actImage = 0;
	Knutzzz parent;
	Sound sound;
	Sound soundSiren;
	Sound music;
	int mode = 0;

	public boolean createBall = false;
	public boolean showToadstool = false;
	private boolean showLighthouse = false;

	public KnutzzzGameObject_Dispenser(Knutzzz parent) {
		this.parent = parent;
		// toadstoolImage =
		// parent.getToolkit().getImage("res/games/knutzzz/toadstool.png");
		lighthouseImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/lighthouse.png");
		loadImages();

		sound = new Sound();
		// sound.load("res/games/knutzzz/sfx/dispense.wav");

		soundSiren = new Sound();
		music = new Sound();
		// soundSiren.load("res/games/knutzzz/sfx/siren.wav");
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(images[actImage], 480, 480, null);

		if (showLighthouse)
			showLighthouse(g);
	}

	private void loadImages() {
		// Bilder fuer Animation laden
		images = new Image[17];
		BufferedImage shapes = null;
		// Try-Catch-Abfrage zur Überprüfung, ob Bilddateien der Kanone
		// vorhanden sind
		try {
			shapes = ImageIO.read(new File("res/games/knutzzz/img/dispensershapes.png"));
		} catch (IOException e) {
		}
		// Lädt die Bilder für die Animation in das Array
		for (int i = 0; i < 17; i++)
			images[i] = shapes.getSubimage(i * 64, 0, 64, 64);
	}

	public void reset() {
		step = 0;
		actImage = 0;
		createBall = false;
		showToadstool = false;
	}

	private void showLighthouse(Graphics2D g) {
		// Bilder drehen
		AffineTransform origXform = g.getTransform();
		AffineTransform newXform = (AffineTransform) (origXform.clone());

		// Rotationsmittelpunkt
		double currentAngle = Math.toRadians(step * 5);
		newXform.rotate(currentAngle, 512, 512);
		g.setTransform(newXform);

		// Bild zeichnen
		g.drawImage(lighthouseImage, 512 - 128, 512 - 32, null);
		g.setTransform(origXform);
	}

	@Override
	public void update(long elapsed) {
		if (mode == 0) {
			if (step < STARTTIME + 172)
				step++;
			else
				STARTTIME = 256;

			if (step < STARTTIME) {
				// Warten
			} else if (step < STARTTIME + 16) {
				// Dispenser drehen
				actImage = step - STARTTIME;
			} else if (step < STARTTIME + 128) {
				// Warten/Lichtstrahl anzeigen
				actImage = 16;
			} else if (step < STARTTIME + 144) {
				// Ball auswerfen
			} else if (step < STARTTIME + 160) {
				// Dispenser drehen
				actImage = STARTTIME + 160 - step;
			} else {
				// Dispenser ruht
				actImage = 0;
			}

			if (step == 1 && STARTTIME > 256) {
				music.instantPlay("res/games/knutzzz/sfx/intro.wav");
			}
			if (step == STARTTIME + 128) {
				// Sound abspielen - Ballausgabe
				sound.instantPlay("res/games/knutzzz/sfx/dispense.wav");
			}
			if (step == STARTTIME + 16) {
				// Sound abspielen - Startsirene
				soundSiren.instantPlay("res/games/knutzzz/sfx/siren.wav");
			}

			createBall = (step == STARTTIME + 128);
			showToadstool = (actImage == 16);
			showLighthouse = (step > (STARTTIME + 16) && step < (STARTTIME + 128));
		} else {
			// Spielende (21 Baelle)
			step++;
			if (step == 128) {
				// Finalmusik
				music.instantPlay("res/games/knutzzz/sfx/extro.wav");
			}
			if (step == 512) {
				// Spielende
				parent.setRunning(false);
			}
		}
	}

	public void setGameEnd() {
		mode = 1;
		step = 0;
	}
}
