package games.knutzzz;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public class KnutzzzGameObject_Ball implements Updateable, Drawable {
	static final int START = 0;
	static final int GAME = 1;
	static final int GOAL = 2;

	double x, y;
	public double vx;
	public double vy;
	Image ballImage;
	Sound sound;
	// int state = GAME;
	public boolean goal = false;
	Knutzzz parent;
	int lastTouch = 0;

	public KnutzzzGameObject_Ball(Knutzzz parent) {
		this.parent = parent;
		ballImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/ball.png");
		sound = new Sound();
		// sound.load("res/games/knutzzz/sfx/thud.wav");
		init();
	}

	public void collide(KnutzzzGameObject_Ball ball, KnutzzzGameObject_Bumper player) {
		double nx = ball.x - player.x;
		double ny = ball.y - player.y;
		if (nx * nx + ny * ny < 576) {
			// Ball und Player auf richtigen Abstand bringen
			// double
			// inset=24-Math.sqrt((ball.x-player.x)*(ball.x-player.x)+(ball.y-player.y)*(ball.y-player.y));
			// double
			// k=inset/(Math.sqrt(ball.vx*ball.vx+ball.vy*ball.vy)+Math.sqrt(player.vx*player.vx+player.vy*player.vy));
			// ball.vx-=k*ball.vx;
			// ball.vx-=k*ball.vy;
			// player.vx-=k*player.vx;
			// player.vx-=k*player.vy;

			// Normalengeschwindigkeit
			double rvx = ball.vx - player.vx;
			double rvy = ball.vy - player.vy;
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
				j = j / (1 / 4 + 1 / 1);
				// Impuls
				double impx = j * nx;
				double impy = j * ny;
				player.vx -= 1 / 4 * impx;
				player.vy -= 1 / 4 * impy;
				ball.vx += impx;
				ball.vy += impy;
			}

			// for (int i=0; i<4; i++)
			// if (players.get(i)==player)
			lastTouch = player.ID;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(ballImage, (int) x - 8, (int) y - 8, null);
	}

	public void init() {
		x = 512;
		y = 512;
		vx = 0;
		vy = 0;
		// state = GAME;
		goal = false;
	}

	@Override
	public void update(long elapsed) {
		x += vx;
		y += vy;

		// Ball abbremsen (Reibung)
		vx *= 0.99;
		vy *= 0.99;

		// Ball stoppen, wenn er zu langsam ist
		if (vx * vx + vy * vy < 0.01) {
			vx = 0;
			vy = 0;
		}

		// Maximale Geschwindigkeit begrenzen
		double rootv = Math.sqrt(vx * vx + vy * vy);
		if (rootv > 24) {
			vx /= rootv * 24.0;
			vy /= rootv * 24.0;
		}

		if (x < 40) {
			// Ball prallt links ab oder fliegt ins Tor
			if (y < 440 || y > 582) {
				x = 40;
				vx = -vx;
				// sound.play();
				sound.instantPlay("res/games/knutzzz/sfx/thud.wav");
			} else if (y < 448 || y > 574)
				vy = -vy;
		}
		if (y < 40) {
			// Ball prallt oben ab oder fliegt ins Tor
			if (x < 440 || x > 582) {
				y = 40;
				vy = -vy;
				// sound.play();
				sound.instantPlay("res/games/knutzzz/sfx/thud.wav");
			} else if (x < 448 || x > 574)
				vx = -vx;

		}
		if (x > 984) {
			// Ball prallt rechts ab oder fliegt ins Tor
			if (y < 440 || y > 582) {
				x = 984;
				vx = -vx;
				// sound.play();
				sound.instantPlay("res/games/knutzzz/sfx/thud.wav");
			} else if (y < 448 || y > 574)
				vy = -vy;
		}

		if (y > 984) {
			// Ball prallt unten ab oder fliegt ins Tor
			if (x < 440 || x > 582) {
				y = 984;
				vy = -vy;
				// sound.play();
				sound.instantPlay("res/games/knutzzz/sfx/thud.wav");
			} else if (x < 448 || x > 574) {
				vx = -vx;
			}
		}

		if (!goal && (x < 20 || y < 20 || x > 1004 || y > 1004)) {
			goal = true;
			sound.instantPlay("res/games/knutzzz/sfx/horn.wav");

			// Punkte vergeben:
			// - Fuer das gegenueberliegende Tor
			// - Fuer den Spieler, der den Ball gespielt hat
			// - Abzug fuer Eigentore
			if (y < 20) {
				// Ball fliegt ins obere Tor
				parent.scoreDisplays[2].score++;
				if (lastTouch != 0)
					parent.scoreDisplays[lastTouch].score++;
				else if (parent.scoreDisplays[0].score > 0)
					parent.scoreDisplays[0].score--;
			}
			if (y > 1004) {
				// Ball fliegt ins untere Tor
				parent.scoreDisplays[0].score++;
				if (lastTouch != 2)
					parent.scoreDisplays[lastTouch].score++;
				else if (parent.scoreDisplays[1].score > 0)
					parent.scoreDisplays[1].score--;
			}
			if (x < 20) {
				// Ball fliegt ins linke Tor
				parent.scoreDisplays[1].score++;
				if (lastTouch != 3)
					parent.scoreDisplays[lastTouch].score++;
				else if (parent.scoreDisplays[3].score > 0)
					parent.scoreDisplays[3].score--;
			}
			if (x > 1004) {
				// Ball fliegt ins rechte Tor
				parent.scoreDisplays[3].score++;
				if (lastTouch != 1)
					parent.scoreDisplays[lastTouch].score++;
				else if (parent.scoreDisplays[1].score > 0)
					parent.scoreDisplays[1].score--;
			}
		}

		for (Object go : parent.getPROCEEDINGINPUTS()) {
			if (go instanceof KnutzzzGameObject_Bumper) {
				collide(this, (KnutzzzGameObject_Bumper) go);
			}
		}
	}
}
