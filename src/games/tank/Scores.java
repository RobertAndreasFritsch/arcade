package games.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import environment.model.Game;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;
import games.utils.Seat;

public class Scores implements Updateable, Drawable {

	public static final BufferedImage[][] scoreImages = new BufferedImage[4][10];

	static {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 10; j++) {
				try {
					scoreImages[i][j] = ImageIO.read(new File("res/games/tankies/img/score/" + i + "" + j + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	int cp = 0;
	int c = 0;

	Image[] ScoreImageArray = new Image[4];
	Image score;
	BufferedImage landrover;
	int maxScore = 9;
	int WinnerPlayer = -1;
	boolean winner = false;
	boolean matchwinner = false;
	boolean draw = false;
	boolean RoundOver = false;

	Tank[] TankArray;
	BulletFactory bulletFactory;
	Player[] PlayerArray;
	private final Game game;

	public Scores(final Tank[] tankArray, final Player[] player, final BulletFactory bulletFactory, final Game game) {
		
		super();
		this.TankArray = tankArray;
		this.bulletFactory = bulletFactory;
		this.PlayerArray = player;
		this.game = game;
		
		try {
			landrover = (BufferedImage) ImageIO.read(new File("res/games/tankies/img/Score/Rover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(final Graphics2D g) {
		AffineTransform originalTransform = g.getTransform();
		
		if (RoundOver) {

			g.rotate(Math.toRadians(buffertime*.05%360), 516, 516);
			g.drawImage(landrover, 516- (landrover.getWidth()>>1),  516- (landrover.getHeight()>>1), null);
			g.setTransform(originalTransform);
		}
		
		for (int i = 0; i < 4; i++) {
			
			Seat seat = Seat.getSeat(i);
			int playerScore = this.PlayerArray[i].score % 10;
			
			g.rotate(seat.getTransformation(), 516, 516);
			g.drawImage(scoreImages[i][playerScore], 0,  1048 - 64, null);
			g.setTransform(originalTransform);
		}
	}

	long MAX_BUFFER_TIME = 6000;
	long buffertime = 0;

	@Override
	public void update(final long elapsed) {// Testen ob noch jmd am Leben

		if (this.RoundOver) {
			this.buffertime += elapsed;

			if (this.buffertime >= this.MAX_BUFFER_TIME) {
				this.game.setRunning(false);
			}

			return;
		}

		int dead = 0;
		for (int c = 0; c < 4; c++) {
			if (this.TankArray[c] == null) {
				dead++;
			}
		}

		// Testen ob noch Kugeln fliegen
		// counter = ;

		if (this.bulletFactory.size() == 0 && dead >= 3) {
			// Ende der Runde Testen

			// Gewinner
			if (dead == 3) {
				// Gewinner finden
				for (int i = 0; i < 4; i++) {
					if (this.TankArray[i] != null) {
						this.PlayerArray[i].score = this.PlayerArray[i].score + 2;
						this.WinnerPlayer = i;
					}
				}
			}

			this.RoundOver = true;
		}
	}
}
