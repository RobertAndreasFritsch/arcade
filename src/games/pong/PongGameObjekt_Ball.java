package games.pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;
import games.pong.Tiles.Tile;
import games.utils.Seat;

//03.02.2017
/**
 * @author default
 *
 */
public class PongGameObjekt_Ball implements Updateable, Drawable {
	static int i =0;
	static int tz=0;
	static int pl = 4;
	static int end = 0;
	static int tb = 300;
	static int th = 50;
	static int pkt = 0;
	static int a=0;
	static int b=0;
	static int c=0;
	static int d=0;
	static int e=0;
	static int p =5;
	static double pBx=0;
	static double pBy=0;
	static int Zaehler=0;
	int Ballstartrichtung = 0;
	//TODO hard coded
	File F = new File("res/games/pong/sounds/Pong.wav");

	/**
	 * <h1>not documented yet</h1>
	 * 
	 * @author Mathis Kautz
	 * 
	 */
	double x = 910;

	/**
	 * 
	 */
	double y = 490;
	/**
	 * 
	 */
	Rectangle rect = new Rectangle((int) x, (int) y, 30, 30);
	/**
	 * 
	 */
	ArrayList<Object> tiles = new ArrayList<Object>();

	// PongGameObjekt_Blocker A = new PongGameObjekt_Blocker();
	/**
	 * <h1>not documented yet</h1>
	 * 
	 * @author Mathis Kautz
	 * 
	 */
	boolean state1, state2, state3, state4, state5; 
	boolean YisDeath = false;
	boolean XisDeath = false;
	boolean blockerlimit = false;
	/**
	 * 
	 */

	/**
	 * 
	 */
	/**
	 * 
	 */
	double dx, dy;
	/**
	 * 
	 */
	PongGameObjekt_Blocker[] blockers;

	private Game game;

	/**
	 * @param keyCode3
	 * @param keyCode4
	 * @param keyCode5
	 * @param keyCode6
	 * @param keyCode7
	 * @param keyCode8
	 * @param tiles
	 * @param blockers
	 * @throws Exception
	 */
	PongGameObjekt_Ball(Game game,KeyRequest KEYS, ArrayList<Object> tiles, PongGameObjekt_Blocker[] blockers) {
		this.game = game;
		
		this.tiles = tiles;
		this.blockers = blockers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gamelibrary.GameObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D GRAPHICS) {

		GRAPHICS.fillRoundRect((int) x, (int) y, 20, 20, 100, 100);
	}

	static long Roundtime = 0;
	long startBuffer = 2000;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gamelibrary.GameObject#update(java.util.List)
	 */
	@Override
	public void update(long elapsed) {
//		System.out.println(startBuffer);
//		System.out.println("-->>"+ ""+elapsed);
		if (startBuffer > 0 && e!=0) {
			startBuffer -= elapsed;
			return;}
		if (startBuffer > 0 && e==0) {
			startBuffer -= elapsed;
			e++;
			i=0;
			tz=0;
			end=0;
			return;
		} else if(i==0) {
			state5 = true;
			i++;
			}
		
		
		// Blockerrelevant
		int b = 100;
		int l = 100;
		double bx = 25;
		double by = 25;
		// andere

		x += dx;
		y += dy;
		rect.x = (int) x;
		rect.y = (int) y;
		
		Tile playerNorth = (Tile) tiles.get(0);
		Tile playerEast = (Tile) tiles.get(1);
		Tile playerSouth = (Tile) tiles.get(2);
		Tile playerWest = (Tile) tiles.get(3);
		int pBx2 = (int) (blockers[0].getX() + x);
		int pBy2 = (int) (blockers[0].getX() + y);
		if(tz==0){
			//hard coded
			//erste Positionen

		starter(playerEast, playerNorth, playerSouth, playerWest, l);
		
	}
		//Feldgrenze bzw. Torlinien
				if (rect.x>1024){
					if(playerEast.getTor()!=0){
					playerEast.setTor(playerEast.getTor()-1);
					if(pkt!=2){
					Pkterhalten(pkt,playerEast, playerNorth, playerSouth, playerWest);
					}
					if(playerEast.getTor()==0){
						playerEast.setheight(1024);
						playerEast.setwidth(b);
						playerEast.seti();
						state2 = true;
						end++;
					}
					startBuffer=startBuffer+5+2000;
					state5 = true;
				}}
				if(rect.x<0){
					if(playerWest.getTor()!=0){
					playerWest.setTor(playerWest.getTor()-1);
					if(pkt!=4){
					Pkterhalten(pkt,playerEast, playerNorth, playerSouth, playerWest);
					}
					if(playerWest.getTor()==0){
						playerWest.setheight(1024);
						playerWest.setwidth(b);
						playerWest.seti();
						state4 = true;
						end++;
					}
					startBuffer=startBuffer+5+2000;
					 state5 = true;}}
				if (rect.y>1024){
					if(playerSouth.getTor()!=0){
					playerSouth.setTor(playerSouth.getTor()-1);
					if(pkt!=3){
					Pkterhalten(pkt,playerEast, playerNorth, playerSouth, playerWest);
					}
					if(playerSouth.getTor()==0){
						playerSouth.setwidth(1024);
						playerSouth.setheight(l);
						playerSouth.seti();
						state3 = true;
						end++;
					}
					startBuffer=startBuffer+5+2000;
					 state5 = true;
					 }}
				if(rect.y<0){
					if(playerNorth.getTor()!=0){
					playerNorth.setTor(playerNorth.getTor()-1);
					if(pkt!=1){
					Pkterhalten(pkt,playerEast, playerNorth, playerSouth, playerWest);
					}
					if(playerNorth.getTor()==0){
						playerNorth.setwidth(1024);
						playerNorth.setheight(l);
						playerNorth.seti();
						state1 = true;
						end++;
					}
					startBuffer=startBuffer+5+2000;
				 	 state5 = true;}}
				
				//2Spieler fehlen auf einer Achse:
				if(state2==true && state4==true && XisDeath==false){
					dy = 20;
					dx = 0;
					XisDeath=true;
				}
				if(state1==true && state3==true && YisDeath==false){
					dx = 20;
					dy = 0;
					YisDeath=true;
				}
				//Torspeeren: 
				if(state2==true && x+bx >=blockers[1].getX()){
					dx = -Math.abs(dx);
					blockers[1].seth(1024);
					Playsound(F);
				}
				if(state4==true && x <=blockers[0].getX()+l){
					dx = Math.abs(dx);
					blockers[0].seth(1024);
					Playsound(F);
				}
				if(state3==true && y+by >=blockers[3].getY()){
					dy = -Math.abs(dy);
					blockers[3].setb(1240);
					Playsound(F);
				}
				if(state1==true && y <=blockers[0].getY()+l){
					dy = Math.abs(dy);
					blockers[0].setb(1240);
					Playsound(F);
				}
		// Linker Spieler

		
		
		if (y <= playerWest.getY() + 300 && y + by >= playerWest.getY() && x <= playerWest.getX() + 55 && x + bx >= playerWest.getX()) {
			if (pBx2 <= playerWest.getX() || pBx2 >= playerWest.getX()) {
				dx = Math.abs(dx);
				//dx = 0;
			}
			if (pBy2 <= playerWest.getY() || pBy2 >= playerWest.getY() + 300) {

				dy = dy * -1;
			}

			if (y<=playerWest.getY()+50) {
				dy = dy - 5;
				
			}
			if (y<=playerWest.getY()+100) {
				dy = dy - 5;
			}
			if(y>=playerWest.getY()+200){
				dy = dy +5;
			}
			if(y>=playerWest.getY()+250){
				dy = dy +5;
			}
			Playsound(F);
			pkt=4;
			Zaehler=0;
		}
		// Rechter Spieler
		if (y <= playerEast.getY() + 300 && y + by >= playerEast.getY() && x <= playerEast.getX() + 55 && x + bx >= playerEast.getX()) {

			if (pBx2 <= playerEast.getX() || pBx2 >= playerEast.getX()) {
				dx = -Math.abs(dx);
				//dx = 0;
			}
			if (pBy2 <= playerEast.getY() || pBy2 >= playerEast.getY() + 300) {
				dy = dy * -1;
			}
			if (y<=playerEast.getY()+50) {
				dy = dy - 5;
				
			}
			if (y<=playerEast.getY()+100) {
				dy = dy - 5;
			}
			if(y>=playerEast.getY()+200){
				dy = dy +5;
			}
			if(y>=playerEast.getY()+250){
				dy = dy +5;
			}
			Playsound(F);
			pkt=2;
			Zaehler=0;
		}
		// oberer Spieler
		
		if (y <= playerNorth.getY() + 55 && y + by >= playerNorth.getY() && x <= playerNorth.getX() + 300 && x + bx >= playerNorth.getX()) {
			if (pBx2 <= playerNorth.getX() || pBx2 >= playerNorth.getX() + 300) {
				dx = dx * -1;
			}
			if (pBy2 <= playerNorth.getY() || pBy2 >= playerNorth.getY()) {
				dy = Math.abs(dy);
			}
			if (x<=playerNorth.getX()+50) {
				dx = dx - 5;	
			}
			if (x<=playerNorth.getX()+100) {
				dx = dx - 5;
			}
			if(x>=playerNorth.getX()+200){
				dx = dx +5;
			}
			if(x>=playerNorth.getX()+250){
				dx = dx +5;
			}
			Playsound(F);
			pkt=1;
			Zaehler=0;
		}

		// Unterer Spieler
		if (y <= playerSouth.getY() + 55 && y + by >= playerSouth.getY() && x <= playerSouth.getX() + 300
				&& x + bx >= playerSouth.getX()) {

			if (pBx2 <= playerSouth.getX() || pBx2 >= playerSouth.getX() + 300) {
				dx = dx * -1;
			}
			if (pBy2 <= playerSouth.getY() || pBy2 >= playerSouth.getY() + 50) {
				dy = -Math.abs(dy);
			}
			if (x<=playerSouth.getX()+50) {
				dx = dx - 5;	
			}
			if (x<=playerSouth.getX()+100) {
				dx = dx - 5;
			}
			if(x>=playerSouth.getX()+200){
				dx = dx +5;
			}
			if(x>=playerSouth.getX()+250){
				dx = dx +5;
			}
			Playsound(F);
			pkt=3;
			Zaehler=0;
		}

		// Max Geschwindigkeit:
		int bmax = 20;   //TODO hard coded
		Roundtime=Roundtime+elapsed;
		if(Roundtime>10000){
		dx += Math.signum(dx)*2;   //Vorzeichen von dx (1/-1) +Faktor bzw. 2mal dx
		dy += Math.signum(dy)*2;
		Roundtime=0;
		}
		if (dy > bmax) {
			dy = bmax;
		}
		if (dy < -bmax) {
			dy = -bmax;
		}
		if (dx > bmax) {
			dx = bmax;
		}
		if (dx < -bmax) {
			dx = -bmax;
		}
		
		// Blocker:
		//oben links Z=0
		//oben rechts Z=1
		//unten links Z=2
		//unten links Z=3
		for (int Z = 0; Z <= 3; Z++) {
			//Bugfix Unbeabsichtigte Kollision bei Torsperren
			if(playerNorth.getTor()==0||playerWest.getTor()==0){
				if(Z==0){
					Z++;
				}
			}
			if(playerNorth.getTor()==0||playerEast.getTor()==0){
				if(Z==1){
					Z++;
				}
			}
			if(playerWest.getTor()==0||playerSouth.getTor()==0){
				if(Z==2){
					Z++;
				}
			}
			if(playerEast.getTor()==0||playerSouth.getTor()==0){
				if(Z==3){
					blockerlimit=true;
				}
			}
			//Bugfix End + boolean ausserhalb
			if (x + bx >= blockers[Z].getX() && x <= blockers[Z].getX() + b && y + by >= blockers[Z].getY() && y <= blockers[Z].getY() + l && blockerlimit == false){
				if (pBx <= blockers[Z].getX()) {
					dx = -Math.abs(dx);
					//System.out.println("1");
				}
				if(pBx >= blockers[Z].getX() + b){
					dx = Math.abs(dx);
					//System.out.println("2");
				}
				if (pBy <= blockers[Z].getY()) {
					dy = -Math.abs(dy);
					//System.out.println("3");
				}
				if(pBy >= blockers[Z].getY() + l){
					dy = Math.abs(dy);
					//System.out.println("4");
				}
				//System.out.println(pBy);
				Playsound(F);
				Zaehler++;
			}
			else{
				blockerlimit=false;
			}
		}
		pBx= x;
		pBy= y;
		if(Zaehler>5){//Ballloopbrecher
			dx++;
			Zaehler=0;
			if(dy==0){
				dy++;
			}
		}
		//Endausgabe:
		if(end==3){
			if(state1==false){
				System.out.println("Spieler 2 hat gewonnen");
			}
			if(state2==false){
				System.out.println("Spieler 4 hat gewonnen");
			}
			if(state3==false){
				System.out.println("Spieler 3 hat gewonnen");
			}
			if(state4==false){
				System.out.println("Spieler 1 hat gewonnen");
			}
			Seat.P1.setScore(playerNorth.getPkt());
			Seat.P2.setScore(playerEast.getPkt());
			Seat.P3.setScore(playerSouth.getPkt());
			Seat.P4.setScore(playerWest.getPkt());
			System.out.println("playerNorth --->>> "+playerNorth.getPkt());
			System.out.println("playerEast  --->>> "+playerEast.getPkt());
			System.out.println("playerSouth --->>> "+playerSouth.getPkt());
			System.out.println("playerWest  --->>> "+playerWest.getPkt());
			tz=0;
			e=0;
			end++;
			game.setRunning(false);
			
		}
		if (state5) {
			// reset
			Ballstartrichtung = (int) ((Math.random()*4)+1);
			pkt=0;
			rect.x = 910;
			rect.y = 490;
			state5 = false;
			x = 512;
			y = 512;
//			x=500;
//			y=512;
//			dx=10;
//			dy=0;		
//			 x = 60;
//			 y = 470;
			if(YisDeath==false && XisDeath==true){
			dx = 10;
			dy = (Math.random()*11);	
			}
			if(XisDeath==false && YisDeath==true){
			dy = 10;
			dx = (Math.random()*11);	
		}
			else if (XisDeath==false && YisDeath==false){
				if(Ballstartrichtung==1){
				dx = (Math.random()*11);				
				dy = (Math.random()*11);
				}
				if(Ballstartrichtung==2){
					dx = -Math.abs((Math.random()*10)-11);				
					dy = (Math.random()*11)+10;
					}
				if(Ballstartrichtung==3){
					dx = (Math.random()*11)+10;				
					dy = -Math.abs((Math.random()*10)-11);
					}
				if(Ballstartrichtung==4){
					dx = -Math.abs((Math.random()*10)-11);				
					dy = -Math.abs((Math.random()*10)-11);
					}
			}
		}
	}
	   static void Playsound(File Sound){
	        try{
	            Clip clip = AudioSystem.getClip();
	            clip.open(AudioSystem.getAudioInputStream(Sound));
	            clip.start();
	            Thread.sleep(clip.getMicrosecondLength()/100000);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	   public void Pkterhalten(int pkt,Tile playerEast, Tile playerNorth, Tile playerSouth, Tile playerWest){
		   if(pkt==1){
			   playerNorth.setPkt();
		   }
		   if(pkt==2){
			   playerEast.setPkt();
		   }
		   if(pkt==3){
			   playerSouth.setPkt();
		   }
		   if(pkt==4){
			   playerWest.setPkt();
		   }
	   }
	   public void starter(Tile playerEast, Tile playerNorth, Tile playerSouth, Tile playerWest, int l){
		   if(Seat.P1.isPlaying()==true){
				playerNorth.setTor(p);
				playerNorth.setheight(th);
				playerNorth.setwidth(tb);
				playerNorth.seti();
			}
			else{
				playerNorth.setTor(0);
				playerNorth.setwidth(1024);
				playerNorth.setheight(l);
				playerNorth.seti();
				state1 = true;
				end++;
			}
			if(Seat.P2.isPlaying()==true){
				playerEast.setTor(p);
				playerEast.setwidth(th);
				playerEast.setheight(tb);
				playerEast.seti();
			}
			else{
				playerEast.setTor(0);
				playerEast.setheight(1024);
				playerEast.setwidth(100);
				playerEast.seti();
				state2 = true;
				end++;
			}
			if(Seat.P3.isPlaying()==true){
				playerSouth.setTor(p);
				playerSouth.setheight(th);
				playerSouth.setwidth(tb);
			}
			else{
				playerSouth.setTor(0);
				playerSouth.setwidth(1024);
				playerSouth.setheight(l);
				playerSouth.seti();
				state3 = true;
				end++;
			}
			if(Seat.P4.isPlaying()==true){
				playerWest.setTor(p);
				playerWest.setheight(tb);
				playerWest.setwidth(th);
			}
			else{
				playerWest.setTor(0);
				playerWest.setheight(1024);
				playerWest.setwidth(100);
				playerWest.seti();
				state4 = true;
				end++;
			}
			playerNorth.setY(0);
			playerEast.setX(1024);
			playerSouth.setY(1024);
			playerWest.setX(0);
			tz++;
	   }

} 
