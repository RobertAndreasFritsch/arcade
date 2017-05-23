//package games.tank;
//
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.geom.AffineTransform;
//
//import environment.model.gameobject.Drawable;
//import environment.model.gameobject.Seat;
//import environment.model.gameobject.Updateable;
//
//public class Bullet implements Updateable, Drawable {
//	// ------------------------------------------------------------------Decs
//	public static final int maxAngle = 360;
//
//	private int BulletSpeed = 7;
//	private int type;
//	private int timer = 200;
//	private Seat player;
//
//	private double BulletPosX, BulletPosY;
//
//	private double angle;
//	private int anglespeed = 0;
//	private double spx;
//	private double spy;
//
//	private double[][] post = new double[4][2];
//
//	private Image BulletObject;
//
//	private Field field;
//
//	private int playerB;
//
//	private Tank[] tankArray;
//
//	// ------------------------------------------------------------------ctor
//	public Bullet(Seat player, Field field, int playerB, Tank[] tankArray) {
//		this.field = field;
//		this.playerB = playerB;
//		this.tankArray = tankArray;
//
//		this.BulletObject = GamePanel.StandardBulletObject;
//		this.player = player;
//
//	}
//
//	@Override
//	public void draw(Graphics2D g) {
//
//		int PaintX = (int) BulletPosX;
//		int PaintY = (int) BulletPosY;
//
//		// Drehen des Objekts
//		Graphics2D g2d = g;
//		AffineTransform origform = g2d.getTransform();
//		AffineTransform newform = (AffineTransform) (origform.clone());
//		newform.rotate(Math.toRadians(angle), PaintX, PaintY);
//		g2d.setTransform(newform);
//
//		g2d.drawImage(BulletObject, PaintX - 2, PaintY - 2, null);
//		g2d.setTransform(origform);
//
//	}
//
//	public double getAngle() {
//		return angle;
//	}
//
//	public double getBulletPosX() {
//		return BulletPosX;
//	}
//
//	public double getBulletPosY() {
//		return BulletPosY;
//	}
//
//	public void init() {
//		spx = Math.cos(Math.toRadians(angle)) * BulletSpeed;
//		spy = Math.sin(Math.toRadians(angle)) * BulletSpeed;
//		BulletPosX += Math.cos(Math.toRadians(angle)) * 18;
//		BulletPosY += Math.sin(Math.toRadians(angle)) * 18;
//	}
//
//	public void setAngle(double angle) {
//		this.angle = angle;
//	}
//
//	public void setBulletPosX(double bulletPosX) {
//		BulletPosX = bulletPosX;
//	}
//
//	public void setBulletPosY(double bulletPosY) {
//		BulletPosY = bulletPosY;
//	}
//
//	@Override
//	public void update(long elapsed) {
//		// Kollision------------------------------------------------------------------------------------------------
//
//		// Bulletposition im naechsten Takt im Array
//		int posax = (int) ((BulletPosX + spx) / 32);
//		int posay = (int) ((BulletPosY + spy) / 32);
//
//		// Bulletposition in diesem Takt im Array
//		// int aktx = (int) (BulletPosX / 32);
//		int akty = (int) (BulletPosY / 32);
//
//		// System.out.println("ArrayX"+posax+"BulletX"+BulletPosX);
//		// System.out.println("ArrayY"+posay+"BulletY"+BulletPosY);
//
//		if (field.getSpielfeld()[posax][posay].typ > 0) {
//			// BulletPosX = oldx;
//			// BulletPosY = oldy;
//
//			if (akty != posay) {
//				spy = -spy;
//			} else {
//				spx = -spx;
//			}
//			// System.out.println("Kollision an " + posax + ":" + posay + " von
//			// " + aktx + ":" + akty);
//
//		}
//		// Bewegung
//		BulletPosX = BulletPosX + spx;
//		BulletPosY = BulletPosY + spy;
//
//		// Kugel-Tank Kollision
//
//		for (int x = 0; x < tankArray.length; x++) {
//			if (tankArray[x] != null) {
//
//				// if (BulletPosX == gamePanel.TankArray[x].TankPosX &&
//				// BulletPosY == gamePanel.TankArray[x].TankPosY)
//				if ((tankArray[x].TankPosX - BulletPosX) * (tankArray[x].TankPosX - BulletPosX)
//						+ (tankArray[x].TankPosY - BulletPosY) * (tankArray[x].TankPosY - BulletPosY) < 144) {
//					System.out.println("Tankkollision erkannt");
//					if (playerB == x) {
//						if (player.getScore() > 0) {
//							player.setScore(player.getScore() - 1);
//						}
//					} else {
//						player.setScore(player.getScore() + 1);
//					}
//					// Explosion und Entfernen
//					tankArray[x] = null;
//				}
//			}
//		}
//
//		timer--;
//
//	}
//
//}
