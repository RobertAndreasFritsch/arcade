// package games.tank;
//
// import java.awt.Graphics2D;
// import java.awt.Image;
// import java.awt.Toolkit;
// import java.awt.geom.AffineTransform;
//
// import environment.model.Game;
// import environment.model.KeyRequest;
// import environment.model.gameobject.Drawable;
// import environment.model.gameobject.ProceedsInput;
// import environment.model.gameobject.Seat;
// import environment.model.gameobject.Updateable;
//
// public class Tank implements Drawable, Updateable, ProceedsInput {
// // ------------------------------------------------------------------Decs
// public static final int bulletcount = 5;
//
// int speed = 0;
// double TankPosX, TankPosY;
//
// Seat player;
// // public int score = 0;
//
// int angle;
// int maxAngle = 360;
// int anglespeed = 0;
//
// Image TankObject =
// Toolkit.getDefaultToolkit().getImage("res/games/tank/img/Tankred.png");
// private Field field;
// private Bullet[] bullets = new Bullet[bulletcount];
//
// private Tank[] tankArray;
//
// private KeyRequest keys;
//
// private MusicPlayer musicPlayer;
//
// private Game parent;
//
// //
// ------------------------------------------------------------------Constructor
// public Tank(Seat player, int x, int y, Field field, Tank[] tankArray,
// KeyRequest keys, MusicPlayer musicPlayer,
// Game parent) {
// this.player = player;
// this.field = field;
// this.keys = keys;
// this.musicPlayer = musicPlayer;
// this.parent = parent;
// this.tankArray = tankArray;
//
// TankPosX = x;
// TankPosY = y;
// }
//
// public void backward() {
// speed = -2;
// }
//
// // ------------------------------------------------------------------Methods
// @Override
// public void draw(Graphics2D g) {
//
// int PaintX = (int) TankPosX;
// int PaintY = (int) TankPosY;
//
// AffineTransform origform = g.getTransform();
// AffineTransform newform = (AffineTransform) (origform.clone());
//
// newform.rotate(Math.toRadians(angle), PaintX, PaintY);
// g.setTransform(newform);
//
// g.drawImage(TankObject, PaintX - 16, PaintY - 16, null);
//
// g.setTransform(origform);
// }
//
// public void forward() {
// speed = 4;
// }
//
// public void leftspin() {
// anglespeed = -5;
//
// }
//
// @Override
// public void processInput() {
//
// if (keys.isPressed(player.UP)) {
// this.forward();
// this.stop();
// }
//
// if (keys.isPressed(player.DOWN)) {
// this.backward();
// this.stop();
// }
//
// if (keys.isPressed(player.LEFT)) {
// this.leftspin();
// this.spinstop();
// }
//
// if (keys.isPressed(player.RIGHT)) {
// this.rightspin();
// this.spinstop();
// }
//
// if (keys.isPressed(player.BTN1)) {
// this.shoot();
// }
// }
//
// public void rightspin() {
// anglespeed = 5;
// }
//
// public void shoot() {
// int c = 0;
// System.out.println(player + " Fire");
//
// // Testen ob Platz im Array
//
// for (int i = 0; i < bullets.length; i++) {
// if (bullets[i] == null) {
//
// Bullet loadedBullet = new Bullet(player, field, 1, tankArray);
//
// loadedBullet.setBulletPosX(TankPosX);
// loadedBullet.setBulletPosY(TankPosY);
//
// loadedBullet.setAngle(angle);
// loadedBullet.init();
//
// parent.add(bullets[i] = loadedBullet);
//
// System.out.println("Kugel " + c + " von Spieler " + player + " mit der
// Position "
// + loadedBullet.getBulletPosX() + " | " + loadedBullet.getBulletPosY() + "
// erstellt.");
//
// break;
// }
// }
// MusicPlayer.Playsound(musicPlayer.Shoot);
// }
//
// public void spinstop() {
// anglespeed = 0;
// }
//
// public void stop() {
// speed = 0;
// }
//
// @Override
// public void update(long elapsed) {
// // Position
// double oldx = TankPosX;
// double oldy = TankPosY;
//
// // Bewegen
// TankPosX = TankPosX + Math.cos(Math.toRadians(angle)) * speed;
// TankPosY = TankPosY + Math.sin(Math.toRadians(angle)) * speed;
//
// // Kollision
// int posx = (int) (TankPosX + Math.cos(Math.toRadians(angle)) * 16 *
// Math.signum(speed)) / 32;
// int posy = (int) (TankPosY + Math.sin(Math.toRadians(angle)) * 16 *
// Math.signum(speed)) / 32;
//
// if (field.getSpielfeld()[posx][posy].typ > 0) {
// // System.out.println("Crash an "+posx+":"+posy+" mit
// // "+TankPosX+":"+TankPosY);
// TankPosX = oldx;
// TankPosY = oldy;
// }
//
// angle += anglespeed;
// if (angle >= 360) {
// angle -= 360;
// }
// if (angle < 0) {
// angle += 360;
// }
// }
//
// }
