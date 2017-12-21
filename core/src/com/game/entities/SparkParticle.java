package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.level.Level;

public class SparkParticle extends Entity {

	private final Texture texture;
	private final int lifespan;
	private int time;

	public SparkParticle(String textureName, Level world, double x, double y, double angle, double speed, int lifespan) {
		super(world, x, y);
		texture = Textures.instance.getTexture(textureName);
		this.lifespan = lifespan;
		velocity.setAngle(angle, speed);
	}

	protected void updateEntity() {
		if(++time > lifespan) { destroy(); }
	}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - height / 2);
	}
}
