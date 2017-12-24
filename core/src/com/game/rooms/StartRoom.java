package com.game.rooms;

import com.game.level.Level;

public class StartRoom extends Room {

	public StartRoom(Level level) {
		super(level, new byte[][] {
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			new byte[] { 1, -1, -1, -1, -2, -3, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -6, -7, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -4, -5, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		});
	}

	public void spawnEntities(Level level, int minX, int minY, int difficulty) {

	}
}
