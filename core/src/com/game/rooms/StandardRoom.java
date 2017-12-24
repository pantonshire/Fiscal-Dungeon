package com.game.rooms;

import com.game.entities.BigGem;
import com.game.entities.BigRedGem;
import com.game.entities.BlackDemonCoin;
import com.game.entities.BlackTreasureChest;
import com.game.entities.CoinSnake;
import com.game.entities.DemonCoin;
import com.game.entities.GoldCoin;
import com.game.entities.RedGem;
import com.game.entities.Tax;
import com.game.entities.TreasureChest;
import com.game.level.TileMap;
import com.game.level.Level;
import com.game.level.LevelFactory;
import com.game.utils.RandomUtils;

public class StandardRoom extends Room {

	public StandardRoom(Level level) {
		super(level, new byte[][] {
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

	public void spawnEntities(Level level, int minX, int minY, int difficulty) {
		TileMap tiles = level.getTileMap();

		if(difficulty == LevelFactory.EASY) {
			switch(RandomUtils.randInt(6)) {
			case 0:
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 2)));
				break;
			case 2:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 3:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new Tax(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 4:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 5:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 2), tiles.getWorldCoordinate(minY + 2)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 14)));
				break;
			case 6:
				for(int x = 3; x <= 13; x++) {
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
			switch(RandomUtils.randInt(11)) {
			case 0:
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 2)));
				break;
			case 2:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 3:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 4:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 5:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 2), tiles.getWorldCoordinate(minY + 2)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 14)));
				break;
			case 6:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 7:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 8:
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				break;
			case 9:
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 2)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				break;
			case 10:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 5), tiles.getWorldCoordinate(minY + 5)));
				break;
			case 11:
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 11), tiles.getWorldCoordinate(minY + 11)));
				break;
			case 12:
				for(int x = 3; x <= 13; x++) {
					for(int y = 3; y <= 13; y++) {
						level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				break;
			default:
				break;
			}
		}
		
		else if(difficulty == LevelFactory.HARD) {
			switch(RandomUtils.randInt(11)) {
			case 0:
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 5), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 11), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 2:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 3:
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 4:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 5:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 2), tiles.getWorldCoordinate(minY + 2)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 14)));
				break;
			case 6:
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 6)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 6)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 10)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 10)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 7:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 8:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				break;
			case 9:
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				break;
			case 10:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 5), tiles.getWorldCoordinate(minY + 5)));
				break;
			case 11:
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 12:
				for(int x = 5; x <= 11; x++) {
					for(int y = 5; y <= 11; y++) {
						level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				break;
			default:
				break;
			}
		}
		
		else if(difficulty == LevelFactory.MADNESS) {
			switch(RandomUtils.randInt(13)) {
			case 0:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 5), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 11), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 1: case 2:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 8)));
				break;
			case 3:
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new BlackDemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 4:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 5:
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new DemonCoin(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 6:
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 2), tiles.getWorldCoordinate(minY + 2)));
				level.spawn(new BigRedGem(level, tiles.getWorldCoordinate(minX + 14), tiles.getWorldCoordinate(minY + 14)));
				break;
			case 7:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 6)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 6)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 6), tiles.getWorldCoordinate(minY + 10)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 10), tiles.getWorldCoordinate(minY + 10)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new CoinSnake(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 8:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 7), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 9)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 7)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 8)));
				level.spawn(new GoldCoin(level, tiles.getWorldCoordinate(minX + 9), tiles.getWorldCoordinate(minY + 9)));
				break;
			case 9:
				level.spawn(new TreasureChest(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 12)));
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				break;
			case 10:
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new BigGem(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 4)));
				break;
			case 11:
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 4), tiles.getWorldCoordinate(minY + 4)));
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 12), tiles.getWorldCoordinate(minY + 12)));
				break;
			case 12: case 13:
				for(int x = 5; x <= 11; x++) {
					for(int y = 5; y <= 11; y++) {
						level.spawn(new RedGem(level, tiles.getWorldCoordinate(minX + x), tiles.getWorldCoordinate(minY + y)));
					}
				}
				level.spawn(new BlackTreasureChest(level, tiles.getWorldCoordinate(minX + 8), tiles.getWorldCoordinate(minY + 8)));
				break;
			default:
				break;
			}
		}
	}
}
