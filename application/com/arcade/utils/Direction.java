package com.arcade.utils;

public enum Direction
{
	NORTH(1, 0, -1), EAST(2, 1, 0), SOUTH(4, 0, 1), WEST(8, -1, 0);

	static
	{
		Direction.NORTH.opposite = Direction.SOUTH;
		Direction.NORTH.right = Direction.EAST;
		Direction.NORTH.left = Direction.WEST;

		Direction.EAST.opposite = Direction.WEST;
		Direction.EAST.right = Direction.SOUTH;
		Direction.EAST.left = Direction.NORTH;

		Direction.SOUTH.opposite = Direction.NORTH;
		Direction.SOUTH.right = Direction.WEST;
		Direction.SOUTH.left = Direction.EAST;

		Direction.WEST.opposite = Direction.EAST;
		Direction.WEST.right = Direction.NORTH;
		Direction.WEST.left = Direction.SOUTH;
	}

	private Direction	opposite;
	private Direction	left;
	private Direction	right;

	private int			bitmask;
	private int			x;
	private int			y;

	Direction(final int bitmask, final int x, final int y)
	{
		this.bitmask = bitmask;
		this.x = x;
		this.y = y;
	}

	public Direction getOpposite()
	{
		return this.opposite;
	}

	public Direction getLeft()
	{
		return this.left;
	}

	public Direction getRight()
	{
		return this.right;
	}

	public int getBitmask()
	{
		return this.bitmask;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}
}