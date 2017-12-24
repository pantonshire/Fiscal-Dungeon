package com.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.game.level.Level;
import com.game.utils.AngleHelper;

public abstract class PlayerProjectile extends Entity {

	//Rendering
	protected Texture texture;

	//Lifetime
	protected int time;
	protected int maxTime;

	//Damage and collision
	protected Hitbox hitbox;
	protected double damage;
	protected int damageCooldown;
	protected boolean collideWithTiles;
	protected boolean destroyOnTileCollision;
	protected boolean piercing;

	//Movement
	protected double speed;
	protected double angle;

	//Homing
	protected boolean homing;
	protected double homingRange;
	protected double turnSpeed;
	protected Enemy targetedEnemy;

	//Ricochet
	protected boolean bouncy;
	protected int bounces;
	protected int maxBounces;

	public PlayerProjectile(Level level, double x, double y, int width, int height, double angle, double speed, int maxTime, double damage) {
		super(level, x, y);

		//Set fields
		this.angle = angle;
		this.speed = speed;
		this.maxTime = maxTime;
		this.damage = damage;
		hitbox = new Hitbox(this, width, height);
		collideWithTiles = true;
		destroyOnTileCollision = true;

		if(speed > 0) {
			velocity.setAngle(angle, speed);
		}
	}

	public PlayerProjectile setHoming(double turnSpeed, double range) {
		homing = true;
		this.turnSpeed = turnSpeed;
		this.homingRange = range;
		return this;
	}

	public PlayerProjectile setCollisionType(boolean collideWithTiles, boolean destroyOnTileCollision, boolean piercing) {
		this.collideWithTiles = collideWithTiles;
		this.destroyOnTileCollision = destroyOnTileCollision;
		this.piercing = piercing;
		return this;
	}

	public PlayerProjectile setBouncy(int maxBounces) {
		bouncy = true;
		this.maxBounces = maxBounces;
		return this;
	}

	protected void updateTileCollisions() {
		if(collideWithTiles) {
			boolean touchedCollidable = false;
			if(velocity.x != 0) {
				if(hitbox.collidedHorizontal(level.getTileMap())) {
					touchedCollidable = true;
					if(bouncy) {
						velocity.x = -velocity.x;
						angle = Math.atan2(velocity.y, velocity.x);
					}

					else {
						velocity.x = 0;
					}
				}
			}

			if(velocity.y != 0) {
				if(hitbox.collidedVertical(level.getTileMap())) {
					touchedCollidable = true;
					if(bouncy) {
						velocity.y = -velocity.y;
						angle = Math.atan2(velocity.y, velocity.x);
					}

					else {
						velocity.y = 0;
					}
				}
			}

			if(touchedCollidable && destroyOnTileCollision) {
				if(bouncy) {
					bounce();
				}

				else {
					destroy();
				}
			}
		}
	}

	private boolean checkEnemyCollisions() {
		if(damageCooldown == 0) {
			ArrayList<Enemy> enemies = level.getEnemies();
			boolean hitEnemy = false;
			double closestDistance = 0;
			Enemy closestEnemy = null;

			for(Enemy enemy : enemies) {
				if(!hitEnemy && hitbox.intersectsHitbox(enemy.hitbox)) {
					if(enemy.damage(damage)) {
						hitEnemy = true;
						if(bouncy) {
							velocity.x = -velocity.x;
							velocity.y = -velocity.y;
							bounce();
						}
						
						else if(!piercing) {
							break;
						}
					}
				}

				if(homing && !enemy.invulnerable()) {
					double distance = position.distBetween(enemy.position);
					if(distance <= homingRange && (closestEnemy == null || distance < closestDistance)) {
						closestEnemy = enemy;
						closestDistance = distance;
					}
				}
			}
			
			if(hitEnemy) {
				if(piercing || bouncy) {
					damageCooldown = 10;
				}
				
				else {
					destroy();
				}
			}

			else if(homing && closestEnemy != null) {
				targetedEnemy = closestEnemy;
			}
		}

		return !shouldRemove();
	}
	
	private void updateHoming() {
		if(targetedEnemy != null) {
			if(targetedEnemy.shouldRemove() || targetedEnemy.invulnerable()) {
				targetedEnemy = null;
			}
			
			else {
				double target = position.angleBetween(targetedEnemy.position);
				double angle = Math.atan2(velocity.y, velocity.x);
				if(Math.abs(AngleHelper.angleDifferenceRadians(angle, target)) >= turnSpeed) {
					int direction = AngleHelper.getQuickestRotationDirection(angle, target);
					angle += turnSpeed * direction;
					if(Math.abs(AngleHelper.angleDifferenceRadians(angle, target)) < turnSpeed) {
						angle = target;
					}
					
					velocity.setAngle(angle, speed);
				}
				
				else if(angle != target) {
					angle = target;
					velocity.setAngle(angle, speed);
				}
			}
		}
	}

	private void bounce() {
		if(++bounces > maxBounces) {
			destroy();
		}
	}

	protected void updateEntity() {
		if(homing) { updateHoming(); }
		
		if(++time > maxTime && maxTime > 0) {
			destroy();
			return;
		}
		
		updateTileCollisions();

		if(!shouldRemove() && checkEnemyCollisions()) {
			updateProjectileBehaviour();
		}
	}

	protected abstract void updateProjectileBehaviour();
}
