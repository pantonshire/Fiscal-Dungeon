package com.game.world;

import java.util.Arrays;

import com.game.graphics.Textures;
import com.game.rooms.Room;

public class TileMapFactory {

	public static TileMap newBlankMap(String tileMap, byte defaultTile, int tileSize, int width, int height) {
		byte[][] tiles = new byte[height][width];
		for(int row = 0; row < height; row++) {
			byte[] rowTiles = new byte[width];
			Arrays.fill(rowTiles, defaultTile);
			tiles[row] = rowTiles;
		}
		
		return new TileMap(Textures.instance.getTexture(tileMap), tiles, tileSize);
	}
	
	public static TileMap insertRoom(TileMap tileMap, Room room, int x, int y) {
		for(int i = 0; i < room.getWidth(); i++) {
			for(int j = 0; j < room.getHeight(); j++) {
				tileMap.setTile(x + i, y + j, room.getTile(i, j));
			}
		}
		
		return tileMap;
	}
}
