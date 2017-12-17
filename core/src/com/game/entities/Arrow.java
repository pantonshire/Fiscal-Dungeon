package com.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.world.World;

public class Arrow extends Entity {

	private Texture texture;
	private Hitbox hitbox;
	private double angle;
	private int time;

	public Arrow(World world, double x, double y, double angle, double speed) {
		super(world, x, y);
		hitbox = new Hitbox(this, 3, 3);
		texture = Textures.instance.getTexture("arrow");
		velocity.setAngle(angle, speed);
		this.angle = angle;
	}

	public void updateTileCollisions() {
		boolean touchedCollidable = false;

		if(velocity.x != 0) {
			if(hitbox.collidedHorizontal(world.getTileMap())) {
				velocity.x = velocity.y = 0;
				touchedCollidable = true;
			}
		}

		if(velocity.y != 0) {
			if(hitbox.collidedVertical(world.getTileMap())) {
				velocity.x = velocity.y = 0;
				touchedCollidable = true;
			}
		}

		if(touchedCollidable) {
			destroy();
		}
	}

	protected void updateEntity() {
		if(++time > 60) {
			destroy();
		}
		
		updateTileCollisions();

		if(!shouldRemove()) {
			ArrayList<Enemy> enemies = world.getEnemies();
			for(Enemy enemy : enemies) {
				if(hitbox.intersectsHitbox(enemy.hitbox)) {
					if(enemy.damage(2)) {
						destroy();
					}
				}
			}
		}
	}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		float renderAngle = (float)Math.toDegrees(angle) - 90;
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - width / 2, width / 2, height / 2, width, height, 1, 1, renderAngle, 0, 0, width, height, false, false);
	}
}
