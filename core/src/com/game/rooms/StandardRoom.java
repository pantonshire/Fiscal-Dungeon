package com.game.rooms;

import com.game.entities.BigGem;
import com.game.entities.BigRedGem;
import com.game.entities.BlackDemonCoin;
import com.game.entities.CoinSnake;
import com.game.entities.DemonCoin;
import com.game.entities.GoldCoin;
import com.game.entities.RedGem;
import com.game.entities.Tax;
import com.game.entities.TreasureChest;
import com.game.utils.RandomUtils;
import com.game.world.TileMap;
import com.game.world.World;
import com.game.world.WorldFactory;

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

	public void spawnEntities(World world, int minX, int minY, int difficulty) {
		TileMap tiles = world.getTileMap();

		if(difficulty == WorldFactory.EASY) {
			switch(RandomUtils.randInt(6)) {
			case 0:
				world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
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
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new Tax(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 4:
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 5:
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 2), tiles.getWorldCoordinate(minY + 2)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 14)));
				break;
			case 6:
				for(int x = 3; x <= 13; x++) {
					for(int y = 3; y <= 13; y++) {
						world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				break;
			default:
				break;
			}
		}
		
		else if(difficulty == WorldFactory.NORMAL) {
			switch(RandomUtils.randInt(11)) {
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
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 3:
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
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
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 8:
				world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				break;
			case 9:
				world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 2)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				break;
			case 10:
				world.spawn(new TreasureChest(world, tiles.getWorldCoordinate(minX + 5), tiles.getWorldCoordinate(minY + 5)));
				break;
			case 11:
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 11), tiles.getWorldCoordinate(minY + 11)));
				break;
			case 12:
				for(int x = 3; x <= 13; x++) {
					for(int y = 3; y <= 13; y++) {
						world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				break;
			default:
				break;
			}
		}
	}
}
