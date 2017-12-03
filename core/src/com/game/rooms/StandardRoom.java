package com.game.rooms;

import com.game.entities.BigGem;
import com.game.entities.BigRedGem;
import com.game.entities.CoinSnake;
import com.game.entities.DemonCoin;
import com.game.utils.RandomUtils;
import com.game.world.TileMap;
import com.game.world.World;

public class StandardRoom extends Room {

	public StandardRoom(World world) {
		super(world, new byte[][] {
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
		});
	}

	public void spawnEntities(World world, int minX, int minY) {
		TileMap tiles = world.getTileMap();
		switch(RandomUtils.randInt(9)) {
		case 0:
			world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
			break;
		case 1:
			world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 2)));
			break;
		case 2:
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
			break;
		case 3:
			world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
			break;
		case 4:
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
			world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
			break;
		case 5:
			world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 2), tiles.getWorldCoordinate(minY + 2)));
			world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 14)));
			break;
		case 6:
			world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
			break;
		case 7:
			world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
			break;
		case 8:
			world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
			world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
			break;
		case 9:
			world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 2)));
			world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
			break;
		default:
			break;
		}
	}
}
