package com.game.world;

import com.game.graphics.LayerRenderer;

public class World {

	private LayerRenderer gameRenderer;
	private LayerRenderer overlayRenderer;
	private TileMap tiles;
	
	public World(LayerRenderer gameRenderer, LayerRenderer overlayRenderer) {
		this.gameRenderer = gameRenderer;
		this.overlayRenderer = overlayRenderer;
	}
	
	public TileMap getTileMap() {
		return tiles;
	}
	
	public void update() {
		
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
		
	}
	
	private void renderOverlayLayer() {
		
	}
}
