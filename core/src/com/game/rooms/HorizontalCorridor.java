package com.game.rooms;

import com.game.world.World;

public class HorizontalCorridor extends Room {

	public HorizontalCorridor(World world) {
		super(world, new byte[][] {
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 },
			new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
			new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 }
		});
	}
}
