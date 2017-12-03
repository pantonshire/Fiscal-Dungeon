package com.game.rooms;

import com.game.world.World;

public class VerticalCorridor extends Room {

	public VerticalCorridor(World world) {
		super(world, new byte[][] {
			new byte[] { 1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, 1 }
		});
	}

	public void spawnEntities(World world, int minX, int minY, int difficulty) {

	}
}
