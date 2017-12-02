package com.game.entities;

import com.game.graphics.LayerRenderer;
import com.game.vector.Vector;
import com.game.world.World;

public abstract class Entity {

	protected World world;
	protected Vector position, velocity;
	private boolean shouldRemove;
	
	public Entity(World world, double x, double y) {
		position = new Vector(x, y);
		velocity = new Vector();
		this.world = world;
	}
	
	/** Returns the position vector associated with this entity, allowing you to get or set the position. */
	public Vector getPosition() {
		return position;
	}
	
	/** Returns the velocity vector associated with this entity, allowing you to get or set the velocity. */
	public Vector getVelocity() {
		return velocity;
	}
	
	/** Returns true if the entity will be removed from the game world at the end of the next tick. */
	public boolean shouldRemove() {
		return shouldRemove;
	}
	
	/** Schedules the entity to be removed from the game world at the end of the next tick. */
	public void destroy() {
		shouldRemove = true;
	}
	
	/** Called each frame to update the position and behaviour of the entity. */
	public void update() {
		updateMotion();
		updateEntity();
	}
	
	/** Moves the entity based on its current velocity. */
	protected void updateMotion() {
		if(!velocity.isZero()) {
			position.add(velocity);
		}
	}
	
	/** Used by subclasses of entity to update their behaviour. */
	protected abstract void updateEntity();
	
	/** Used by subclasses of entity to draw sprites to the screen at their location. */
	public abstract void render(LayerRenderer renderer);
}
