package games.bomberman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Seat;

public class Field implements Drawable {
	int[][] ground = new int[32][32];
	BufferedImage Grass1, Grass2, Grass3, SolidBlock, Block;

	public Field() {
		int[] vals = { 0, 1 };

		ground = MazeGenerator.generate(32, 32);

		for (int x = 0; x < 32; x++) {
			for (int y = 0; y < 32; y++) {
				if (Math.random() < 0.15) {
					ground[x][y] = 2;
				}
			}
		}

		for (int x = 0; x < 32; x++) {
			ground[x][0] = 2;
			ground[x][31] = 2;
			ground[0][x] = 2;
			ground[31][x] = 2;
		}

		if (Seat.P3.isPlaying()) {
			ground[16][24] = 0;
			ground[16][25] = 0;
			ground[17][24] = 0;
		}
		if (Seat.P4.isPlaying()) {
			ground[8][16] = 0;
			ground[8][17] = 0;
			ground[9][16] = 0;
		}
		if (Seat.P1.isPlaying()) {
			ground[16][8] = 0;
			ground[16][9] = 0;
			ground[17][8] = 0;
		}
		if (Seat.P2.isPlaying()) {
			ground[24][16] = 0;
			ground[25][16] = 0;
			ground[24][17] = 0;
		}

		try {
			Grass1 = ImageIO.read(new File("res/games/bomberman/Floor1.png"));
			Grass2 = ImageIO.read(new File("res/games/bomberman/Floor2.png"));
			Grass3 = ImageIO.read(new File("res/games/bomberman/Floor3.png"));
			Block = ImageIO.read(new File("res/games/bomberman/BrokenWall.png"));
			SolidBlock = ImageIO.read(new File("res/games/bomberman/Wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (int x = 0; x < ground.length; x++) {
			for (int y = 0; y < ground[x].length; y++) {
				switch (ground[x][y]) {
				case 0:
					if (x % 3 == 0) {
						g.drawImage(Grass1, x * 32, y * 32, 32, 32, null);
					} else if (x % 2 == 0) {
						g.drawImage(Grass2, x * 32, y * 32, 32, 32, null);
					} else {
						g.drawImage(Grass3, x * 32, y * 32, 32, 32, null);
					}
					break;
				case 1:
					g.drawImage(Block, x * 32, y * 32, 32, 32, null);
					break;
				case 2:
					g.drawImage(SolidBlock, x * 32, y * 32, 32, 32, null);
					break;
				}
			}
		}
	}

	public void generate(int[][] field, int x, int y) {
	}

}
