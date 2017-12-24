package com.game.level;

import com.game.Main;
import com.game.graphics.LayerRenderer;
import com.game.run.Run;
import com.game.spells.PortalSpell;
import com.game.spells.SpellUpgrade;

public class LevelFactory {

	public static final int NUM_FLOORS = 8;
	public static final int
	EASY = 0,
	BOSS_1 = 1,
	NORMAL = 2,
	BOSS_2 = 3,
	HARD = 4,
	BOSS_3 = 5,
	MADNESS = 6,
	BOSS_4 = 7;

	public static int floor = 4;
	private static final int[] SIZES = new int[] { 100, 100, 170, 100, 200, 100, 240, 100 };

	public static void firstFloor(LayerRenderer game, LayerRenderer overlay) {
		floor = EASY;
		Run.newRun(new PortalSpell());
		Run.currentRun.spell.upgrade(SpellUpgrade.HOMING);
		Run.currentRun.spell.upgrade(SpellUpgrade.HOMING);
		Run.currentRun.spell.upgrade(SpellUpgrade.HOMING);
		Run.currentRun.spell.upgrade(SpellUpgrade.PORTAL_RANGE);
		Run.currentRun.spell.upgrade(SpellUpgrade.PORTAL_RANGE);
		Run.currentRun.spell.upgrade(SpellUpgrade.PORTAL_RANGE);
//		Run.currentRun.spell.upgrade(SpellUpgrade.SPEED);
//		Run.currentRun.spell.upgrade(SpellUpgrade.SPEED);
//		Run.currentRun.spell.upgrade(SpellUpgrade.SPEED);
		Run.currentRun.spell.upgrade(SpellUpgrade.RATE_OF_FIRE);
		Run.currentRun.spell.upgrade(SpellUpgrade.RATE_OF_FIRE);
		Run.currentRun.spell.upgrade(SpellUpgrade.RATE_OF_FIRE);
		Run.currentRun.spell.upgrade(SpellUpgrade.NUM_SHOTS);
		Run.currentRun.spell.upgrade(SpellUpgrade.NUM_SHOTS);
		Run.currentRun.spell.upgrade(SpellUpgrade.NUM_SHOTS);
//		Run.currentRun.spell.upgrade(SpellUpgrade.ACCURACY);
//		Run.currentRun.spell.upgrade(SpellUpgrade.ACCURACY);
//		Run.currentRun.spell.upgrade(SpellUpgrade.ACCURACY);
		Level nextFloor = new Level(game, overlay, SIZES[floor], SIZES[floor], floor % 2 != 0);
		Main.nextLevel = nextFloor;
	}

	public static void nextFloor(Level current) {
		if(nextFloorExists()) {
			++floor;
			Level nextFloor = new Level(current.gameRenderer, current.overlayRenderer, SIZES[floor], SIZES[floor], floor % 2 != 0);
			Main.nextLevel = nextFloor;
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
		case BOSS_1: case BOSS_2: case BOSS_3: case BOSS_4:
			return "boss fight!";
		default:
			return "you shouldn\'t see this message";
		}
	}

	public static String getTileset() {
		switch(floor) {
		case EASY: case BOSS_1:
			return "tilemap";
		case NORMAL: case BOSS_2:
			return "tilemap_2";
		case HARD: case BOSS_3:
			return "tilemap_3";
		case MADNESS: case BOSS_4:
			return "tilemap_4";
		default:
			return "tilemap";
		}
	}
}
