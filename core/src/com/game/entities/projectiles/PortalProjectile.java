package com.game.entities.projectiles;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.game.entities.Enemy;
import com.game.entities.Entity;
import com.game.entities.Hitbox;
import com.game.entities.SparkParticle;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.light.LevelLightManager;
import com.game.light.LightSource;
import com.game.utils.AngleHelper;
import com.game.utils.RandomUtils;
import com.game.vector.Vector;

public class PortalProjectile extends Entity implements LightSource {

	private Texture texture;
	private Hitbox hitbox;
	private double angle;
	private int time;
	private double speed;
	private boolean homing;
	private double homingRange;
	private double turnSpeed;
	private Enemy targetedEnemy;

	public PortalProjectile(Level level, double x, double y, double angle, double speed, int homingLevel) {
		super(level, x, y);
		hitbox = new Hitbox(this, 3, 3);
		texture = Textures.instance.getTexture("portal_projectile");
		velocity.setAngle(angle, speed);
		this.angle = angle;
		this.speed = speed;
		
		if(homingLevel > 0) {
			homing = true;
			turnSpeed = Math.toRadians(10) + (Math.toRadians(10) * (homingLevel - 1));
			homingRange = 96 + (32 * homingLevel);
		}
		
		level.getLightManager().addDynamicLight(this);
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
		if(++time > 360) {
			destroy();
		}
		
		if(homing && targetedEnemy != null) {
			if(targetedEnemy.shouldRemove() || targetedEnemy.invulnerable()) {
				targetedEnemy = null;
			}
			
			else {
				double target = position.angleBetween(targetedEnemy.getPosition());
				int direction = AngleHelper.getQuickestRotationDirection(angle, target);
				angle += turnSpeed * direction;
				if(Math.abs(AngleHelper.angleDifferenceRadians(angle, target)) < turnSpeed) {
					angle = target;
				}
				
				velocity.setAngle(angle, speed);
			}
		}
		
		updateTileCollisions();

		if(!shouldRemove()) {
			if(time % 3 == 0) {
				level.spawn(new SparkParticle("dart_trap_particle", level, position.x, position.y, RandomUtils.randAngle(), RandomUtils.randDouble(0.1, 0.5), 30));
			}
			
			ArrayList<Enemy> enemies = level.getEnemies();
			double closestDistance = 0;
			Enemy closestEnemy = null;
			
			for(Enemy enemy : enemies) {
				if(hitbox.intersectsHitbox(enemy.getHitbox())) {
					if(enemy.damage(1)) {
						destroy();
						break;
					}
				}
				
				else if(homing && !enemy.invulnerable()) {
					double distance = position.distBetween(enemy.getPosition());
					if(distance <= homingRange && (closestEnemy == null || distance < closestDistance)) {
						closestEnemy = enemy;
						closestDistance = distance;
					}
				}
			}
			
			if(homing && closestEnemy != null) {
				targetedEnemy = closestEnemy;
			}
		}
	}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		float renderAngle = (float)Math.toDegrees(angle) - 90;
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - height / 2, width / 2, height / 2, width, height, 1, 1, renderAngle, 0, 0, width, height, false, false);
	}
	
	public Vector lightPosition() {
		return position;
	}
	
	public int numLightRays() {
		return LevelLightManager.DEFAULT_RADIAL_SOURCE;
	}
	
	public float lightStrength() {
		return 32;
	}
	
	public Color lightColor() {
		return new Color(0.75F, 0.05F, 0.9F, 0.25F);
	}
}
