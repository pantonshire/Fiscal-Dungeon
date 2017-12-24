package com.game.rooms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.game.level.Level;

public abstract class Room {
	
	private byte[][] tiles;
	
	public Room(Level level, byte[][] tiles) {
		List<byte[]> list = Arrays.asList(tiles);
		Collections.reverse(list);
		this.tiles = tiles;
	}
	
	public abstract void spawnEntities(Level level, int minX, int minY, int difficulty);
	
	public byte[][] getTiles() {
		return tiles;
	}
	
	public byte getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public void setTile(int tile, int x, int y) {
		tiles[y][x] = (byte)tile;
	}
	
	public int getWidth() {
		return tiles[0].length;
	}
	
	public int getHeight() {
		return tiles.length;
	}
}
