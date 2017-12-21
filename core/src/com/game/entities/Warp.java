package com.game.entities;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.utils.RandomUtils;
import com.game.vector.Vector;

public class Warp extends Entity {

	private Texture texture;
	private Hitbox hitbox;
	private double angle;
	private int time;
	private Player player;

	public Warp(Level world, double x, double y, double angle, double speed, Player player) {
		super(world, x, y);
		hitbox = new Hitbox(this, 3, 3);
		texture = Textures.instance.getTexture("warp");
		velocity.setAngle(angle, speed);
		this.angle = angle;
		this.player = player;
		player.setInvisible(true);
		blast(position);
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
		if(++time > 20) {
			destroy();
		}
		
		updateTileCollisions();

		if(!shouldRemove()) {
			for(int i = 0; i < 3; i++) {
				world.spawn(new SparkParticle("warp_particle", world, position.x, position.y, RandomUtils.randAngle(), RandomUtils.randDouble(0.1, 0.5), 30));
			}
			
			if(player != null) {
				player.position.set(position.x, position.y);
			}
		}
	}
	
	private void blast(Vector pos) {
		for(int i = 0; i < 30; i++) {
			Vector spawnPos = pos.copy().add(RandomUtils.randVector(0, 30));
			world.spawn(new SparkParticle("warp_particle", world, spawnPos.x, spawnPos.y, position.angleBetween(spawnPos), RandomUtils.randDouble(0.5, 1.5), 30));
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		if(player != null) {
			Point tilemapPos = new Point(world.getTileMap().getMapCoordinate(position.x), world.getTileMap().getMapCoordinate(position.y));
			Vector spawnPos = new Vector(world.getTileMap().getWorldCoordinate(tilemapPos.x), world.getTileMap().getWorldCoordinate(tilemapPos.y));
			player.position.set(spawnPos.x, spawnPos.y);
			player.setInvisible(false);
			blast(spawnPos);
		}
	}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		float renderAngle = (float)Math.toDegrees(angle) - 90;
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - height / 2, width / 2, height / 2, width, height, 1, 1, renderAngle, 0, 0, width, height, false, false);
	}
}
