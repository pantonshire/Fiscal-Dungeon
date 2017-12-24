package com.game.rooms;

import com.game.level.Level;

public class HorizontalCorridor extends Room {

	public HorizontalCorridor(Level level) {
		super(level, new byte[][] {
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 },
			new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
			new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
			new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
			new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 }
		});
	}
	
	public void spawnEntities(Level level, int minX, int minY, int difficulty) {
		
	}
}
