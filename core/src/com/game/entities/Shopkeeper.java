package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;

public class Shopkeeper extends Entity {

	private Animation shopkeeper;
	private Texture counter;

	public Shopkeeper(Level level, double x, double y) {
		super(level, x, y);
		shopkeeper = new Animation(Textures.instance.getTexture("shopkeeper_v2"), Sequence.formatSequences(new Sequence(22, 22, 4, 6)));
		counter = Textures.instance.getTexture("shop_counter");
	}

	protected void updateEntity() {
		
	}

	public void render(LayerRenderer renderer) {
		int width = counter.getWidth(), height = counter.getHeight();
		renderer.getSpriteBatch().draw(counter, (float)position.x - width / 2, (float)position.y - height / 2);
		int frameWidth = shopkeeper.getFrameWidth(), frameHeight = shopkeeper.getFrameHeight();
		renderer.getSpriteBatch().draw(shopkeeper.getFrame(), (float)position.x - frameWidth / 2, (float)position.y - frameHeight / 2 + 8);
		shopkeeper.updateTimer();
	}
}
