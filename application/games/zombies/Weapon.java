package games.zombies;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.deprecated.Updateable;

public class Weapon implements Updateable {

	private static final Weapon PISTOL = new Weapon(15, 350f, 500,
			"res/games/zombies/textures/citizenplayer_handgun.png", null, new FireMethod() {
				@Override
				public void fire(float x, float y, float rotation, int dmg, float speed, Zombies game) {
					float newX = x, newY = y;

					final float length = 50;
					newX += Math.sin(Math.toRadians(95 + rotation)) * length;
					newY -= Math.cos(Math.toRadians(95 + rotation)) * length;

					game.add(new Bullet(newX, newY, dmg, rotation, speed, game));
				}
			});

	private static final Weapon MACHINEGUN = new Weapon(20, 500f, 100,
			"res/games/zombies/textures/citizenplayermachinegun.png", null, new FireMethod() {
				@Override
				public void fire(float x, float y, float rotation, int dmg, float speed, Zombies game) {
					float newX = x, newY = y;

					final float length = 60;
					newX += Math.sin(Math.toRadians(105 + rotation)) * length;
					newY -= Math.cos(Math.toRadians(105 + rotation)) * length;

					game.add(new Bullet(newX, newY, dmg, rotation, speed, game));
				}
			});

	private static final Weapon UZI = new Weapon(5, 600f, 75, "res/games/zombies/textures/citizenplayer_uzi.png", null,
			new FireMethod() {
				@Override
				public void fire(float x, float y, float rotation, int dmg, float speed, Zombies game) {
					float newX = x, newY = y;

					final float length = 45;
					newX += Math.sin(Math.toRadians(98 + rotation)) * length;
					newY -= Math.cos(Math.toRadians(98 + rotation)) * length;

					game.add(new Bullet(newX, newY, dmg, rotation, speed, game));
				}
			});

	private static final Weapon SHOTGUN = new Weapon(20, 300f, 750,
			"res/games/zombies/textures/citizenplayershotgun.png", null, new FireMethod() {
				@Override
				public void fire(float x, float y, float rotation, int dmg, float speed, Zombies game) {
					float newX = x, newY = y;

					final float length = 66;
					newX += Math.sin(Math.toRadians(102 + rotation)) * length;
					newY -= Math.cos(Math.toRadians(102 + rotation)) * length;
					
					game.add(new Bullet(newX, newY, dmg, rotation + 20, speed, game));
					game.add(new Bullet(newX, newY, dmg, rotation + 10, speed, game));
					game.add(new Bullet(newX, newY, dmg, rotation, speed, game));
					game.add(new Bullet(newX, newY, dmg, rotation - 10, speed, game));
					game.add(new Bullet(newX, newY, dmg, rotation - 20, speed, game));
				}
			});

	private int dmg;
	private float speed;
	private long wait;
	private Image image;
	private Zombies game;
	private FireMethod method;
	private long timer = 0;

	private Weapon(int dmg, float speed, long wait, String image, Zombies game, FireMethod m) {
		this.dmg = dmg;
		this.speed = speed;
		this.wait = wait;
		this.game = game;
		this.method = m;
		try {
			this.image = ImageIO.read(new File(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Weapon(Weapon w, Zombies game) {
		this.dmg = w.getDmg();
		this.speed = w.getSpeed();
		this.wait = w.getWait();
		this.image = w.getImage();
		this.method = w.getMethod();
		this.game = game;

		game.add(this);
	}

	public void fire(float x, float y, float rotation) {
		if (timer <= 0) {
			method.fire(x, y, rotation, dmg, speed, game);
			timer = wait;
		}
	}

	public int getDmg() {
		return dmg;
	}

	public float getSpeed() {
		return speed;
	}

	public long getWait() {
		return wait;
	}

	public Image getImage() {
		return image;
	}

	public FireMethod getMethod() {
		return method;
	}

	public static Weapon getPistol(Zombies game) {
		return new Weapon(PISTOL, game);
	}

	public static Weapon getUzi(Zombies game) {
		return new Weapon(UZI, game);
	}

	public static Weapon getMachinegun(Zombies game) {
		return new Weapon(MACHINEGUN, game);
	}

	public static Weapon getShotgun(Zombies game) {
		return new Weapon(SHOTGUN, game);
	}

	@Override
	public void update(long elapsed) {
		if (timer > 0) {
			timer -= elapsed;
		}
	}

}
