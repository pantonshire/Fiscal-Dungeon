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

	public LongRoom(Level level) {
		super(level, new byte[][] {
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

	public void spawnEntities(Level level, int minX, int minY, int difficulty) {
		TileMap tiles = level.getTileMap();

		if(difficulty == LevelFactory.EASY) {
			switch(RandomUtils.randInt(5)) {
			case 0:
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 16), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 22), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 17), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 21), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 18), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 20), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 18), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 20), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 3:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 4:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 5:
				for(int x = 3; x <= 35; x++) {
					for(int y = 3; y <= 13; y++) {
						level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
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
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 17), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 21), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2: case 3:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 15), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 16), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 17), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 18), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 20), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 21), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 22), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 23), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 15), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 16), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 17), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 18), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 20), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 21), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 22), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 23), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 4:
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 5:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 6:
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 7:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 4)));
			case 8: case 9:
				for(int x = 3; x <= 35; x++) {
					for(int y = 3; y <= 13; y++) {
						level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			default:
				break;
			}
		}

		else if(difficulty == LevelFactory.HARD) {
			switch(RandomUtils.randInt(9)) {
			case 0:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 28), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 3:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 4:
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 5:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 6:
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 7:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 4)));
			case 8: case 9:
				for(int x = 5; x <= 33; x++) {
					for(int y = 5; y <= 11; y++) {
						level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			default:
				break;
			}
		}

		else if(difficulty == LevelFactory.MADNESS) {
			switch(RandomUtils.randInt(9)) {
			case 0:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 32), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2: case 3:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 28), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 4:
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 5:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 34), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 6:
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 7:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 29), tiles.getWorldCoordinate(minY + 4)));
			case 8: case 9:
				for(int x = 5; x <= 33; x++) {
					for(int y = 5; y <= 11; y++) {
						level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 19), tiles.getWorldCoordinate(minY + 8)));
				break;
			default:
				break;
			}
		}
	}
}
