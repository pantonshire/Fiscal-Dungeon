package com.game.rooms;

import com.game.level.Level;

public class VerticalCorridor extends Room {

	public VerticalCorridor(Level world) {
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

	public void spawnEntities(Level world, int minX, int minY, int difficulty) {

	}
}
