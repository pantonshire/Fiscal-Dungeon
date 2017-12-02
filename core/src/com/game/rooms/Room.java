package com.game.rooms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.game.world.World;

public abstract class Room {
	
	private byte[][] tiles;
	
	public Room(World world, byte[][] tiles) {
		List<byte[]> list = Arrays.asList(tiles);
		Collections.reverse(list);
		this.tiles = tiles;
	}
	
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
