package com.arcade.utils;

import static java.awt.Color.*;
import static java.awt.event.KeyEvent.*;

import java.awt.Color;

public enum Seat
{

	/***/
	P1(VK_8, VK_4, VK_5, VK_6, VK_7, VK_9, RED, 3.1415926536),
	/***/
	P2(VK_W, VK_A, VK_S, VK_D, VK_Q, VK_E, BLUE, 4.7123889804),
   //
	P4(VK_T, VK_F, VK_G, VK_H, VK_R, VK_Y, GREEN, 0),
	/***/
	P3(VK_I, VK_J, VK_K, VK_L, VK_U, VK_O, YELLOW, 1.5707963268),

	/***/
	P1_PlayerView(VK_5, VK_6, VK_8, VK_4, VK_7, VK_9, RED, 3.1415926536),
	/***/
	P2_PlayerView(VK_D, VK_W, VK_A, VK_S, VK_Q, VK_E, BLUE, 4.7123889804),
	/***/
	P4_PlayerView(VK_F, VK_G, VK_H, VK_T, VK_R, VK_Y, GREEN, 0),
	/***/
	P3_PlayerView(VK_I, VK_J, VK_K, VK_L, VK_U, VK_O, YELLOW, 1.5707963268);

	public final int	UP;
	public final int	LEFT;
	public final int	DOWN;
	public final int	RIGHT;
	public final int	BTN1;
	public final int	BTN2;

	private boolean	playing	= false;

	private Color		color;
	private double		transformation;

	private Seat(final int UP, final int LEFT, final int DOWN, final int RIGHT, final int BTN1, final int BTN2,
	      final Color color, final double transformation)
	{
		this.UP = UP;
		this.LEFT = LEFT;
		this.DOWN = DOWN;
		this.RIGHT = RIGHT;
		this.BTN1 = BTN1;
		this.BTN2 = BTN2;
		this.color = color;
		this.transformation = transformation;
	}

	public Color getColor()
	{
		return this.color;
	}

	public boolean isPlaying()
	{
		return this.playing;
	}

	public void setPlaying(final boolean playing)
	{
		this.playing = playing;
	}

	public double getTransform()
	{
		return this.transformation;
	}

	/**
	 * @return
	 * @deprecated Score and NameSelection have been removed from the project
	 */
	@Deprecated
	public String getName()
	{
		return "";
	}

	/**
	 * @return
	 * @deprecated Score and NameSelection have been removed from the project
	 */
	@Deprecated
	public int getScore()
	{
		System.err.println("@deprecated function-call");
		return -1;
	}

	/**
	 * @param name
	 * @deprecated Score and NameSelection have been removed from the project
	 */
	@Deprecated
	public void setName(final String name)
	{
		System.err.println("@deprecated function-call");
	}

	/**
	 * @param score
	 * @deprecated Score and NameSelection have been removed from the project
	 */
	@Deprecated
	public void setScore(final int score)
	{
		System.err.println("@deprecated function-call");
	}
}
