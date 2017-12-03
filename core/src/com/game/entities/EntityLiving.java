package com.game.entities;

import com.game.world.World;

public abstract class EntityLiving extends Entity {

	protected Hitbox hitbox;
	protected double walkSpeed;
	protected boolean up, down, left, right;
	protected boolean collided;
	
	protected boolean lockedVelocity;
	protected int pushTimer;
	protected double pushSpeed;
	protected double pushAngle;
	protected double pushAcceleration;

	public EntityLiving(World world, double x, double y, int width, int height, double walkSpeed) {
		super(world, x, y);
		hitbox = new Hitbox(this, width, height);
		this.walkSpeed = walkSpeed;
	}
	
	@Override
	protected void updateMotion() {
		if(!lockedVelocity) {
			double moveSpeed = (up || down) && (left || right) ? getWalkSpeed() * 0.707 : getWalkSpeed();
			if(up) { velocity.y = moveSpeed; }
			else if(down) { velocity.y = -moveSpeed; }
			else { velocity.y = 0; }
			if(right) { velocity.x = moveSpeed; }
			else if(left) { velocity.x = -moveSpeed; }
			else { velocity.x = 0; }
		}
		
		if(pushTimer > 0) {
			if(--pushTimer == 0) {
				setVelocityLocked(false);
				velocity.set(0, 0);
			}
			
			else {
				pushSpeed += pushAcceleration;
				velocity.setAngle(pushAngle, pushSpeed);
			}
		}

		updateTileCollisions();
		super.updateMotion();
	}

	public void updateTileCollisions() {
		boolean touchedCollidable = false;

		if(velocity.x != 0) {
			if(hitbox.collidedHorizontal(world.getTileMap())) {
				velocity.x = 0;
				touchedCollidable = true;
			}
		}

		if(velocity.y != 0) {
			if(hitbox.collidedVertical(world.getTileMap())) {
				velocity.y = 0;
				touchedCollidable = true;
			}
		}

		collided = touchedCollidable;
	}

	public boolean intersects(EntityLiving other) {
		return hitbox.intersectsHitbox(other.getHitbox());
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public boolean isWalking() {
		return up || down || left || right;
	}

	public void setWalkingUp() {
		up = true;
		down = false;
	}

	public void setWalkingDown() {
		up = false;
		down = true;
	}

	public void setStoppedVertical() {
		up = down = false;
	}

	public void setWalkingLeft() {
		left = true;
		right = false;
	}

	public void setWalkingRight() {
		left = false;
		right = true;
	}

	public void setStoppedHorizontal() {
		left = right = false;
	}

	public void setStopped() {
		up = down = left = right = false;
	}
	
	public void setVelocityLocked(boolean locked) {
		lockedVelocity = locked;
	}
	
	public void push(double angle, double speed, double acceleration, int time) {
		pushAcceleration = acceleration;
		pushAngle = angle;
		pushSpeed = speed;
		setVelocityLocked(true);
		velocity.setAngle(angle, speed);
		pushTimer = time;
	}

	public boolean isCollided() {
		return collided;
	}

	public double getWalkSpeed() {
		return walkSpeed;
	}
	
	public boolean canSee(Entity other) {
		int tileSize = world.getTileMap().getTileSize();
		double ownX = position.x / tileSize, ownY = position.y / tileSize, otherX = other.position.x / tileSize, otherY = other.position.y / tileSize;
		
		double deltaX = Math.abs(otherX - ownX);
		double deltaY = Math.abs(otherY - ownY);
		
		if(deltaX > 1600 || deltaY > 1600) {
			return false;
		}

		int x = (int)ownX;
		int y = (int)ownY;

		double inverseDeltaX = 1.0D / deltaX;
		double inverseDeltaY = 1.0D / deltaY;

		int n = 1;
		int xIncrement, yIncrement;
		double tNextVertical, tNextHorizontal;

		if(deltaX == 0) {
			xIncrement = 0;
			tNextHorizontal = inverseDeltaX;
		}
		
		else if(otherX > ownX) {
			xIncrement = 1;
			n += (int)otherX - x;
			tNextHorizontal = ((int)ownX + 1 - ownX) * inverseDeltaX;
		}
		
		else {
			xIncrement = -1;
			n += x - (int)otherX;
			tNextHorizontal = (ownX - (int)ownX) * inverseDeltaX;
		}

		if(deltaY == 0) {
			yIncrement = 0;
			tNextVertical = inverseDeltaY;
		}
		
		else if(otherY > ownY) {
			yIncrement = 1;
			n += (int)otherY - y;
			tNextVertical = ((int)ownY + 1 - ownY) * inverseDeltaY;
		}
		
		else {
			yIncrement = -1;
			n += y - (int)otherY;
			tNextVertical = (ownY - (int)ownY) * inverseDeltaY;
		}

		for(; n > 0; n--) {
			if(world.getTileMap().isTileCollidable(x, y)) {
				return false;
			}

			if(tNextVertical < tNextHorizontal) {
				y += yIncrement;
				tNextVertical += inverseDeltaY;
			}
			
			else {
				x += xIncrement;
				tNextHorizontal += inverseDeltaX;
			}
		}

		return true;
	}
}
