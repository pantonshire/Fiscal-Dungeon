package com.game.rooms;

import com.game.entities.Shopkeeper;
import com.game.entities.Tax;
import com.game.entities.Trapdoor;
import com.game.level.TileMap;
import com.game.level.Level;

public class ExitRoom extends Room {

	public ExitRoom(Level level) {
		super(level, new byte[][] {
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

	public void spawnEntities(Level level, int minX, int minY, int difficulty) {
		TileMap tiles = level.getTileMap();
		level.spawn(new Trapdoor(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 12)));
		
		level.spawn(new Shopkeeper(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 14) - 4));
		
		int numTaxes = difficulty / 2 + 1;
		for(int i = 0; i < numTaxes; i++) {
			level.spawn(new Tax(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8 - i)));
		}
	}
}
