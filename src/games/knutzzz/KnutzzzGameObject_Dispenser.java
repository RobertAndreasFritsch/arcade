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
	Image images[];
	// Image toadstoolImage;
	Image lighthouseImage;
	int actImage = 0;
	Knutzzz parent;
	Sound sound;
	Sound soundSiren;

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
		sound.load("res/games/knutzzz/sfx/dispense.wav");

		soundSiren = new Sound();
		soundSiren.load("res/games/knutzzz/sfx/siren.wav");
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
		if (step < 300)
			step++;

		if (step < 128) {
			// Warten
		} else if (step < 144) {
			// Dispenser drehen
			actImage = step - 128;
		} else if (step < 256) {
			// Warten/Lichtstrahl anzeigen
			actImage = 16;
		} else if (step < 272) {
			// Ball auswerfen
		} else if (step < 288) {
			// Dispenser drehen
			actImage = 288 - step;
		} else {
			// Dispenser ruht
			actImage = 0;
		}

		if (step == 256) {
			// Sound abspielen - Ballausgabe
			sound.play();
		}
		if (step == 144) {
			// Sound abspielen - Startsirene
			soundSiren.play();
		}

		createBall = (step == 256);
		showToadstool = (actImage == 16);
		showLighthouse = (step > 144 && step < 256);
	}
}
