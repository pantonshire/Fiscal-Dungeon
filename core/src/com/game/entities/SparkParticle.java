package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.utils.RandomUtils;
import com.game.world.World;

public class SparkParticle extends Entity {

	private Texture texture;
	private int time;

	public SparkParticle(World world, double x, double y, double angle, double speed) {
		super(world, x, y);
		texture = Textures.instance.getTexture(RandomUtils.randBoolean() ? "fireball_particle_red" : "fireball_particle_yellow");
		velocity.setAngle(angle, speed);
	}

	protected void updateEntity() {
		if(++time > 40) { destroy(); }
	}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - height / 2);
	}
}
