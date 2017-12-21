package com.game.rooms;

import com.game.entities.BigGem;
import com.game.entities.BigRedGem;
import com.game.entities.BlackDemonCoin;
import com.game.entities.BlackTreasureChest;
import com.game.entities.CoinSnake;
import com.game.entities.DemonCoin;
import com.game.entities.GoldCoin;
import com.game.entities.RedGem;
import com.game.entities.TreasureChest;
import com.game.level.TileMap;
import com.game.level.Level;
import com.game.level.LevelFactory;
import com.game.utils.RandomUtils;

public class LongRoom extends Room {

	public LongRoom(Level world) {
		super(world, new byte[][] {
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1 },
			new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
		});
	}

	public void spawnEntities(Level world, int minX, int minY, int difficulty) {
		TileMap tiles = world.getTileMap();

		if(difficulty == LevelFactory.EASY) {
			switch(RandomUtils.randInt(5)) {
			case 0:
				world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 16), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigGem(world, tiles.getWorldCoordinate(minX + 22), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 17), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 21), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2:
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 18), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 20), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 18), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 20), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 3:
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 4:
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 5:
				for(int x = 3; x <= 35; x++) {
					for(int y = 3; y <= 13; y++) {
						world.spawn(new GoldCoin(world, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				break;
			default:
				break;
			}
		}

		else if(difficulty == LevelFactory.NORMAL) {
			switch(RandomUtils.randInt(9)) {
			case 0:
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 17), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 21), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2: case 3:
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 15), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 16), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 17), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 18), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 20), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 21), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 22), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 23), tiles.getWorldCoordinate(minY + 7)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 15), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 16), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 17), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 18), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 20), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 21), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 22), tiles.getWorldCoordinate(minY + 9)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 23), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 4:
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 5:
				world.spawn(new TreasureChest(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new CoinSnake(world, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 6:
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 7:
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 12)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 4)));
			case 8: case 9:
				for(int x = 3; x <= 35; x++) {
					for(int y = 3; y <= 13; y++) {
						world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			default:
				break;
			}
		}

		else if(difficulty == LevelFactory.HARD) {
			switch(RandomUtils.randInt(9)) {
			case 0:
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new TreasureChest(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2:
				world.spawn(new TreasureChest(world, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new TreasureChest(world, tiles.getWorldCoordinate(minX + 28), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 3:
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 4:
				world.spawn(new BlackTreasureChest(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 5:
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 6:
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 7:
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 12)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 4)));
			case 8: case 9:
				for(int x = 5; x <= 33; x++) {
					for(int y = 5; y <= 11; y++) {
						world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				world.spawn(new TreasureChest(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			default:
				break;
			}
		}

		else if(difficulty == LevelFactory.MADNESS) {
			switch(RandomUtils.randInt(9)) {
			case 0:
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BlackTreasureChest(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BigRedGem(world, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2: case 3:
				world.spawn(new TreasureChest(world, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new TreasureChest(world, tiles.getWorldCoordinate(minX + 28), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 4:
				world.spawn(new BlackTreasureChest(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 5:
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 6:
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 7:
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 4)));
				world.spawn(new BlackDemonCoin(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 12)));
				world.spawn(new DemonCoin(world, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 4)));
			case 8: case 9:
				for(int x = 5; x <= 33; x++) {
					for(int y = 5; y <= 11; y++) {
						world.spawn(new RedGem(world, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				world.spawn(new BlackTreasureChest(world, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			default:
				break;
			}
		}
	}
}
