package com.game.entities.projectiles;

import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.level.Level;

public class Arrow extends PlayerProjectile {

	public Arrow(Level level, double x, double y, double angle, double speed, double damage) {
		super(level, x, y, 3, 3, angle, speed, 60, damage);
		texture = Textures.instance.getTexture("arrow");
	}
	
	protected void updateProjectileBehaviour() {}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		float renderAngle = (float)Math.toDegrees(angle) - 90;
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - height / 2, width / 2, height / 2, width, height, 1, 1, renderAngle, 0, 0, width, height, false, false);
	}
}
