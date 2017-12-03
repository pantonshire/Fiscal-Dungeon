package com.game.rooms;

import com.game.world.World;

public class StartRoom extends Room {

	public StartRoom(World world) {
		super(world, new byte[][] {
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			new byte[] { 1, -1, -1, -1, -2, -3, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -4, -5, -1, -1, -1, 1 },
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		});
	}

	public void spawnEntities(World world, int minX, int minY, int difficulty) {

	}
}
