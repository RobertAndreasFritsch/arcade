package games.knutzzz;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;

public class KnutzzzGameObject_Bumper implements Drawable, Updateable, ProceedsInput {
	final int VK_ButtonLeft, VK_ButtonRight, VK_ButtonThrust;

	double x, y;
	double vx, vy;
	double ax, ay;
	int angle;
	Image image;
	Image carImage, driverImage, spotlightImage;
	boolean throttle = false;
	int turn = 0;
	Knutzzz parent;
	Sound sound;
	int startx, starty, startangle;
	int ID = 0;

	private KeyRequest KEYS;

	KnutzzzGameObject_Bumper(Knutzzz parent, int keyCodeLeft, int keyCodeRight, int keyCodeThrust, int ID,
			KeyRequest KEYS) {
		this.parent = parent;
		this.KEYS = KEYS;

		VK_ButtonLeft = keyCodeLeft;
		VK_ButtonRight = keyCodeRight;
		VK_ButtonThrust = keyCodeThrust;
		this.ID = ID;

		switch (ID) {
		case 0:
			init(512, 128, 90);
			break;
		case 1:
			init(896, 512, 180);
			break;
		case 2:
			init(512, 896, 270);
			break;
		default:
			init(128, 512, 0);
		}
		spotlightImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/spotlight.png");
		loadImages(ID);

		sound = new Sound();
		sound.load("res/games/knutzzz/sfx/pling.wav");
	}

	public void collide(KnutzzzGameObject_Bumper player, KnutzzzGameObject_Bumper player2) {
		// Normale
		double nx = player2.x - player.x;
		double ny = player2.y - player.y;
		if (nx * nx + ny * ny < 1024) {

			// Beide Player auf richtigen Abstand bringen
			// double
			// inset=32-Math.sqrt((player2.x-player.x)*(player2.x-player.x)+(player2.y-player.y)*(player2.y-player.y));
			// double
			// k=inset/(Math.sqrt(player2.vx*player2.vx+player2.vy*player2.vy)+Math.sqrt(player.vx*player.vx+player.vy*player.vy));
			// player2.vx-=k*player2.vx;
			// player2.vx-=k*player2.vy;
			// player.vx-=k*player.vx;
			// player.vx-=k*player.vy;

			// Normalengeschwindigkeit
			double rvx = player2.vx - player.vx;
			double rvy = player2.vy - player.vy;
			// Normale normieren
			double absn = Math.sqrt(nx * nx + ny * ny);
			nx /= absn;
			ny /= absn;

			double vn = rvx * nx + rvy * ny;
			if (vn < 0) {
				// Kollisionspartner bewegen sich aufeinander zu
				// Elastizitaet e=1
				double e = 1;
				double j = -(1 + e) * vn;
				// Massen Player m=4, Ball m=1
				j = j * 2;
				// Impuls
				double impx = j * nx;
				double impy = j * ny;
				player.vx -= 0.25 * impx;
				player.vy -= 0.25 * impy;
				player2.vx += 0.25 * impx;
				player2.vy += 0.25 * impy;
				sound.play();
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {

		// Bilder drehen
		AffineTransform origXform = g.getTransform();
		AffineTransform newXform = (AffineTransform) (origXform.clone());

		// Rotationsmittelpunkt
		double currentAngle = Math.toRadians(angle);
		newXform.rotate(currentAngle, x, y);
		g.setTransform(newXform);

		// Bild zeichnen
		g.drawImage(driverImage, (int) x - 16, (int) y - 16, null);
		g.drawImage(spotlightImage, (int) x - 16, (int) y - 16, null);
		g.setTransform(origXform);

	}

	public void init(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;

		startx = x;
		starty = y;
		startangle = angle;
	}

	private void loadImages(int playerID) {
		// Bilder fuer Animation laden
		BufferedImage shapes = null;
		try {
			shapes = ImageIO.read(new File("res/games/knutzzz/img/bumpershapes.png"));
		} catch (IOException e) {
		}
		// Laedt die Bilder für die Animation in das Array
		carImage = shapes.getSubimage(playerID * 32, 0, 32, 32);
		driverImage = shapes.getSubimage(playerID * 32, 32, 32, 32);
	}

	@Override
	public void processInput() {
		if (parent.isGameRunning()) {
			// Tastaturabfrage: Left/Right - Drehung, Button1 - Gas geben
			if (KEYS.isPressed(VK_ButtonLeft)) {
				turn = -4;
			} else if (KEYS.isPressed(VK_ButtonRight)) {
				turn = 4;
			} else {
				turn = 0;
			}
			throttle = KEYS.isPressed(VK_ButtonThrust);
		} else {
			throttle = false;
			turn = 0;
		}
	}

	public void reset() {
		x = startx;
		y = starty;
		angle = startangle;
	}

	@Override
	public void update(long elapsed) {
		angle += turn;
		if (angle < 0)
			angle += 360;
		if (angle >= 360)
			angle -= 360;

		if (throttle) {
			ax = Math.cos(Math.toRadians(angle)) * 0.25;
			ay = Math.sin(Math.toRadians(angle)) * 0.25;

			// Geschwindigkeit aendern
			vx += ax;
			vy += ay;
		}
		vx *= 0.95;
		vy *= 0.95;

		if (vx < 0.1 && vx > -0.1)
			vx = 0.0;
		if (vy < 0.1 && vy > -0.1)
			vy = 0.0;

		// Position aendern
		x += vx;
		y += vy;

		if (x < 48) {
			x = 48;
			vx = -vx;
			sound.play();
		}
		if (y < 48) {
			y = 48;
			vy = -vy;
			sound.play();
		}
		if (x > 976) {
			x = 976;
			vx = -vx;
			sound.play();
		}
		if (y > 976) {
			y = 976;
			vy = -vy;
			sound.play();
		}

		for (Object go : parent.getPROCEEDINGINPUTS()) {
			if (go instanceof KnutzzzGameObject_Bumper && go != this) {
				collide(this, (KnutzzzGameObject_Bumper) go);
			}
		}
	}
}
