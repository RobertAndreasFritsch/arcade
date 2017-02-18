package games.kickthemoff;

import java.awt.Graphics2D;

import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

public class Player implements Drawable, ProceedsInput, Updateable {
	private Seat s;
	private float x, y;
	private float accelerationX, accelerationY;
	private KeyRequest keys;
	private boolean dead = false, dying = false;
	private float scale = 1;
	private Ground g;
	private Player[] otherPlayers;
	private Kickthemoff game;
	private Player lastKickedBy = this;

	public Player(Kickthemoff game,Seat s, int x, int y, KeyRequest keys, Ground g, Player ... otherPlayers) {
		this.s = s;
		this.x = x;
		this.y = y;
		accelerationX = 0;
		accelerationY = 0;
		this.keys = keys;
		this.g = g;
		this.otherPlayers = otherPlayers;
		this.game = game;
	}

	@Override
	public void processInput() {
		if (dead|dying) {
			return;
		}
		if (keys.isPressed(s.UP)) {
			accelerationY -= 0.05;
		}
		if (keys.isPressed(s.DOWN)) {
			accelerationY += 0.05;
		}
		if (keys.isPressed(s.LEFT)) {
			accelerationX -= 0.05;
		}
		if (keys.isPressed(s.RIGHT)) {
			accelerationX += 0.05;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (dead) {
			return;
		}
		g.setColor(s.getColor());
		g.fillOval((int) (x - 25 * scale), (int) (y - 25 * scale), (int) (50 * scale), (int) (50 * scale));
	}

	@Override
	public void update(long elapsed) {
		if (dead) {
			return;
		}
		x += accelerationX;
		y += accelerationY;

		accelerationX -= Math.abs(accelerationX) > 0 ? accelerationX > 0 ? 0.01 : -0.01 : 0;
		accelerationY -= Math.abs(accelerationY) > 0 ? accelerationY > 0 ? 0.01 : -0.01 : 0;

		float dx = x - g.getX();
		float dy = y - g.getY();
		float len = (float) Math.sqrt(dx * dx + dy * dy);
		if (len > g.getR() & !dying) {
			dying = true;
			lastKickedBy.getSeat().setScore(lastKickedBy.getSeat().getScore()+50);
			new Thread(){
				public void run(){
					while(scale>0){
						scale -= 0.05;
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					dead = true;
				}
			}.start();
		}
		
		for(Player p:game.getPlayers()){
			if(p != this){
				doCollision(this, p);
			}
		}
	}

	public float getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(float accelerationX) {
		this.accelerationX = accelerationX;
	}

	public float getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(float accelerationY) {
		this.accelerationY = accelerationY;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public boolean isDead() {
		return dead;
	}
	
	public Seat getSeat(){
		return s;
	}

	public Player getLastKickedBy() {
		return lastKickedBy;
	}

	public void setLastKickedBy(Player lastKickedBy) {
		this.lastKickedBy = lastKickedBy;
	}

	public static void doCollision(Player p1, Player p2){
		float dx = p2.getX() - p1.getX();
		float dy = p2.getY() - p1.getY();
		float len = (float) Math.sqrt(dx * dx + dy * dy);
		if(len<=50){
			float oldp2accX = p2.getAccelerationX();
			float oldp2accY = p2.getAccelerationY();
			p2.setAccelerationX(p1.getAccelerationX() - p2.getAccelerationX());
			p2.setAccelerationY(p1.getAccelerationY() - p2.getAccelerationY());
			p1.setAccelerationX(oldp2accX - p1.getAccelerationX());
			p1.setAccelerationY(oldp2accY - p1.getAccelerationY());
			p1.setLastKickedBy(p2);
			p2.setLastKickedBy(p1);
		}
	}
	
}
