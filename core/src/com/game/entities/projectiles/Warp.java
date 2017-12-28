package com.game.entities.projectiles;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.game.entities.Entity;
import com.game.entities.Hitbox;
import com.game.entities.Player;
import com.game.entities.SparkParticle;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.utils.AngleHelper;
import com.game.utils.RandomUtils;
import com.game.vector.Vector;

public class Warp extends Entity {

	private Texture texture;
	private Hitbox hitbox;
	private double angle;
	private double speed;
	private int time;
	private boolean controllable;
	private double turnSpeed;
	private Player player;
	private Point lastUnblockedTile;

	public Warp(Level level, double x, double y, double angle, double speed, int controlLevel, Player player) {
		super(level, x, y);
		hitbox = new Hitbox(this, 3, 3);
		texture = Textures.instance.getTexture("warp");
		velocity.setAngle(angle, speed);
		this.angle = angle;
		this.speed = speed;
		this.player = player;
		
		if(controlLevel > 0) {
			controllable = true;
			turnSpeed = Math.toRadians(8/3) * controlLevel;
		}
		
		player.setInvisible(true);
		
		lastUnblockedTile = new Point(level.getTileMap().getMapCoordinate(position.x), level.getTileMap().getMapCoordinate(position.y));
		if(level.getTileMap().isTileCollidable(lastUnblockedTile.x, lastUnblockedTile.y)) {
			lastUnblockedTile.setLocation(level.getTileMap().getMapCoordinate(player.getPosition().x), level.getTileMap().getMapCoordinate(player.getPosition().y));
		}
		
		blast(position);
	}

	public void updateTileCollisions() {
		boolean touchedCollidable = false;

		if(velocity.x != 0) {
			if(hitbox.collidedHorizontal(level.getTileMap())) {
				velocity.x = velocity.y = 0;
				touchedCollidable = true;
			}
		}

		if(velocity.y != 0) {
			if(hitbox.collidedVertical(level.getTileMap())) {
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
		
		if(controllable) {
			double target = player.getLookAngle();
			if(Math.abs(AngleHelper.angleDifferenceRadians(angle, target)) >= turnSpeed) {
				int direction = AngleHelper.getQuickestRotationDirection(angle, target);
				angle += direction * turnSpeed;
				if(Math.abs(AngleHelper.angleDifferenceRadians(angle, target)) < turnSpeed) {
					angle = target;
				}
				
				velocity.setAngle(angle, speed);
			}
		}
		
		updateTileCollisions();

		if(!shouldRemove()) {
			Point currentPoint = new Point(level.getTileMap().getMapCoordinate(position.x), level.getTileMap().getMapCoordinate(position.y));
			if(!level.getTileMap().isTileCollidable(currentPoint.x, currentPoint.y)) {
				lastUnblockedTile.setLocation(currentPoint.x, currentPoint.y);
			}
			
			for(int i = 0; i < 3; i++) {
				level.spawn(new SparkParticle("warp_particle", level, position.x, position.y, RandomUtils.randAngle(), RandomUtils.randDouble(0.1, 0.5), 30));
			}
			
			if(player != null) {
				player.getPosition().set(position.x, position.y);
			}
		}
	}
	
	private void blast(Vector pos) {
		for(int i = 0; i < 30; i++) {
			Vector spawnPos = pos.copy().add(RandomUtils.randVector(0, 30));
			level.spawn(new SparkParticle("warp_particle", level, spawnPos.x, spawnPos.y, position.angleBetween(spawnPos), RandomUtils.randDouble(0.5, 1.5), 30));
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		if(player != null) {
			if(player.getHitbox().collidedHorizontal(level.getTileMap()) || player.getHitbox().collidedVertical(level.getTileMap())) {
				Vector spawnPos = new Vector(level.getTileMap().getWorldCoordinate(lastUnblockedTile.x), level.getTileMap().getWorldCoordinate(lastUnblockedTile.y));
				player.getPosition().set(spawnPos.x, spawnPos.y);
				blast(spawnPos);
			}
			
			else {
				blast(position);
			}
			
			player.setInvisible(false);
		}
	}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		float renderAngle = (float)Math.toDegrees(angle) - 90;
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - height / 2, width / 2, height / 2, width, height, 1, 1, renderAngle, 0, 0, width, height, false, false);
	}
}
