package com.game.world;

import com.game.entities.Player;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;

public class World {

	private LayerRenderer gameRenderer;
	private LayerRenderer overlayRenderer;
	private TileMap tiles;
	private Player player;
	
	public World(LayerRenderer gameRenderer, LayerRenderer overlayRenderer) {
		this.gameRenderer = gameRenderer;
		this.overlayRenderer = overlayRenderer;
		
		tiles = new TileMap(Textures.instance.getTexture("coin"), new byte[100][100], 20);
		player = new Player(this, 300, 300);
	}
	
	public TileMap getTileMap() {
		return tiles;
	}
	
	public void update() {
		player.update();
	}
	
	public void render(int pass) {
		switch(pass) {
			case 0:
				renderGameLayer();
				break;
			case 1:
				renderOverlayLayer();
				break;
			default:
				break;
		}
	}
	
	private void renderGameLayer() {
		player.render(gameRenderer);
	}
	
	private void renderOverlayLayer() {
		
	}
}
