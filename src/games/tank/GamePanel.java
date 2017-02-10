package games.tank;

import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public class GamePanel {

	static Image StandardBulletObject;

	Thread timer; // Texturen

	// Waffen
	Image spaceImage, wallImage; // Bullet Textur
	Image ExplosionObject;
	boolean Explosion = false;
	double ExplosionPosX, ExplosionPosY;
	String Setting;

	// PanzerVariablen
	Tank TankRed;
	Tank TankBlue;
	Tank TankOrange;
	Tank TankPurple;

	Tank[] tankArray = { TankRed, TankBlue, TankOrange, TankPurple };

	// MenuVariablen
	boolean menu = false;
	boolean option = false;
	int scrollpx = 0;
	int scrollpy = 0;

	// MenuImages
	public Image cover1;
	public Image cover2;
	public Image btd;
	public Image auswahl;
	public Image cursor;
	public Image cursor2;
	public Image pre;

	// KugelArray
	Bullet[][] BulletArray = new Bullet[4][5];

	// Score
	Image[] ScoreImageArray = new Image[4];
	Image score;
	int maxScore = 9;
	int WinnerPlayer;
	boolean winner = false;
	boolean matchwinner = false;
	boolean draw = false;
	boolean RoundOver = false;
	Image RoundOverObject;
	Image WinnerObject;

	// Spieler
	int Spieleranzahl;

	// ----------------------------------------------------------------------------------------------------------------------
	int cp = 0;

	// Spielfeld
	// ------------------------------------------------------------------------------------------------------------------

	// Menu
	// ------------------------------------------------------------------------------------------------------------------
	int c = 0;

	public GamePanel(Tank[] tankArray) {
		this.tankArray = tankArray;

		// setPreferredSize(new Dimension((int)
		// getToolkit().getScreenSize().getWidth(),
		// (int) getToolkit().getScreenSize().getHeight()));
		setPreferredSize(new Dimension(1024, 1024));
		this.Spieleranzahl = tankArray.length;

		// MenuImages
		cover1 = getToolkit().getImage("img/Menu/Cover.gif");
		btd = getToolkit().getImage("img/Menu/p1pf.gif");
		auswahl = getToolkit().getImage("img/Menu/Auswahl2.png");
		cursor = getToolkit().getImage("img/Menu/cursor.png");
		cursor2 = getToolkit().getImage("img/Menu/Startcursor.png");

		// SpielfeldImages
		StandardBulletObject = getToolkit().getImage("img/Bullet.png");
		RoundOverObject = getToolkit().getImage("img/Score/Rover.png");

		timer = new Thread(this);
		timer.start();
	}

	public void options() {
		option = true;
	}

	public void paint(Graphics g) {
		// g.fillRect(0, 0, 1024, 1024);
		// g.drawLine(0, 0, 200, 300);
		// g.drawRect(50, 60, 100, 150);

		// Menu-----------------------
		if (!menu) {

			g.drawImage(cover1, 0, 0, this);
			g.drawImage(btd, 212 - 150, 912 - 500, this);

			if (option == true) {

				g.drawImage(cover1, 0, 0, this);
				g.drawImage(auswahl, 212, 112 - 100, this);
				g.drawImage(pre, 247 + 60, 147 + 230, this);

				if (scrollpx == 0) {
					pre = getToolkit().getImage("img//" + Settings[scrollpy] + "//pre.png");
					g.drawImage(cursor, 247 + 2, 147 + 220, this);
				}

				if (scrollpx == 1) {
					g.drawImage(cursor2, 247 + 40, 147 + 455, this);
				}
			}
		}

		// Panzer
		for (int i = 0; i < 4; i++) {
			if (TankArray[i] != null)
				TankArray[i].paint(g);
		}
		// Kugeln
		for (cp = 0; cp < 4; cp++) {
			for (c = 0; c < 5; c++) {
				if (BulletArray[cp][c] != null) {
					BulletArray[cp][c].paint(g);
				}
			}
		}
		// //Explosion
		// if (Explosion == true){
		// ExplosionObject =
		// getToolkit().getImage("img/Explosion/Explosion.gif");
		// g.drawImage(ExplosionObject, (int)ExplosionPosX-20,
		// (int)ExplosionPosY-18, this);
		// }
		//
		// //RoundOver
		// if (RoundOver == true){
		// g.drawImage(RoundOverObject, 212 - 150, 912 - 500, this);
		// if (matchwinner == true){
		// WinnerObject =
		// getToolkit().getImage("img/Score/p"+WinnerPlayer+"won.png");
		// g.drawImage(WinnerObject, 212 - 150, 200, this);
		// }
		// }
	}

	public void scrolld() {

		if (scrollpx <= 1 && option == true) {
			scrollpx = 1;
		}

	}

	// Start----------------------------------------------------------------------------------------------------------
	public void scrolll() {
		if (option == true && scrollpx == 0) {
			scrollpy = scrollpy - 1;
			if (scrollpy < 0) {
				scrollpy = 6;
			}

		}
	}

	// Zeichnen
	// ---------------------------------------------------------------------------------------------------

	public void scrollr() {

		if (option == true && scrollpx == 0) {
			scrollpy = scrollpy + 1;
			if (scrollpy > 6) {
				scrollpy = 0;
			}
		}
	}

	}

	// ------------------------------------------------------------------------------------------------------------
	public void scrollu() {
		if (scrollpx <= 1 && option == true) {
			scrollpx = 0;
		}

	}

	public void start(MusicPlayer musicPlayer) {
		if (scrollpx == 1) {
			// Setting aus array
			Setting = Settings[scrollpy];

			// Musicplayer Setting geben
			musicPlayer.play = "music//" + Setting + "//Soundtrack.mp3";
			// MusicPlayer neu starten
			musicPlayer.timerPlayer.stop();
			musicPlayer.timerPlayer = new Thread(musicPlayer);
			musicPlayer.timerPlayer.start();

			Explosion = false;

			menu = true;
		}
	}

	// @Override
	// public void run() {
	// while (timer != null) {
	// if (menu) {
	// //Testen ob noch jmd am Leben
	// int dead = 0;
	// int counter = 0;
	// for (int c = 0; c < 4; c++){
	// if (TankArray[c] == null){
	// dead ++;
	// }
	// }
	//
	// //Testen ob noch Kugeln fliegen
	// for (cp = 0; cp < 4; cp++) {
	// for (c = 0; c < 5; c++) {
	// if (BulletArray[cp][c] != null) {
	// counter ++;
	// }
	// }
	// }
	// if (counter == 0){
	// //Kein Gewinner
	// if (dead == 4){
	// winner = false;
	// draw = true;
	// System.out.println("draw");
	// }
	// //Gewinner
	// else if (dead == 3){
	// winner = true;
	// System.out.println("winner");
	// }
	// }
	//
	//
	// //Ende der Runde Testen
	// if (winner == true){
	// winner = false;
	// for (int T = 0; T<4; T++){
	// if (TankArray[T] != null){
	// PlayerArray[T].score ++;
	// WinnerPlayer = T;
	// RoundOver = true;
	// }
	// }
	// }
	// else if(draw == true){
	// draw = false;
	// RoundOver = true;
	// }
	//
	// //Score prüfen
	// for (int p = 0 ; p < 4 ; p++){
	// if (PlayerArray[p] != null){
	// if (PlayerArray[p].score > maxScore){
	// matchwinner = true;
	// RoundOver = true;
	// PlayerArray[p].globalScore ++;
	// //Score Reset
	// for (int z=0;z<4;z++ ){
	// if (PlayerArray[z] != null){
	// PlayerArray[z].score=0;
	// }
	// }
	// PlayerArray[WinnerPlayer].score = 0;
	// //Kugeln entfernen
	// for (cp = 0; cp < 4; cp++) {
	// for (c = 0; c < 5; c++) {
	// if (BulletArray[cp][c] != null) {
	// BulletArray[cp][c] = null;
	// }
	// }
	// }
	// }
	// }
	// }
	//
	// // Panzer
	// for(int i=0;i<4;i++)
	// {
	// if(TankArray[i]!=null)
	// {
	// TankArray[i].clock(this);
	//
	// }
	// }
	//
	// // Kugeln
	// for (cp = 0; cp < 4; cp++) {
	/// *BUG*/ for (c = 0; c <= 4; c++) {
	// if (BulletArray[cp][c] != null && c >= 0) {
	// BulletArray[cp][c].clock(this, c, PlayerArray, Spieleranzahl, Explosion,
	// ExplosionPosX, ExplosionPosY);
	// }
	// }
	// }
	// }
	//
	// repaint();
	//
	// // Kugeln aufraeumen
	// for (cp = 0; cp < 4; cp++) {
	// for (c = 0; c < 5; c++) {
	// if (cp <= 3 && BulletArray[cp][c] != null && BulletArray[cp][c].timer <=
	// 0) {
	// BulletArray[cp][c] = null;
	// System.out.println("Kugel " + c + " von Spieler " + cp + " entfernt.");
	// }
	// }
	// }
	// if (RoundOver == true){
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// }
	// menu = false;
	// RoundOver = false;
	//// scrollpy = scrollpy -1;
	//// if (scrollpy < 0) {
	//// scrollpy = 6;
	//// }
	// scrollpx = 1;
	// }
	// try {
	// Thread.sleep(30);
	// } catch (InterruptedException e) {
	// }
	//
	// }
	//
	// }

}
