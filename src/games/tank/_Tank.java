// package games.tank;
//
// import javax.swing.JPanel;
//
// import environment.implementation.MyGame;
// import environment.model.KeyRequest;
// import environment.model.gameobject.Seat;
//
// public class _Tank extends MyGame {
// public _Tank(JPanel PANEL, KeyRequest KEYS) {
// super(PANEL, KEYS);
//
// MusicPlayer player = new MusicPlayer();
// Field field = new Field(0);
// add(field);
//
// Tank[] tankArray = new Tank[4];
//
// if (Seat.P1.isPlaying())
// add(tankArray[0] = new Tank(Seat.P1, 80, 80, field, tankArray, KEYS, player,
// this));
//
// if (Seat.P2.isPlaying())
// add(tankArray[1] = new Tank(Seat.P2, 80, 944, field, tankArray, KEYS, player,
// this));
//
// if (Seat.P3.isPlaying())
// add(tankArray[2] = new Tank(Seat.P3, 944, 944, field, tankArray, KEYS,
// player, this));
//
// if (Seat.P4.isPlaying())
// add(tankArray[3] = new Tank(Seat.P4, 944, 80, field, tankArray, KEYS, player,
// this));
//
// add(new ScoreCounter());
//
// }
// }
