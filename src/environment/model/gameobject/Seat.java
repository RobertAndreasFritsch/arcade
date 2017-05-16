package environment.model.gameobject;

import java.awt.Color;
import java.awt.event.KeyEvent;

public enum Seat {

	//
	P1(KeyEvent.VK_8, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_9, Color.RED, "Red"),
	//
	P2(KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_Q, KeyEvent.VK_E, Color.BLUE, "Blue"),
	//
	P4(KeyEvent.VK_T, KeyEvent.VK_F, KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_R, KeyEvent.VK_Y, Color.YELLOW, "Yellow"),
	//
	P3(KeyEvent.VK_I, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L, KeyEvent.VK_U, KeyEvent.VK_O, new Color(0,128,0), "Green"),

	//
	P1_PlayerView(KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_8, KeyEvent.VK_4, KeyEvent.VK_7, KeyEvent.VK_9,
			Color.RED, "Red"),
			//
			P2_PlayerView(KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E,
					Color.BLUE, "Blue"),
					//
					P4_PlayerView(KeyEvent.VK_F, KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_T, KeyEvent.VK_R,
							KeyEvent.VK_Y, Color.YELLOW, "Yellow"),
							//
							P3_PlayerView(KeyEvent.VK_I, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L, KeyEvent.VK_U,
									KeyEvent.VK_O, Color.GREEN, "Green");

	public final int UP, LEFT, DOWN, RIGHT;

	public final int BTN1, BTN2;

	private String name = "";
	private boolean playing = false;
	private int score = 0;

	private final Color color;

	private Seat(int UP, int LEFT, int DOWN, int RIGHT, int BTN1, int BTN2, Color color, String name) {
		this.UP = UP;
		this.LEFT = LEFT;
		this.DOWN = DOWN;
		this.RIGHT = RIGHT;
		this.BTN1 = BTN1;
		this.BTN2 = BTN2;
		this.color = color;
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
