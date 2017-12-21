package com.game.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import com.game.graphics.Textures;
import com.game.rooms.ExitRoom;
import com.game.rooms.HorizontalCorridor;
import com.game.rooms.LongRoom;
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

	public static TileMap insertRoom(TileMap tileMap, Room room, Level world, int x, int y, int difficulty) {
		for(int i = 0; i < room.getWidth(); i++) {
			for(int j = 0; j < room.getHeight(); j++) {
				tileMap.setTile(x + i, y + j, room.getTile(i, j));
			}
		}

		room.spawnEntities(world, x, y, difficulty);
		return tileMap;
	}

	public static TileMap generateRandomMap(Level world, TileMap map, int numPaths, int difficulty) {
		int numRoomsX = (int)(map.getWidth() / 24.0D) - 1, numRoomsY = (int)(map.getHeight() / 24.0D) - 1;
		int[][] rooms = new int[numRoomsY][numRoomsX];
		rooms[0][rooms[0].length - 1] = 3; //Exit room

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

		int spawnRoomX = 5, spawnRoomY = 7;
		TileMapFactory.insertRoom(map, new StartRoom(world), world, 21, 5, difficulty);

		int firstRoomX = spawnRoomX + 14, firstRoomY = spawnRoomY + 14;
		int roomSeparation = 22;

		ArrayList<Point> xCorridors = new ArrayList<Point>(), yCorridors = new ArrayList<Point>();

		for(int y = 0; y < rooms.length; y++) {
			for(int x = 0; x < rooms[y].length; x++) {
				if(rooms[y][x] == 1 || rooms[y][x] == 2 || rooms[y][x] == 3) {
					int roomX = firstRoomX + x * roomSeparation;
					int roomY = firstRoomY + (rooms.length - 1 - y) * roomSeparation;
					boolean longRoom = false;

					//Horizontal corridor
					if(x < rooms[0].length - 1 && (rooms[y][x + 1] == 1 || rooms[y][x + 1] == 2 || rooms[y][x + 1] == 3)) {
						if(RandomUtils.randDouble() > 0.3 || rooms[y][x] == 2 || rooms[y][x + 1] == 3) {
							xCorridors.add(new Point(roomX + 15, roomY + RandomUtils.randInt(10)));
						}

						else {
							longRoom = true;
							rooms[y][x + 1] = 2;
						}
					}

					//Vertical corridor
					if(y > 0 && (rooms[y - 1][x] == 1 || rooms[y - 1][x] == 2 || rooms[y - 1][x] == 3)) {
						yCorridors.add(new Point(roomX + RandomUtils.randInt(10), roomY + 15));
					}

					if(rooms[y][x] == 1) {
						if(longRoom) {
							TileMapFactory.insertRoom(map, new LongRoom(world), world, roomX, roomY, difficulty);
						}

						else {
							TileMapFactory.insertRoom(map, new StandardRoom(world), world, roomX, roomY, difficulty);
						}
					}
					
					else if(rooms[y][x] == 3) {
						TileMapFactory.insertRoom(map, new ExitRoom(world), world, roomX, roomY, difficulty);
					}
				}
			}
		}

		for(Point point : xCorridors) {
			TileMapFactory.insertRoom(map, new HorizontalCorridor(world), world, point.x, point.y, difficulty);
		}

		for(Point point : yCorridors) {
			TileMapFactory.insertRoom(map, new VerticalCorridor(world), world, point.x, point.y, difficulty);
		}

		TileMapFactory.insertRoom(map, new VerticalCorridor(world), world, 23, 14, difficulty);

		return map;
	}
}
