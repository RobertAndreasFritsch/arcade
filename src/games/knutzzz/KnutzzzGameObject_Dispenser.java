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
	int					step				= 0;
	private int			STARTTIME		= 512;

	Image					images[];
	// Image toadstoolImage;
	Image					lighthouseImage;
	int					actImage			= 0;
	Knutzzz				parent;
	Sound					sound;
	Sound					soundSiren;
	Sound					music;
	int					mode				= 0;

	public boolean		createBall		= false;
	public boolean		showToadstool	= false;
	private boolean	showLighthouse	= false;

	public KnutzzzGameObject_Dispenser(final Knutzzz parent) {
		this.parent = parent;
		// toadstoolImage =
		// parent.getToolkit().getImage("res/games/knutzzz/toadstool.png");
		this.lighthouseImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/lighthouse.png");
		this.loadImages();

		this.sound = new Sound();
		// sound.load("res/games/knutzzz/sfx/dispense.wav");

		this.soundSiren = new Sound();
		this.music = new Sound();
		// soundSiren.load("res/games/knutzzz/sfx/siren.wav");
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawImage(this.images[this.actImage], 480, 480, null);

		if (this.showLighthouse) {
			this.showLighthouse(g);
		}
	}

	private void loadImages() {
		// Bilder fuer Animation laden
		this.images = new Image[17];
		BufferedImage shapes = null;
		// Try-Catch-Abfrage zur Überprüfung, ob Bilddateien der Kanone
		// vorhanden sind
		try {
			shapes = ImageIO.read(new File("res/games/knutzzz/img/dispensershapes.png"));
		}
		catch (final IOException e) {
		}
		// Lädt die Bilder für die Animation in das Array
		for (int i = 0; i < 17; i++) {
			this.images[i] = shapes.getSubimage(i * 64, 0, 64, 64);
		}
	}

	public void reset() {
		this.step = 0;
		this.actImage = 0;
		this.createBall = false;
		this.showToadstool = false;
	}

	private void showLighthouse(final Graphics2D g) {
		// Bilder drehen
		final AffineTransform origXform = g.getTransform();
		final AffineTransform newXform = (AffineTransform) origXform.clone();

		// Rotationsmittelpunkt
		final double currentAngle = Math.toRadians(this.step * 5);
		newXform.rotate(currentAngle, 512, 512);
		g.setTransform(newXform);

		// Bild zeichnen
		g.drawImage(this.lighthouseImage, 512 - 128, 512 - 32, null);
		g.setTransform(origXform);
	}

	@Override
	public void update(final long elapsed) {
		if (this.mode == 0) {
			if (this.step < this.STARTTIME + 172) {
				this.step++;
			}
			else {
				this.STARTTIME = 256;
			}

			if (this.step < this.STARTTIME) {
				// Warten
			}
			else
				if (this.step < this.STARTTIME + 16) {
					// Dispenser drehen
					this.actImage = this.step - this.STARTTIME;
				}
				else
					if (this.step < this.STARTTIME + 128) {
						// Warten/Lichtstrahl anzeigen
						this.actImage = 16;
					}
					else
						if (this.step < this.STARTTIME + 144) {
							// Ball auswerfen
						}
						else
							if (this.step < this.STARTTIME + 160) {
								// Dispenser drehen
								this.actImage = this.STARTTIME + 160 - this.step;
							}
							else {
								// Dispenser ruht
								this.actImage = 0;
							}

			if (this.step == 1 && this.STARTTIME > 256) {
				this.music.instantPlay("res/games/knutzzz/sfx/intro.wav");
			}
			if (this.step == this.STARTTIME + 128) {
				// Sound abspielen - Ballausgabe
				this.sound.instantPlay("res/games/knutzzz/sfx/dispense.wav");
			}
			if (this.step == this.STARTTIME + 16) {
				// Sound abspielen - Startsirene
				this.soundSiren.instantPlay("res/games/knutzzz/sfx/siren.wav");
			}

			this.createBall = this.step == this.STARTTIME + 128;
			this.showToadstool = this.actImage == 16;
			this.showLighthouse = this.step > this.STARTTIME + 16 && this.step < this.STARTTIME + 128;
		}
		else {
			// Spielende (21 Baelle)
			this.step++;
			if (this.step == 128) {
				// Finalmusik
				this.music.instantPlay("res/games/knutzzz/sfx/extro.wav");
			}
			if (this.step == 512) {
				// Spielende
				this.parent.setRunning(false);
			}
		}
	}

	public void setGameEnd() {
		this.mode = 1;
		this.step = 0;
	}
}
