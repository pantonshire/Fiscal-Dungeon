package com.game.entities;

import com.game.vector.Vector;
import com.game.world.TileMap;

public class Hitbox {

	private EntityLiving entity;
	private double width, height;
	
	public Hitbox(EntityLiving boundEntity, double width, double height) {
		entity = boundEntity;
		this.width = width;
		this.height = height;
	}
	
	public Vector getPosition() {
		return entity.getPosition().copy().sub(width / 2, height / 2);
	}
	
	public boolean collidedVertical(TileMap tiles) {
		Vector position = getPosition();
		Vector velocity = entity.getVelocity();
		int xl = tiles.getMapCoordinate(position.x);
		int xr = tiles.getMapCoordinate(position.x + width);
		int y = tiles.getMapCoordinate(velocity.y < 0 ? position.y + velocity.y : position.y + height + velocity.y);
		
		for(int x = xl; x <= xr; x++) {
			if(tiles.isTileCollidable(x, y)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean collidedHorizontal(TileMap tiles) {
		Vector position = getPosition();
		Vector velocity = entity.getVelocity();
		int yb = tiles.getMapCoordinate(position.y);
		int yt = tiles.getMapCoordinate(position.y + height);
		int x = tiles.getMapCoordinate(velocity.x < 0 ? position.x + velocity.x : position.x + width + velocity.x);
		
		for(int y = yb; y <= yt; y++) {
			if(tiles.isTileCollidable(x, y)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean intersectsHitbox(Hitbox other) {
		Vector ownPosition = getPosition();
		Vector otherPosition = other.getPosition();
		return ownPosition.x < otherPosition.x + other.width && ownPosition.x + width > otherPosition.x && ownPosition.y < otherPosition.y + other.height && ownPosition.y + height > otherPosition.y;
	}
}
