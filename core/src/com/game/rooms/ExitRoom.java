package com.game.rooms;

import com.game.entities.Tax;
import com.game.entities.Trapdoor;
import com.game.world.TileMap;
import com.game.world.World;

public class ExitRoom extends Room {

	public ExitRoom(World world) {
		super(world, new byte[][] {
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -9, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -2, -3, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -6, -7, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -6, -7, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -6, -7, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -6, -7, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -4, -5, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
		});
	}

	public void spawnEntities(World world, int minX, int minY, int difficulty) {
		TileMap tiles = world.getTileMap();
		world.spawn(new Trapdoor(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 12)));
		
		int numTaxes = difficulty / 2 + 1;
		for(int i = 0; i < numTaxes; i++) {
			world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8 - i)));
		}
	}
}
