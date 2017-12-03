package com.game.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import com.game.graphics.Textures;
import com.game.rooms.HorizontalCorridor;
import com.game.rooms.Room;
import com.game.rooms.StandardRoom;
import com.game.rooms.StartRoom;
import com.game.rooms.VerticalCorridor;
import com.game.utils.RandomUtils;

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
	
	public static TileMap insertRoom(TileMap tileMap, Room room, World world, int x, int y) {
		for(int i = 0; i < room.getWidth(); i++) {
			for(int j = 0; j < room.getHeight(); j++) {
				tileMap.setTile(x + i, y + j, room.getTile(i, j));
			}
		}
		
		room.spawnEntities(world, x, y);
		return tileMap;
	}
	
	public static TileMap generateRandomMap(World world, TileMap map, int numPaths) {
		int numRoomsX = (int)(map.getWidth() / 24.0D) - 3, numRoomsY = (int)(map.getHeight() / 24.0D) - 2;
		int[][] rooms = new int[numRoomsY][numRoomsX];
		rooms[0][rooms[0].length - 1] = 1; //Exit room
		
		for(int i = 0; i < numPaths; i++) {
			int x = 0, y = rooms.length - 1;
			while(x != rooms[0].length - 1 || y != 0) {
				rooms[y][x] = 1;
				boolean xAxis = RandomUtils.randBoolean();
				if((xAxis && x == rooms[0].length - 1) || (!xAxis && y == 0)) {
					xAxis = !xAxis;
				}
				
				if(xAxis) { ++x; }
				else { --y; }
			}
		}
		
		int spawnRoomX = 0, spawnRoomY = 0;
		TileMapFactory.insertRoom(map, new StartRoom(world), world, spawnRoomX + 16, spawnRoomY);
		
		int firstRoomX = spawnRoomX + 14, firstRoomY = spawnRoomY + 14;
		int roomSeparation = 22;
		
		ArrayList<Point> xCorridors = new ArrayList<Point>(), yCorridors = new ArrayList<Point>();
		
		for(int y = 0; y < rooms.length; y++) {
			for(int x = 0; x < rooms[y].length; x++) {
				if(rooms[y][x] == 1) {
					int roomX = firstRoomX + x * roomSeparation;
					int roomY = firstRoomY + (rooms.length - 1 - y) * roomSeparation;
					TileMapFactory.insertRoom(map, new StandardRoom(world), world, roomX, roomY);
					
					//Horizontal corridor
					if(x < rooms[0].length - 1 && rooms[y][x + 1] == 1) {
						xCorridors.add(new Point(roomX + 15, roomY + RandomUtils.randInt(5, 11)));
					}
					
					//Vertical corridor
					if(y > 0 && rooms[y - 1][x] == 1) {
						yCorridors.add(new Point(roomX + RandomUtils.randInt(5, 11), roomY + 15));
					}
				}
			}
		}
		
		for(Point point : xCorridors) {
			TileMapFactory.insertRoom(map, new HorizontalCorridor(world), world, point.x, point.y);
		}
		
		for(Point point : yCorridors) {
			TileMapFactory.insertRoom(map, new VerticalCorridor(world), world, point.x, point.y);
		}
		
//		TileMapFactory.insertRoom(map, new StartRoom(world), world, 0, 0);
//		TileMapFactory.insertRoom(map, new StandardRoom(world), world, 16, 0);
//		TileMapFactory.insertRoom(map, new StandardRoom(world), world, 38, 0);
//		TileMapFactory.insertRoom(map, new VerticalCorridor(world), world, 3, 9);
//		TileMapFactory.insertRoom(map, new HorizontalCorridor(world), world, 9, 3);
//		TileMapFactory.insertRoom(map, new HorizontalCorridor(world), world, 31, 3);
		
		return map;
	}
}
