package com.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.game.graphics.LayerRenderer;

public class TileMap {

	private TextureRegion tileset;
	private int tileSize;
	private byte[][] tiles;

	public TileMap(Texture texture, byte[][] tiles, int tileSize) {
		this.tiles = tiles;
		tileset = new TextureRegion();
		this.tileSize = tileSize;
		setTileset(texture);
	}

	public TileMap(Texture texture, int width, int height, int tileSize) {
		this(texture, new byte[height][width], tileSize);
	}

	public void setTileset(Texture texture) {
		tileset.setRegion(texture);
	}

	private int getTilesetTileSize() {
		return tileSize + getBorderSize();
	}
	
	private int getBorderSize() {
		return 0;
	}

	public byte getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public int getMapCoordinate(double coordinate) {
		return (int)(coordinate / tileSize);
	}

	public void setTile(int x, int y, int tile) {
		tiles[y][x] = (byte)tile;
	}
	
	public void clean() {
		for(int y = 0; y < tiles.length; y++) {
			for(int x = 0; x < tiles[y].length; x++) {
				tiles[y][x] = 0;
			}
		}
	}
	
	public void makeNew(int width, int height) {
		tiles = new byte[height][width];
	}
	
	public int getHeight() {
		return tiles.length;
	}
	
	public int getWidth() {
		return tiles[0].length;
	}

	public boolean isInMapBounds(int x, int y) {
		return x >= 0 && y >= 0 && y < tiles.length && x < tiles[y].length;
	}

	public boolean isTileCollidable(int x, int y) {
		if(x < 0 || y < 0 || y >= tiles.length || x >= tiles[y].length) { return false; }
		return tiles[y][x] > 0; //Negative tiles are not collidable
	}

	public int getTileSize() {
		return tileSize;
	}

	public void render(LayerRenderer renderer) {
		int halfBorderSize = getBorderSize() / 2;
		Vector3 cameraPosition = renderer.getCamera().position;
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		float zoom = renderer.getCamera().zoom;
		float minX = cameraPosition.x - (width / 2 * zoom), maxX = cameraPosition.x + (width / 2 * zoom), minY = cameraPosition.y - (height / 2 * zoom), maxY = cameraPosition.y + (height / 2 * zoom);
		int minTileX = (int)(minX / tileSize) - 1, maxTileX = (int)(maxX / tileSize), minTileY = (int)(minY / tileSize), maxTileY = (int)(maxY / tileSize);
		byte lastTile = 0;

		for(int y = minTileY; y <= maxTileY; y++) {
			for(int x = minTileX; x <= maxTileX; x++) {
				if(y >= 0 && y < tiles.length && x >= 0 && x < tiles[y].length) {
					byte tile = tiles[y][x];
					if(tile != 0) {
						if(tile != lastTile) {
							lastTile = tile;
							tileset.setRegion(getTilesetColumn(tile) * getTilesetTileSize(), getTilesetRow(tile) * getTilesetTileSize(), getTilesetTileSize(), getTilesetTileSize());
						}

						renderer.getSpriteBatch().draw(tileset, x * tileSize - halfBorderSize, y * tileSize - halfBorderSize);
					}
				}
			}
		}
	}

	public TextureRegion getTileTexture(byte tile) {
		tileset.setRegion(getTilesetColumn(tile) * getTilesetTileSize(), getTilesetRow(tile) * getTilesetTileSize(), getTilesetTileSize(), getTilesetTileSize());
		return tileset;
	}

	private static int getTilesetRow(byte tile) {
		return tile < 0 ? 1 : 0;
	}

	private static int getTilesetColumn(byte tile) {
		return Math.abs(tile) - 1;
	}
}
