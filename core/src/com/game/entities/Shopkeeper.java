package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.world.World;

public class Shopkeeper extends Entity {

	private Texture texture;

	public Shopkeeper(World world, double x, double y) {
		super(world, x, y);
		texture = Textures.instance.getTexture("shopkeeper");
	}

	protected void updateEntity() {
		
	}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - height / 2);
	}
}
