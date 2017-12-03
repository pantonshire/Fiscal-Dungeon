package com.game.world;

import com.game.Main;
import com.game.graphics.LayerRenderer;

public class WorldFactory {

	public static final int NUM_FLOORS = 4;
	public static final int
	EASY = 0,
	NORMAL = 1,
	HARD = 2,
	MADNESS = 3;

	public static int floor = 4;
	private static final int[] SIZES = new int[] { 100, 170, 200, 240 };

	public static void firstFloor(LayerRenderer game, LayerRenderer overlay) {
		floor = 0;
		World nextFloor = new World(game, overlay, SIZES[floor], SIZES[floor]);
		Main.nextWorld = nextFloor;
	}
	
	public static void nextFloor(World current) {
		if(nextFloorExists()) {
			++floor;
			World nextFloor = new World(current.gameRenderer, current.overlayRenderer, SIZES[floor], SIZES[floor]);
			nextFloor.getPlayer().setCoins(current.getPlayer().getCoins());
			Main.nextWorld = nextFloor;
		}
		
		else {
			Main.toCongratulesScreen();
		}
	}

	public static boolean nextFloorExists() {
		return floor < NUM_FLOORS - 1;
	}
	
	public static String getFloorName() {
		switch(floor) {
		case EASY:
			return "dungeon entrance";
		case NORMAL:
			return "dungeon depths";
		case HARD:
			return "treasure trove";
		case MADNESS:
			return "madness";
		default:
			return "you shouldn\'t see this message";
		}
	}
	
	public static String getTileset() {
		switch(floor) {
		case EASY:
			return "tilemap";
		case NORMAL:
			return "tilemap_2";
		case HARD:
			return "tilemap_3";
		case MADNESS:
			return "tilemap_4";
		default:
			return "tilemap";
		}
	}
}
