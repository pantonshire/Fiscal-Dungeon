package com.game.rooms;

import com.game.entities.Tax;
import com.game.entities.Trapdoor;
import com.game.utils.RandomUtils;
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
		
		switch(RandomUtils.randInt(2)) {
		case 0:
			world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
			break;
		case 1:
			world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 9)));
			world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 9)));
			break;
		case 2:
			world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 9)));
			world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
			world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 9)));
			break;
		default:
			world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
			break;
		}
	}
}
